package core.serde;

public class SerDeException extends RuntimeException {

    public SerDeException(String message) {
        super(message);
    }

    public SerDeException(String message, Throwable cause) {
        super(message, cause);
    }
}
