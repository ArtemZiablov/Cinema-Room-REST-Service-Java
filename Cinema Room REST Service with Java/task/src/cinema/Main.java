package cinema;

import cinema.configs.CinemaProperties;
import cinema.models.entities.SeatCoordinatesId;
import cinema.models.entities.SeatEntity;
import cinema.models.mappers.SeatMapper;
import cinema.repositories.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@Slf4j(topic = "Main")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ApplicationRunner runner(SeatRepository repo, CinemaProperties props, SeatMapper mapper){
        return args -> {
            for(int iRow = 1; iRow <= props.nRows(); iRow++){
                for(int iCol = 1; iCol <= props.nCols(); iCol++){
                    var coords = new SeatCoordinatesId(iRow, iCol);
                    repo.save(SeatEntity.builder()
                            .coordinates(coords)
                            .build());
                }

            }
            log.info("There are {} seats in the database", repo.count());
            var seatEntity = new SeatEntity(new SeatCoordinatesId(2, 3), 10, UUID.randomUUID().toString());
            var seat = mapper.map(seatEntity);
            log.info("seatEntity: {}", seatEntity);
            log.info("seat: {}", seat);
        };
    }
}
