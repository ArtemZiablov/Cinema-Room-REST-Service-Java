package cinema.exceptions;

public class SeatCoordinatesOutOfBoundException extends BusinessException {
    public SeatCoordinatesOutOfBoundException() {
        super("The number of a row or a column is out of bounds!");
    }
}
