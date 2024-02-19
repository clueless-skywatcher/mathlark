package io.mathlark.numbers.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.BooleanExpression;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.NumericExpression;

public class NumQFunc implements LarkCallable {

    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        if (CallableUtils.isInstanceOf(expressions[0], NumericExpression.class)) {
            return new BooleanExpression(true);
        }

        return new BooleanExpression(false);
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 1) {
            throw new WrongParameterLengthException("Expected 1 element, got %d elements", expressions.length);
        }
    }

    @Override
    public String getName() {
        return "NumQ";
    }
    
}
