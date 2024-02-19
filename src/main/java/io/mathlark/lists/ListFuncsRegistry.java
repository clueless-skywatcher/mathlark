package io.mathlark.lists;

import io.mathlark.funcs.FunctionRegistryContract;
import io.mathlark.lists.funcs.ElementAtFunc;
import io.mathlark.lists.funcs.FirstFunc;
import io.mathlark.lists.funcs.InterleaveFunc;
import io.mathlark.lists.funcs.LastFunc;
import io.mathlark.lists.funcs.ListFunc;

public class ListFuncsRegistry extends FunctionRegistryContract {
    public ListFuncsRegistry() {
        register(ListFunc.class);
        register(ElementAtFunc.class);
        register(FirstFunc.class);
        register(LastFunc.class);
        register(InterleaveFunc.class);
    }
}
