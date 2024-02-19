package io.mathlark.funcs;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FunctionRegistryContract {
    protected static FunctionRegistryContract INSTANCE = new FunctionRegistryContract();
    public Map<String, Class<? extends LarkCallable>> functions = new HashMap<>();

    public void register(Class<? extends LarkCallable> class1) {
        try {
            functions.put(class1.getDeclaredConstructor().newInstance().getName(), class1);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            System.out.println("Failed to register function: " + class1.getSimpleName());
        }
    }
}
