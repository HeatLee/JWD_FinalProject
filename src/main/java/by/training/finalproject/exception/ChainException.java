package by.training.finalproject.exception;

public class ChainException extends Exception {
    public ChainException() {
        super();
    }

    public ChainException(String message) {
        super(message);
    }

    public ChainException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChainException(Throwable cause) {
        super(cause);
    }
}
