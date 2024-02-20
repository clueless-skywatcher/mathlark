package io.mathlark.parser;

import java.util.HashMap;
import java.util.Map;

import io.mathlark.parser.expressions.FunctionDefExpression;
import io.mathlark.parser.expressions.IExpression;

public class SymbolTables {
    private static SymbolTables INSTANCE = new SymbolTables();

    private Map<String, IExpression> localSymbols;
    private Map<String, IExpression> globalSymbols;
    private Map<String, FunctionDefExpression> localFunctions;
    
    private SymbolTables() {
        localSymbols = new HashMap<>();
        globalSymbols = GlobalSymbolRegistry.SYMBOLS;
        localFunctions = new HashMap<>();
    }
    
    public static IExpression eval(String symbol) {
        if (!INSTANCE.localSymbols.containsKey(symbol)) {
            return GlobalSymbolRegistry.UNDEFINED;
        }
        return INSTANCE.localSymbols.get(symbol);
    }

    public static IExpression evalGlobal(String symbol) {
        return INSTANCE.globalSymbols.get(symbol);
    }

    public static boolean isGlobal(String symbol) {
        return INSTANCE.globalSymbols.containsKey(symbol);
    }

    public static void addLocal(String symVal, IExpression expr) {
        INSTANCE.localSymbols.put(symVal, expr);
    }

    public static void addLocalFunc(String funcName, FunctionDefExpression expr) {
        INSTANCE.localFunctions.put(funcName, expr);
    }

    public static boolean isLocalFunc(String symbol) {
        return INSTANCE.localFunctions.containsKey(symbol);
    }

    public static void init() {
        
    }
}
