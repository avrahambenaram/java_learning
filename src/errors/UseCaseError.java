package errors;

public class UseCaseError extends ErrorException {
    public UseCaseError(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
