package exception;

public class notEnoughMoneyException extends RuntimeException {
    public notEnoughMoneyException(String message) {
        super(message);
    }
}
