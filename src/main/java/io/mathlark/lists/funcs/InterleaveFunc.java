package io.mathlark.lists.funcs;

import java.util.ArrayList;
import java.util.List;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.CallableUtils;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;

public class InterleaveFunc implements LarkCallable {
    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }

        ListExpression list = (ListExpression) expressions[0];
        if (CallableUtils.isInstanceOf(expressions[1], ListExpression.class)) {
            return interleave(list, (ListExpression) expressions[1]);
        }

        return interleave(list, expressions[1]);
    }

    private IExpression interleave(ListExpression list, IExpression expr) {
        List<IExpression> result = new ArrayList<>();

        for (int i = 0; i < (Integer) list.getSize() - 1; i++) {
            result.add(list.getElementAt(i));
            result.add(expr);
        }

        result.add(list.getElementAt(-1));

        return new ListExpression(result);
    }

    private IExpression interleave(ListExpression list1, ListExpression list2) {
        List<IExpression> result = new ArrayList<>();

        if (list1.getSize().equals(list2.getSize())) {
            for (int i = 0; i < (Integer) list1.getSize(); i++) {
                result.add(list1.getElementAt(i));
                result.add(list2.getElementAt(i));
            }
        }
        else {
            for (int i = 0; i < (Integer) list1.getSize() - 1; i++) {
                result.add(list1.getElementAt(i));
                result.add(list2.getElementAt(i % (Integer) list2.getSize()));
            }

            result.add(list1.getElementAt(-1));
        }
        return new ListExpression(result);
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        if (expressions.length != 2) {
            throw new WrongParameterLengthException("Expected 2 elements, got %d elements", expressions.length);
        }
        if (!CallableUtils.isInstanceOf(expressions[0], ListExpression.class)) {
            throw new WrongParameterTypeException(ListExpression.class, 0, expressions[0].getClass());
        }
    }

    @Override
    public String getName() {
        return "Interleave";
    }
    
}
