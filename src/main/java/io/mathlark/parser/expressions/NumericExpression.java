package io.mathlark.parser.expressions;

import lombok.Getter;

public class NumericExpression implements IExpression {
    public @Getter Number val;

    public NumericExpression(Number val) {
        this.val = val;
    }

    @Override
    public IExpression evaluate() {
        return this;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    public boolean equals(Object other) {
        if (other instanceof NumericExpression) {
            return this.val.equals(((NumericExpression) other).val);
        }
        if (other instanceof Number) {
            return this.val.equals((Number) other);
        }
        if (other instanceof IExpression) {
            return this.val.equals(((IExpression) other).evaluate().getVal());
        }

        return false;
    }

    @Override
    public Number getSize() {
        return 0;
    }

    public int hashCode() {
        return this.val.hashCode();
    }
}
