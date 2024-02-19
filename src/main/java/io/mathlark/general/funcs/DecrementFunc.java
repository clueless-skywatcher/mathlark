package io.mathlark.general.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.NumericExpression;

/**
 * Decrement(Number or Expression evaluating to a Number)
 * -----------------
 * Internal Java Class Name: DecrementFunc
 * 
 * Decrements a given number by 1
 * 
 * Examples:
 * >> a = Decrement(1)
 * >> a
 * 0
 * >> a = Decrement(Decrement(1))
 * >> a
 * -1
 * >> a = Decrement(a)
 * -2
 */
public class DecrementFunc implements LarkCallable {
    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        }
        catch (Exception e) {
            System.out.println("Invalid function call: " + e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        NumericExpression num = (NumericExpression) expressions[0].evaluate();
        if (num.val instanceof Integer) {
            return new NumericExpression(num.val.intValue() - 1);
        }
        else {
            return new NumericExpression(num.val.doubleValue() - 1);
        }
    }

    @Override
    public void checkArgs(IExpression... expressions) throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 1) {
            throw new WrongParameterLengthException("Expected 1 element, got %d elements", expressions.length);
        }
        if (CallableUtils.containsUndefined(expressions)) {
            throw new WrongParameterTypeException("Undefined expression present in arguments");
        }
        if (!(expressions[0] instanceof NumericExpression)) {
            throw new WrongParameterTypeException("Expected NumericExpression at position 0, got %d", expressions[0].getClass().getName());
        }
    }

    @Override
    public String getName() {
        return "Decrement";
    }    
    
}
