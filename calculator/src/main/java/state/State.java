package state;

import state.exception.InvalidSymbolException;
import token.Tokenizer;

import java.io.IOException;

public abstract class State {
    protected Tokenizer tokenizer;

    public State(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public abstract void process() throws IOException, InvalidSymbolException;
}
