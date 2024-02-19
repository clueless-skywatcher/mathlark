package io.mathlark.parser;

import java.util.HashMap;
import java.util.Map;

import io.mathlark.parser.expressions.BooleanExpression;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.NumericExpression;
import io.mathlark.parser.expressions.UndefinedExpression;

public class GlobalSymbolRegistry {
    public static final UndefinedExpression UNDEFINED = new UndefinedExpression();
    public static final NumericExpression PI = new NumericExpression(Math.PI);
    public static final NumericExpression E = new NumericExpression(Math.E);
    public static final BooleanExpression TRUE = new BooleanExpression(true);
    public static final BooleanExpression FALSE = new BooleanExpression(false);

    public static final Map<String, IExpression> SYMBOLS = new HashMap<>();

    static {
        SYMBOLS.put("Undefined", UNDEFINED);
        SYMBOLS.put("Pi", PI);
        SYMBOLS.put("E", E);
        SYMBOLS.put("True", TRUE);
        SYMBOLS.put("False", FALSE);
    }
}
