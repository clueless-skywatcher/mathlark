package io.mathlark.general.funcs;

import java.util.List;
import java.util.ArrayList;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.AllFunctionRegistry;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.FunctionCallExpression;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;
import io.mathlark.parser.expressions.StringExpression;

public class MapFunc implements LarkCallable {

    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        ListExpression expr = (ListExpression) expressions[1];
        String funcName = ((StringExpression) expressions[0]).getVal();

        List<IExpression> results = new ArrayList<>();
        
        for (int i = 0; i < (Integer) expr.getSize(); i++) {
            if (!CallableUtils.isInstanceOf(expr.getElementAt(i), ListExpression.class)) {
                results.add(new FunctionCallExpression(funcName, List.of(expr.getElementAt(i))));
            }
            else {
                ListExpression listExpr = (ListExpression) expr.getElementAt(i).evaluate();
                results.add(new FunctionCallExpression(funcName, listExpr.getVal()));
            }
        }

        for (int i = 0; i < results.size(); i++) {
            if (AllFunctionRegistry.isFunc(funcName)) {
                results.set(i, results.get(i));
            }
        }

        return new ListExpression(results);
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 2){
            throw new WrongParameterLengthException("Expected 2 elements, got %d elements", expressions.length);
        }
        if (!CallableUtils.isInstanceOf(expressions[1], ListExpression.class)) {
            throw new WrongParameterTypeException(ListExpression.class, 1, expressions[1].getClass());
        }
    }

    @Override
    public String getName() {
        return "Map";
    }
    
}
