package io.mathlark.numbers.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.BooleanExpression;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.NumericExpression;

public class EvenQFunc implements LarkCallable {

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
            Number val = ((NumericExpression) expressions[0]).getVal();
            if (val instanceof Integer) {
                return new BooleanExpression((Integer) val % 2 == 0);
            }
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
        return "EvenQ";
    }
    
}
