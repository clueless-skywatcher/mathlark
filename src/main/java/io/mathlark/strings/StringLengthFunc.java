package io.mathlark.strings;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;
import io.mathlark.parser.expressions.NumericExpression;
import io.mathlark.parser.expressions.StringExpression;

public class StringLengthFunc implements LarkCallable {

    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        StringExpression str = (StringExpression) expressions[0];
        return new NumericExpression(str.getVal().length());
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 1) {
            throw new WrongParameterLengthException("Expected 1 element, got %d elements", expressions.length);
        }

        if (!CallableUtils.isInstanceOf(expressions[0], StringExpression.class)) {
            throw new WrongParameterTypeException(StringExpression.class, 0, expressions[0].getClass());
        }
    }

    @Override
    public String getName() {
        return "StringLength";
    }
    
}
