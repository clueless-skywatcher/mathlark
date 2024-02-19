package io.mathlark.parser.expressions;

import io.mathlark.funcs.CallableUtils;
import io.mathlark.parser.GlobalSymbolRegistry;
import lombok.Getter;

public class AccessExpression implements IExpression {
    private IExpression expr;
    private IExpression accExpr;
    private @Getter Object val;

    public AccessExpression(IExpression expr, IExpression accExpr) {
        this.expr = expr;
        this.accExpr = accExpr;
    }

    @Override
    public IExpression evaluate() {
        if (!CallableUtils.isInstanceOf(expr, ListExpression.class) 
            && !CallableUtils.isInstanceOf(expr, DictExpression.class)) {
            System.out.println("Cannot access a non-iterable expression");
            return GlobalSymbolRegistry.UNDEFINED;
        }
        if (CallableUtils.isInstanceOf(expr, DictExpression.class)) {
            return ((DictExpression) expr).getValue(accExpr);
        }
        return ((ListExpression) expr).getElementAt(accExpr);
    }

    @Override
    public Number getSize() {
        return 0;
    }    
}
