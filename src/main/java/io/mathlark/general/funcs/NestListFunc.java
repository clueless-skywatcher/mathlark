package io.mathlark.general.funcs;

import java.util.List;
import java.util.ArrayList;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.numbers.funcs.IntQFunc;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;
import io.mathlark.parser.expressions.NumericExpression;
import io.mathlark.parser.expressions.StringExpression;

public class NestListFunc implements LarkCallable {

    @Override
    public IExpression evaluate(IExpression... expressions) {
        List<IExpression> result = new ArrayList<>();

        StringExpression funcName = (StringExpression) expressions[0];
        int nest = (int) expressions[2].getVal();
        IExpression input = expressions[1];

        LarkCallable nestFunc = new NestFunc();

        for (int i = 0; i < nest + 1; i++) {
            result.add(nestFunc.evaluate(funcName, input, new NumericExpression(i)));
        }

        return new ListExpression(result);
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 3) {
            throw new WrongParameterLengthException("Expected 3 arguments, got %d", expressions.length);
        }

        LarkCallable intQFunc = new IntQFunc();

        if (intQFunc.evaluate(expressions[1]).getVal().equals("False")) {
            throw new WrongParameterTypeException("Expected integer in position 1");
        }
    }

    @Override
    public String getName() {
        return "NestList";
    }
    
}
