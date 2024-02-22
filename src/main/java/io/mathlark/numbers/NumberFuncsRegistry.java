package io.mathlark.numbers;

import io.mathlark.funcs.FunctionRegistryContract;
import io.mathlark.numbers.funcs.DigitListFunc;
import io.mathlark.numbers.funcs.DigitSumFunc;
import io.mathlark.numbers.funcs.EvenQFunc;
import io.mathlark.numbers.funcs.IntQFunc;
import io.mathlark.numbers.funcs.NumQFunc;

public class NumberFuncsRegistry extends FunctionRegistryContract  {
    public NumberFuncsRegistry() {
        register(EvenQFunc.class);
        register(NumQFunc.class);
        register(IntQFunc.class);
        register(DigitListFunc.class);
        register(DigitSumFunc.class);
    }
}
