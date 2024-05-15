package cinema.controllers;

import cinema.configs.CinemaProperties;
import cinema.exceptions.WrongPasswordException;
import cinema.models.*;
import cinema.services.CinemaRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j(topic = "CinemaController")
@RequiredArgsConstructor
@RestController
public class CinemaController {
    //static Logger logger = LoggerFactory.getLogger(CinemaController.class);  // could be simpled by annotation @Slf4j

    final CinemaProperties props;
    final CinemaRoomService cinemaRoomService;

    @GetMapping("/seats")
    CinemaRoom getAvailableSeats(){
        int nRows = props.nRows();
        int nColumns = props.nCols();
        List<Seat> seats = cinemaRoomService.getAvailableSeats();

        return new CinemaRoom(nRows, nColumns, seats);
    }

    @PostMapping("/purchase")
    SoldTicket purchase(@RequestBody SeatCoordinates seatCoordinates){
        // trace debug info warn error
        log.info("Req to /purchase : {}", seatCoordinates);
        return cinemaRoomService.purchase(seatCoordinates);
    }

    @PostMapping("/return")
    ReturnedTicketResponse purchase(@RequestBody TicketReturnRequest ticketReturnRequest){
        // trace debug info warn error
        log.info("Req to /return : {}", ticketReturnRequest);
        Seat seat = cinemaRoomService.ticketReturn(ticketReturnRequest.token());
        return new ReturnedTicketResponse(seat);
    }

    @GetMapping("/stats")
    Stats getStatistics(@RequestParam(value = "password") Optional<String> password){
        log.info("Req to /stats :");
        log.info("password = {}", password);

        password.filter(pass -> props.secret().equals(pass))
                .orElseThrow(WrongPasswordException::new);

        return cinemaRoomService.stats();
    }
}
