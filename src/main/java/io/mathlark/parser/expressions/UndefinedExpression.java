package io.mathlark.parser.expressions;

public class UndefinedExpression implements IExpression {
    
    @Override
    public IExpression evaluate() {
        return new StringExpression("Undefined");
    }

    @Override
    public Object getVal() {
        return new StringExpression("Undefined");
    }

    @Override
    public Number getSize() {
        return 0;
    }
}
