package io.mathlark.parser.expressions;

import lombok.Getter;

public class BooleanExpression implements IExpression {
    public @Getter Boolean val;

    public BooleanExpression(boolean val) {
        this.val = val;
    }

    public BooleanExpression(String val) {
        if (val.equals("True")) this.val = true;
        else if (val.equals("False")) this.val = false;
        else this.val = null;
    }

    @Override
    public IExpression evaluate() {
        return new StringExpression(toString());
    }

    public String toString() {
        return this.val ? "True" : "False";
    }

    public boolean equals(Object other) {
        if (other instanceof IExpression) {
            return this.val == ((IExpression) other).evaluate().getVal();
        }
        if (other instanceof Boolean) {
            return this.val == other;
        }

        return false;
    }

    @Override
    public Number getSize() {
        return 0;
    }

    
}
