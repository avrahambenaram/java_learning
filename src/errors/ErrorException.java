package errors;

public class ErrorException extends Exception {
    public Integer statusCode;
    public ErrorException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
