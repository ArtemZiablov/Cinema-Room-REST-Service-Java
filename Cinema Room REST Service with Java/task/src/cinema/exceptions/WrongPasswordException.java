package cinema.exceptions;

public class WrongPasswordException extends BusinessException{
    public WrongPasswordException() {
        super("The password is wrong!");
    }
}
