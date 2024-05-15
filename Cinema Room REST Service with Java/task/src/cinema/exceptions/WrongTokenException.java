package cinema.exceptions;

public class WrongTokenException extends BusinessException{
    public WrongTokenException() {
        super("Wrong token!");
    }
}
