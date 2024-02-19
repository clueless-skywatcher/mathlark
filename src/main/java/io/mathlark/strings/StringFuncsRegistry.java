package io.mathlark.strings;

import io.mathlark.funcs.FunctionRegistryContract;

public class StringFuncsRegistry extends FunctionRegistryContract {
    public StringFuncsRegistry() {
        register(StringLengthFunc.class);
    }
}
