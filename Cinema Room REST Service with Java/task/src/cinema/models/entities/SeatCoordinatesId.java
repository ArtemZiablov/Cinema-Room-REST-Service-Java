package cinema.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Embeddable
public class SeatCoordinatesId {
    @Column(name = "row_id")
    private int row;
    @Column(name = "col_id")
    private int column;
}
