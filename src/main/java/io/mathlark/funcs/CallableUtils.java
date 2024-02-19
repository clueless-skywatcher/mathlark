package io.mathlark.funcs;

import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;

public class CallableUtils {
    public static boolean containsUndefined(IExpression... expressions) {
        for (IExpression expression : expressions) {
            if (expression.evaluate().equals(GlobalSymbolRegistry.UNDEFINED) | expression.equals(GlobalSymbolRegistry.UNDEFINED)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInstanceOf(IExpression expr, Class<? extends IExpression> exprClass) {
        return exprClass.isInstance(expr) || exprClass.isInstance(expr.evaluate()); 
    }
}
