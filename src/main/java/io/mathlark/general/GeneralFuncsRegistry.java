package io.mathlark.general;

import io.mathlark.funcs.FunctionRegistryContract;
import io.mathlark.general.funcs.DecrementFunc;
import io.mathlark.general.funcs.ExitFunc;
import io.mathlark.general.funcs.IncrementFunc;
import io.mathlark.general.funcs.LengthFunc;
import io.mathlark.general.funcs.MapFunc;

public class GeneralFuncsRegistry extends FunctionRegistryContract {
    public GeneralFuncsRegistry() {
        register(IncrementFunc.class);
        register(DecrementFunc.class);
        register(ExitFunc.class);
        register(LengthFunc.class);
        register(MapFunc.class);
    }
}
