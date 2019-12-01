package token;

import visitor.TokenVititor;

public class NumberToken implements Token {
    private int value;

    public NumberToken(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void accept(TokenVititor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "NUMBER(" + value + ")";
    }
}
