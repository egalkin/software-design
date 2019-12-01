package visitor;

import token.Brace;
import token.NumberToken;
import token.Operation;
import visitor.exception.IncorrectRpnExpression;

import java.util.ArrayDeque;
import java.util.Deque;

public class CalcVisitor implements TokenVititor {

    private Deque<Integer> calculatorState = new ArrayDeque<>();


    public int getResult() {
        assert  calculatorState.size() == 1 : "Empty result";
        return calculatorState.peek();
    }

    @Override
    public void visit(NumberToken token) {
        calculatorState.push(token.getValue());
    }

    @Override
    public void visit(Brace token) {

    }

    @Override
    public void visit(Operation token) {
        if (calculatorState.size() < 2) {
            throw new IncorrectRpnExpression();
        } else {
            int num2 = calculatorState.pop();
            int num1 = calculatorState.pop();
            switch (token.getOperationType()) {
                case ADD:
                    calculatorState.push(num1 + num2);
                    break;
                case SUB:
                    calculatorState.push(num1 - num2);
                    break;
                case MUL:
                    calculatorState.push(num1 * num2);
                    break;
                case DIV:
                    calculatorState.push(num1 / num2);
                    break;
            }
        }
    }
}
