package state;

import state.exception.InvalidSymbolException;
import token.Tokenizer;

import java.io.IOException;

public class End extends State {
    public End(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void process() throws IOException, InvalidSymbolException {
        tokenizer.setFinishParsing(true);
    }
}
