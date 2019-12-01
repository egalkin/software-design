package state;

import token.NumberToken;
import token.Tokenizer;

import java.io.IOException;
import java.util.StringJoiner;

public class Number extends State {
    public Number(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void process() throws IOException {
        tokenizer.skipWhitespaces();
        StringJoiner number = new StringJoiner("");
        while (Character.isDigit(tokenizer.curChar())) {
            number.add((char)tokenizer.curChar() + "");
            tokenizer.nextChar();
        }
        tokenizer.addToken(new NumberToken(Integer.parseInt(number.toString())));
        tokenizer.changeState(new Start(tokenizer));
    }
}
