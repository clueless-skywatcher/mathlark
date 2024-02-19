package io.mathlark.numbers;

import io.mathlark.funcs.FunctionRegistryContract;
import io.mathlark.numbers.funcs.EvenQFunc;
import io.mathlark.numbers.funcs.IntQFunc;
import io.mathlark.numbers.funcs.NumQFunc;

public class NumberFuncsRegistry extends FunctionRegistryContract  {
    public NumberFuncsRegistry() {
        register(EvenQFunc.class);
        register(NumQFunc.class);
        register(IntQFunc.class);
    }
}
