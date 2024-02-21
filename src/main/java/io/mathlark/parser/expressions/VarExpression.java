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

    public int hashCode() {
        return toString().hashCode();
    }
}
