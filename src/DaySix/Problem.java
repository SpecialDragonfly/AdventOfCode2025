package DaySix;

import java.math.BigInteger;
import java.util.Vector;

public class Problem {
    Vector<Column> columns = new Vector<>();
    String operand = "";

    public void addColumn(Column col) {
        this.columns.add(col);
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public BigInteger calculate() {
        if (this.operand.equals("*")) {
            return this.multiply();
        } else {
            return this.add();
        }
    }

    private BigInteger add() {
        return columns.stream().map(Column::value).reduce(BigInteger.ZERO, BigInteger::add);
    }

    private BigInteger multiply() {
        return columns.stream().map(Column::value).reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public String toString() {
        return columns + "Operand: " + operand;
    }
}
