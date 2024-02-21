package io.mathlark.general;

import io.mathlark.funcs.FunctionRegistryContract;
import io.mathlark.general.funcs.*;

public class GeneralFuncsRegistry extends FunctionRegistryContract {
    public GeneralFuncsRegistry() {
        register(IncrementFunc.class);
        register(DecrementFunc.class);
        register(ExitFunc.class);
        register(LengthFunc.class);
        register(MapFunc.class);
        register(FuncDefFunc.class);
        register(NestFunc.class);
        register(NestListFunc.class);
    }
}
