package token;

import state.Start;
import state.State;
import state.exception.InvalidSymbolException;
import visitor.CalcVisitor;
import visitor.ParserVisitor;
import visitor.PrintVisitor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private InputStream inputStream;
    private int curChar = ' ';
    private int curPos = 0;
    private List<Token> tokens;
    private boolean finishParsing;
    private State state;

    public static void main(String... args) throws Exception{
//        (23 + 10) * 5 - 3 * (32 + 5) * (10 - 4 * 5) + 8 / 2
        Tokenizer tokenizer = new Tokenizer(new ByteArrayInputStream("()".getBytes()));
        List<Token> tokens = tokenizer.tokenize();
        ParserVisitor parserVisitor = new ParserVisitor();
        PrintVisitor printVisitor = new PrintVisitor();
        CalcVisitor calcVisitor = new CalcVisitor();
        for (Token token : tokens)
            token.accept(parserVisitor);
        parserVisitor.finalizeData();
        for (Token token : parserVisitor.getRpn()) {
            token.accept(printVisitor);
        }
        for (Token token : parserVisitor.getRpn()) {
            token.accept(calcVisitor);
        }
        System.out.println();
        System.out.println(calcVisitor.getResult());

    }

    public Tokenizer(InputStream inputStream) {
        this.inputStream = inputStream;
        state = new Start(this);
        this.tokens = new ArrayList<>();
        this.finishParsing = false;
    }


    public void nextChar() throws IOException {
        curPos += 1;
        curChar = inputStream.read();
    }

    public int curChar() {
        return curChar;
    }

    public int curPos() {
        return curPos;
    }


    public void addToken(Token token) {
        tokens.add(token);
    }

    public void skipWhitespaces() throws IOException{
        while (Character.isWhitespace(curChar))
            nextChar();
    }

    public void setFinishParsing(boolean finishParsing) {
        this.finishParsing = finishParsing;
    }

    public void changeState(State newState) {
        this.state = newState;
    }

    public List<Token> tokenize() throws IOException, InvalidSymbolException {
       nextChar();
       while (!finishParsing) {
           state.process();
       }
       return tokens;
    }

}
