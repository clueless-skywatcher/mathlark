package io.mathlark.dicts.funcs;

import java.util.List;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.DictExpression;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.NumericExpression;
import io.mathlark.parser.expressions.StringExpression;

public class DictValFunc implements LarkCallable {

    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        DictExpression expression = (DictExpression) expressions[0];
        IExpression key = expressions[1];
        return expression.getValue(key);
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 2) {
            throw new WrongParameterLengthException("Expected 2 elements, got %d elements", expressions.length);
        }
        if (!CallableUtils.isInstanceOf(expressions[0], DictExpression.class)) {
            throw new WrongParameterTypeException(DictExpression.class, 0, expressions[0].getClass());
        }
        if (!(CallableUtils.isInstanceOf(expressions[1], StringExpression.class) ||
            CallableUtils.isInstanceOf(expressions[1], NumericExpression.class))) {
            throw new WrongParameterTypeException(List.of(NumericExpression.class, StringExpression.class), 1, expressions[1].getClass());
        }
    }

    @Override
    public String getName() {
        return "DictVal";
    }
}
