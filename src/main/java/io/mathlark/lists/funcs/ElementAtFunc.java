package io.mathlark.lists.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;
import io.mathlark.parser.expressions.NumericExpression;

/**
 * ElementAt(List l, Numeric n)
 * -----------------
 * Internal Java Class Name: ElementAtFunc
 * 
 * Access the n-th element of list l. (0-indexed elements)
 * Same as using l{n}
 * 
 * Examples:
 * >> a = List(1, 2, 3)
 * [1, 2, 3]
 * >> ElementAt(a, 0)
 * 1
 * >> ElementAt(List(a, 2.5, False), 2)
 * False
 */
public class ElementAtFunc implements LarkCallable {
    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        }
        catch (Exception e) {
            System.out.println("Invalid function call: " + e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        ListExpression expr = (ListExpression) expressions[0].evaluate();
        NumericExpression pos = (NumericExpression) expressions[1].evaluate();

        return expr.getElementAt(pos);
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 2) {
            throw new WrongParameterLengthException("Expected 2 elements, got %d elements", expressions.length);
        }
        if (!CallableUtils.isInstanceOf(expressions[0], ListExpression.class)) {
            throw new WrongParameterTypeException("Expected ListExpression at position 0, got %s", expressions[0].getClass().getName());
        }
        if (!CallableUtils.isInstanceOf(expressions[1], NumericExpression.class)) {
            throw new WrongParameterTypeException("Expected NumericExpression at position 1, got %s", expressions[0].getClass().getName());
        }
    }

    @Override
    public String getName() {
        return "ElementAt";
    }
    
}
