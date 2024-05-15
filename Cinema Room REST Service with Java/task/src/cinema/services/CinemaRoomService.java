package cinema.services;

import cinema.configs.CinemaProperties;
import cinema.exceptions.AlreadyPurchasedException;
import cinema.exceptions.SeatCoordinatesOutOfBoundException;
import cinema.exceptions.WrongTokenException;
import cinema.models.*;
import cinema.models.entities.SeatCoordinatesId;
import cinema.models.entities.SeatEntity;
import cinema.models.mappers.SeatMapper;
import cinema.repositories.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CinemaRoomService {
    final CinemaProperties props;
    //final CinemaRepo cinemaRepo;
    //final SoldTicketRepo soldTicketRepo;
    final SeatRepository seatRepository;
    final SeatMapper seatMapper;

    @Transactional
    public List<Seat> getAvailableSeats() {
        return seatRepository.findByTokenNull().stream()
                .map(SeatEntity::getCoordinates)
                .map(this::addPrice)
                .toList();
    }

    private int getPriceForRow(int row) {
        return row <= props.firstRows()
                ? props.firstRowsPrice()
                : props.lastRowsPrice();
    }

    private Seat addPrice(SeatCoordinatesId seat) {
        return new Seat(
                seat.getRow(),
                seat.getColumn(),
                getPriceForRow(seat.getRow()));
    }

    @Transactional
    public SoldTicket purchase(SeatCoordinates seatCoordinates) {

        var seatCoordinatesId = seatMapper.map(seatCoordinates);
        SeatEntity seatEntity = seatRepository.findById(seatCoordinatesId).orElseThrow(SeatCoordinatesOutOfBoundException::new);
        if (seatEntity.getToken() != null) {
            throw new AlreadyPurchasedException();
        }
        var seat = addPrice(seatCoordinatesId);
        String token = UUID.randomUUID().toString();

        seatEntity.setPrice(seat.price());
        seatEntity.setToken(token);

        return new SoldTicket(token, seat);
    }

    @Transactional
    public Seat ticketReturn(String token) {
        var seatEntity = seatRepository.findByToken(token)
                .orElseThrow(WrongTokenException::new);
        Seat seat = seatMapper.map(seatEntity);

        seatEntity.setPrice(null);
        seatEntity.setToken(null);
        seatRepository.save(seatEntity);

        return seat;
    }

    @Transactional(readOnly = true)
    public Stats stats() {
        int income = seatRepository.sumPriceByTokenNotNull();
        int available = seatRepository.countByTokenNull();
        int purchased = seatRepository.countByTokenNotNull();

        return new Stats(income, available, purchased);
    }
}
