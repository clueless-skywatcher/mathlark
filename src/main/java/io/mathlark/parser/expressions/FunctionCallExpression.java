package io.mathlark.parser.expressions;

import java.util.ArrayList;
import java.util.List;

import io.mathlark.funcs.AllFunctionRegistry;
import lombok.Getter;

public class FunctionCallExpression implements IExpression {
    private String funcName;
    private List<IExpression> params;
    private @Getter Object val;

    public FunctionCallExpression(String funcName, List<IExpression> params) {
        this.funcName = funcName;
        this.params = params;
    }

    @Override
    public IExpression evaluate() {
        List<IExpression> evalParams = new ArrayList<>();
        for (IExpression param: params) {
            evalParams.add(param.evaluate());
        }
        IExpression invocation = AllFunctionRegistry.invoke(funcName, evalParams).evaluate();
        this.val = invocation.getVal();
        return invocation;
    }

    public boolean equals(Object other) {
        IExpression val = this.evaluate();
        if (other instanceof IExpression) {
            return val.equals(((IExpression) other).evaluate());
        }
        return val.getVal().equals(other);
    }

    @Override
    public Number getSize() {
        return 0;
    }
}
