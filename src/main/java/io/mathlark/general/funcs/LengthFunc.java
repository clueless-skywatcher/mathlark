package io.mathlark.general.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.NumericExpression;

/**
 * Length(Expression)
 * -----------------
 * Internal Java Class Name: LengthFunc
 * 
 * Retrieves the length of the expression.
 * - For integers, strings, booleans or any atomic
 *   expressions, the value is 0
 * - For iterable expressions, the value is the number
 *   of elements in the expression
 * - Strings are considered atomic. To find the number of
 *   characters in a string use StrLength instead.
 * 
 * Examples:
 * >> Length(17)
 * 0
 * >> Length("abc")
 * 0
 * >> Length(True)
 * 0
 * >> Length([1, 2, 3])
 * 3
 * >> Length({a: 1, b: 2, c: 3})
 * 3
 */
public class LengthFunc implements LarkCallable {
    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }
        return new NumericExpression(expressions[0].getSize());
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
        return "Length";
    }
    
}
