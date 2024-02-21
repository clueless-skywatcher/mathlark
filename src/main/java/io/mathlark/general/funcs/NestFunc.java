package io.mathlark.general.funcs;

import java.util.List;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.numbers.funcs.IntQFunc;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.FunctionCallExpression;
import io.mathlark.parser.expressions.IExpression;
public class NestFunc implements LarkCallable {

    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        String funcName = (String) expressions[0].getVal();
        IExpression result = expressions[1];
        int nest = (int) expressions[2].getVal();

        while (nest-- > 0) {
            result = new FunctionCallExpression(funcName, List.of(result)).evaluate();
        }

        return result;
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
        return "Nest";
    }
    
}
