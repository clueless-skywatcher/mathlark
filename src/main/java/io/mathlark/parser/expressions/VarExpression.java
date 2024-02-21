package io.mathlark.parser.expressions;

import lombok.Getter;

public class VarExpression implements IExpression {
    private @Getter String symbol;

    public VarExpression(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public IExpression evaluate() {
        return this;
    }

    @Override
    public Object getVal() {
        return this.symbol;
    }

    public String toString() {
        return this.symbol;
    }

    @Override
    public Number getSize() {
        return 0;
    }

    public boolean equals(Object other) {
        if (other instanceof VarExpression) {
            return this.symbol.equals(((VarExpression) other).evaluate().getVal());
        }
        if (other instanceof String) {
            return this.symbol.equals((String) other);
        }
        
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
