package io.mathlark.funcs;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.mathlark.dicts.DictFuncsRegistry;
import io.mathlark.general.GeneralFuncsRegistry;
import io.mathlark.lists.ListFuncsRegistry;
import io.mathlark.numbers.NumberFuncsRegistry;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.strings.StringFuncsRegistry;

public class AllFunctionRegistry {
    private static AllFunctionRegistry INSTANCE = new AllFunctionRegistry();
    private Map<String, Class<? extends LarkCallable>> functions = new HashMap<>();

    private AllFunctionRegistry() {
        register(new GeneralFuncsRegistry());
        register(new ListFuncsRegistry());
        register(new DictFuncsRegistry());
        register(new NumberFuncsRegistry());
        register(new StringFuncsRegistry());
    }

    public static IExpression invoke(String funcName, List<IExpression> exprs) {
        try {
            LarkCallable func = INSTANCE.functions.get(funcName).getDeclaredConstructor().newInstance();
            return func.evaluate(exprs.toArray(new IExpression[0]));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            return GlobalSymbolRegistry.UNDEFINED;
        }
    }

    public static boolean isFunc(String name) {
        return INSTANCE.functions.containsKey(name);
    }

    public void register(FunctionRegistryContract funcRegistry) {
        functions.putAll(funcRegistry.functions);
    }
}
