package state;

import state.exception.InvalidSymbolException;
import token.Tokenizer;

import java.io.IOException;

public class Error extends State {

    public Error(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void process() throws IOException, InvalidSymbolException {
        throw new InvalidSymbolException(tokenizer.curPos(), tokenizer.curChar());
    }
}
