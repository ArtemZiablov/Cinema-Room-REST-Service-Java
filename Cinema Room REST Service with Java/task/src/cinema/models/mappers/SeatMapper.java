package cinema.models.mappers;

import cinema.models.Seat;
import cinema.models.SeatCoordinates;
import cinema.models.entities.SeatCoordinatesId;
import cinema.models.entities.SeatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    SeatCoordinatesId map(SeatCoordinates seatCoordinates); /*{
        return new SeatCoordinatesId(
                seatCoordinates.row(),
                seatCoordinates.column()
        );
    }*/

    @Mapping(target = "row", source = "coordinates.row")
    @Mapping(target = "column", source = "coordinates.column")
    Seat map(SeatEntity seatEntity); /*{
        return new Seat(
                seatEntity.getCoordinates().getRow(),
                seatEntity.getCoordinates().getColumn(),
                seatEntity.getPrice()
        );
    }*/
}
