package io.mathlark.numbers.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.numbers.IntegerUtils;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.NumericExpression;

public class DigitSumFunc implements LarkCallable {

    @Override
    public IExpression evaluate(IExpression... expressions) {
        return IntegerUtils.digitSum((NumericExpression) expressions[0]);
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 1) {
            throw new WrongParameterLengthException("Expected 1 arguments, got %d", expressions.length);
        }

        LarkCallable intQFunc = new IntQFunc();

        if (intQFunc.evaluate(expressions[0]).getVal().equals("False")) {
            throw new WrongParameterTypeException("Expected integer in position 0");
        }
    }

    @Override
    public String getName() {
        return "DigitSum";
    }
    
}
