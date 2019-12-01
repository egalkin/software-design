package visitor;

import token.Brace;
import token.NumberToken;
import token.Operation;
import token.Token;
import visitor.exception.MissedLeftBraceException;
import visitor.exception.UnexpectedTokenException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ParserVisitor implements TokenVititor {
    private Deque<Token> unusedTokens;
    private List<Token> rpn;

    public ParserVisitor() {
        unusedTokens = new ArrayDeque<>();
        rpn = new ArrayList<>();
    }

    public void finalizeData() {
        while (unusedTokens.size() != 0) {
            if (unusedTokens.peek() instanceof Operation)
                rpn.add(unusedTokens.pop());
            else
                throw new UnexpectedTokenException(unusedTokens.pop());
        }
    }

    public List<Token> getRpn() {
        return rpn;
    }

    @Override
    public void visit(NumberToken token) {
        rpn.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token.getBraceType() == Brace.BraceType.LEFT)
            unusedTokens.push(token);
        else {
            boolean meetLeftBracket = false;
            while (unusedTokens.size() != 0 && !meetLeftBracket) {
                Token curToken = unusedTokens.pop();
                if (curToken instanceof Brace
                        && ((Brace) curToken).getBraceType() == Brace.BraceType.LEFT)
                    meetLeftBracket = true;
                else
                    rpn.add(curToken);
            }
            if (unusedTokens.size() == 0 && !meetLeftBracket) {
                throw new MissedLeftBraceException();
            }
        }
    }

    @Override
    public void visit(Operation token) {
        boolean finishedUpWithOperation = false;
        while (unusedTokens.size() != 0 && !finishedUpWithOperation) {
            Token curToken = unusedTokens.peek();
            if (curToken instanceof Operation) {
                if (token.compareTo((Operation) curToken) <= 0)
                    rpn.add(unusedTokens.pop());
                else
                    finishedUpWithOperation = true;
            } else {
                finishedUpWithOperation = true;
            }
        }
        unusedTokens.push(token);
    }
}
