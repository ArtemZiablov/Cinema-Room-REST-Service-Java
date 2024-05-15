package cinema.models.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@ToString
@Entity
@Table(name = "seats")
public class SeatEntity {
    @EmbeddedId
    SeatCoordinatesId coordinates;

    Integer price;
    String token;
}
