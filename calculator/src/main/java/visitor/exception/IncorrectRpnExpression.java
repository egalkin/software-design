package visitor.exception;

public class IncorrectRpnExpression extends RuntimeException{
    @Override
    public String getMessage() {
        return "Given rpn expression was parsed incorrectly";
    }
}
