package io.mathlark.parser.expressions;

import io.mathlark.funcs.AllFunctionRegistry;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.SymbolTables;
import lombok.Getter;

public class FunctionDefExpression implements IExpression {
    public @Getter String funcName;

    public FunctionDefExpression(String funcName) {
        this.funcName = funcName;
    }

    public FunctionDefExpression(StringExpression funcName) {
        this.funcName = funcName.getVal();
    }

    @Override
    public IExpression evaluate() {
        if (AllFunctionRegistry.isFunc(funcName)) {
            System.out.println(String.format("Cannot redefine inbuilt function \"%s\"", funcName));
            return GlobalSymbolRegistry.UNDEFINED;
        }
        SymbolTables.addLocalFunc(funcName, this);
        return new StringExpression((String) getVal());
    }

    @Override
    public Object getVal() {
        return String.format("Defined function %s", this.funcName);
    }

    @Override
    public Number getSize() {
        return 0;
    }
    
}
