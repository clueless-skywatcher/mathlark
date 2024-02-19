package io.mathlark.dicts;

import io.mathlark.dicts.funcs.DictFunc;
import io.mathlark.dicts.funcs.DictKeysFunc;
import io.mathlark.dicts.funcs.DictValFunc;
import io.mathlark.dicts.funcs.DictValsFunc;
import io.mathlark.funcs.FunctionRegistryContract;

public class DictFuncsRegistry extends FunctionRegistryContract {
    public DictFuncsRegistry() {
        register(DictFunc.class);
        register(DictValFunc.class);
        register(DictKeysFunc.class);
        register(DictValsFunc.class);
    }
}
