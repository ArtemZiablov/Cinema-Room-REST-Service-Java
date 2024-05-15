package cinema.exceptions;

public class AlreadyPurchasedException extends BusinessException {
    public AlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }
}
