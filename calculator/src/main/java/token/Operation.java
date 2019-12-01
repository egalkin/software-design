package token;

import visitor.TokenVititor;

import java.util.HashMap;

public class Operation implements Token, Comparable<Operation>{

    private static HashMap<OperationType, Integer> operationPriority = new HashMap<>();

    static {
        operationPriority.put(OperationType.ADD, 1);
        operationPriority.put(OperationType.SUB, 1);
        operationPriority.put(OperationType.MUL, 2);
        operationPriority.put(OperationType.DIV, 2);
    }

    private OperationType operationType;

    public Operation(OperationType operationType) {
        this.operationType = operationType;
    }

    public void accept(TokenVititor visitor) {
        visitor.visit(this);
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public enum OperationType {
        ADD,
        SUB,
        MUL,
        DIV
    }

    @Override
    public String toString() {
        return operationType.toString();
    }

    @Override
    public int compareTo(Operation o) {
        return operationPriority.get(this.operationType) - operationPriority.get(o.operationType);
    }
}
