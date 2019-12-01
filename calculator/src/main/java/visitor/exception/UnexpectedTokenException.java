package visitor.exception;

import token.Token;

public class UnexpectedTokenException extends RuntimeException {
    private Token unexpectedToken;

    public UnexpectedTokenException(Token unexpectedToken) {
        this.unexpectedToken = unexpectedToken;
    }

    @Override
    public String getMessage() {
        return "Unexpected token: " + unexpectedToken + ". Possibly expression is incorrect";
    }
}
