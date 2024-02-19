package io.mathlark.parser.expressions;

import lombok.Getter;

public class StringExpression implements IExpression {
    public @Getter String val;

    public StringExpression(String val) {
        this.val = val;
    }

    @Override
    public IExpression evaluate() {
        return this;
    }
    
    @Override
    public String toString() {
        return val;
    }

    public boolean equals(Object other) {
        if (other instanceof IExpression) {
            return this.val.equals(((StringExpression) other).evaluate().getVal());
        }
        if (other instanceof String) {
            return this.val.equals((String) other);
        }
        
        return false;
    }

    public int hashCode() {
        return this.val.hashCode();
    }

    @Override
    public Number getSize() {
        return 0;
    }
}
