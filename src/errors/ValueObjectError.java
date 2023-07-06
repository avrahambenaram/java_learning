package errors;

public class ValueObjectError extends ErrorException {
    public ValueObjectError(String message) {
        super(message, 403);
    }
}
