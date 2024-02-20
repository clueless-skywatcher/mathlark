package io.mathlark.parser.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
        if (!AllFunctionRegistry.isFunc(funcName)) {
            this.val = new StringExpression(toString());
            return this;
        }
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
        if (other instanceof FunctionCallExpression) {
            FunctionCallExpression exp = (FunctionCallExpression) other;
            return funcName.equals(exp.funcName) && params.equals(exp.params);
        }
        if (other instanceof IExpression) {
            return val.equals(((IExpression) other).evaluate());
        }
        return val.getVal().equals(other);
    }

    @Override
    public Number getSize() {
        return 0;
    }

    public String toString() {
        StringJoiner join = new StringJoiner(", ");
        for (IExpression param: params) {
            join.add(param.toString());
        }

        return String.format("%s(%s)", funcName, join.toString());
    }
}
