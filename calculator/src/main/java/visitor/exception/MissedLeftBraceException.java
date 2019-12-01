package visitor.exception;

public class MissedLeftBraceException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Miss left brace";
    }
}
