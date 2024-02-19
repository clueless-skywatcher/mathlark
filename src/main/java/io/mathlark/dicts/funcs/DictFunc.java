package io.mathlark.dicts.funcs;

import java.util.Map;

import java.util.HashMap;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;
import io.mathlark.parser.expressions.DictExpression;

/**
 * Dict()
 */
public class DictFunc implements LarkCallable {
    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        } 

        Map<IExpression, IExpression> map = new HashMap<>();
        
        for (int i = 0; i < expressions.length; i++) {
            ListExpression entry = (ListExpression) expressions[i];
            IExpression key = entry.getElementAt(0).evaluate();
            IExpression value = entry.getElementAt(1).evaluate();
            map.put(key, value);
        }

        return new DictExpression(map);
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        for (int i = 0; i < expressions.length; i++) {
            if (!CallableUtils.isInstanceOf(expressions[i], ListExpression.class)) {
                throw new WrongParameterTypeException(ListExpression.class, i, expressions[i].getClass());
            }
            ListExpression expression = (ListExpression) expressions[i].evaluate();
            if (expression.getVal().size() != 2) {
                throw new WrongParameterLengthException("Expected 2 elements at position %d, got %d elements", i, expression.getVal().size());
            }
        }
    }

    @Override
    public String getName() {
        return "Dict";
    }
    
}
