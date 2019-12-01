package state.exception;

public class InvalidSymbolException extends Exception {

    private int position;
    private int symbol;


    public InvalidSymbolException(int position, int symbol) {
        super();
        this.position = position;
        this.symbol = symbol;
    }

    @Override
    public String getMessage() {
        return "Unexpected symbol '" + (char) symbol + "' at position: " + position ;
    }
}
