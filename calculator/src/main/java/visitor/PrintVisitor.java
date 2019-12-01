package visitor;

import sun.reflect.generics.visitor.Visitor;
import token.Brace;
import token.NumberToken;
import token.Operation;

public class PrintVisitor implements TokenVititor {
    @Override
    public void visit(NumberToken token) {
        System.out.print(token + " ");
    }

    @Override
    public void visit(Brace token) {
        System.out.print(token + " ");
    }

    @Override
    public void visit(Operation token) {
        System.out.print(token + " ");
    }
}
