package io.mathlark.dicts.funcs;

import java.util.ArrayList;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.DictExpression;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;

public class DictKeysFunc implements LarkCallable {

    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        DictExpression expression = (DictExpression) expressions[0];
        return new ListExpression(new ArrayList<>(expression.getVal().keySet()));
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 1) {
            throw new WrongParameterLengthException("Expected 1 elements, got %d elements", expressions.length);
        }
        if (!CallableUtils.isInstanceOf(expressions[0], DictExpression.class)) {
            throw new WrongParameterTypeException(DictExpression.class, 0, expressions[0].getClass());
        }
    }

    @Override
    public String getName() {
        return "DictKeys";
    }
    
}
