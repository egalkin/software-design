package state;

import token.Brace;
import token.Operation;
import token.Tokenizer;

import java.io.IOException;

public class Start extends State{

    public Start(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void process() throws IOException {
        tokenizer.skipWhitespaces();
        if (tokenizer.curChar() == -1) {
            tokenizer.changeState(new End(tokenizer));
            return;
        }
        switch (tokenizer.curChar()) {
            case '(':
                tokenizer.nextChar();
                tokenizer.addToken(new Brace(Brace.BraceType.LEFT));
                break;
            case ')':
                tokenizer.nextChar();
                tokenizer.addToken(new Brace(Brace.BraceType.RIGHT));
                break;
            case '+':
                tokenizer.nextChar();
                tokenizer.addToken(new Operation(Operation.OperationType.ADD));
                break;
            case '-':
                tokenizer.nextChar();
                tokenizer.addToken(new Operation(Operation.OperationType.SUB));
                break;
            case '*':
                tokenizer.nextChar();
                tokenizer.addToken(new Operation(Operation.OperationType.MUL));
                break;
            case '/':
                tokenizer.nextChar();
                tokenizer.addToken(new Operation(Operation.OperationType.DIV));
                break;
            default:
                if (Character.isDigit(tokenizer.curChar()))
                    tokenizer.changeState(new Number(tokenizer));
                else
                    tokenizer.changeState(new Error(tokenizer));
        }

    }
}
