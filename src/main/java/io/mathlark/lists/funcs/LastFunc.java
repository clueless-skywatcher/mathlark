package io.mathlark.lists.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;
import io.mathlark.parser.expressions.NumericExpression;

public class LastFunc implements LarkCallable {

    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        ListExpression expr = (ListExpression) expressions[0].evaluate();
        return expr.getElementAt(new NumericExpression(expr.getVal().size() - 1));
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 1) {
            throw new WrongParameterLengthException("Expected 1 element, got %d elements", expressions.length);
        }

        if (!CallableUtils.isInstanceOf(expressions[0], ListExpression.class)) {
            throw new WrongParameterTypeException(ListExpression.class, 0, expressions[0].getClass());
        }
    }

    @Override
    public String getName() {
        return "Last";
    }

}
