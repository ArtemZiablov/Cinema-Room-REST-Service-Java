package cinema.repositories;

import cinema.models.Seat;
import cinema.models.entities.SeatCoordinatesId;
import cinema.models.entities.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface SeatRepository extends JpaRepository<SeatEntity, SeatCoordinatesId> {
    int countByTokenNotNull();

    int countByTokenNull();

    @Transactional(readOnly = true)
    @Query("select sum(se.price) from SeatEntity se where se.token is not null")
    int sumPriceByTokenNotNull();

    List<SeatEntity> findByTokenNull();

    Optional<SeatEntity> findByToken(String token);
}
