package io.mathlark.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.parser.expressions.IExpression;

public interface LarkCallable {
    public IExpression evaluate(IExpression... expressions);

    void checkArgs(IExpression... expressions) throws WrongParameterLengthException, WrongParameterTypeException;

    public String getName();
}
