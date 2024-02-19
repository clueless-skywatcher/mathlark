package io.mathlark.parser.expressions;

import java.util.ArrayList;
import java.util.List;

import io.mathlark.parser.GlobalSymbolRegistry;
import lombok.Getter;

public class ListExpression implements IExpression {
    public @Getter List<IExpression> val;
    
    public ListExpression(List<IExpression> val) {
        this.val = val;
    }

    @Override
    public IExpression evaluate() {
        List<IExpression> evalExpressions = new ArrayList<>();
        for (IExpression expr : val) {
            evalExpressions.add(expr.evaluate());
        }
        this.val = evalExpressions;
        return new ListExpression(this.val);
    }

    public String toString() {
        return val.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof ListExpression) {
            List<IExpression> otherList = ((ListExpression) other).getVal();
            return this.val.equals(otherList);
        }
        else if (other instanceof List) {
            return this.val.equals(other);
        }
        return false;
    }

    public IExpression getElementAt(IExpression expr) {
        try {
            if (expr instanceof NumericExpression) {
                if (expr.getVal() instanceof Integer) {
                    int idx = (int) expr.getVal();
                    if (idx < 0) {
                        return this.val.get(this.val.size() + idx);
                    }
                    return this.val.get(idx);
                }
            }
            else if (expr.evaluate() instanceof NumericExpression) {
                IExpression eval = expr.evaluate();
                if (eval.getVal() instanceof Integer) {
                    int idx = (int) expr.getVal();
                    if (idx < 0) {
                        return this.val.get(this.val.size() + idx);
                    }
                    return this.val.get(idx);
                }
            }
        } catch(IndexOutOfBoundsException e) {
            System.out.println(String.format("Invalid index %s for list of length %d", expr.toString(), val.size()));
        }
        return GlobalSymbolRegistry.UNDEFINED;
    }

    public IExpression getElementAt(int num) {
        return getElementAt(new NumericExpression(num));
    }

    @Override
    public Number getSize() {
        return this.val.size();
    }
}
