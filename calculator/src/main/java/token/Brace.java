package token;

import visitor.TokenVititor;

public class Brace implements Token{

    private BraceType braceType;

    public Brace(BraceType braceType) {
        this.braceType = braceType;
    }

    public void accept(TokenVititor visitor) {
        visitor.visit(this);
    }

    public BraceType getBraceType() {
        return braceType;
    }

    public enum BraceType {
        LEFT,
        RIGHT
    }


    @Override
    public String toString() {
        return braceType == BraceType.LEFT ? "LEFT" : "RIGHT";
    }
}
