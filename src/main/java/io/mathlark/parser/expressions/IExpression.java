package io.mathlark.parser.expressions;

public interface IExpression {
    public IExpression evaluate();
    public String toString();
    public Object getVal();
    public Number getSize();
}
