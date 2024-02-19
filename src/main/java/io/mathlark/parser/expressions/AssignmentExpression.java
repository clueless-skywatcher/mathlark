package io.mathlark.parser.expressions;

import io.mathlark.parser.SymbolTables;

@SuppressWarnings("unused")
public class AssignmentExpression implements IExpression {
    private String symVal;
    private IExpression expr;

    public AssignmentExpression(String symVal, IExpression expr) {
        this.symVal = symVal;
        this.expr = expr;

        SymbolTables.addLocal(symVal, expr);
    }

    @Override
    public IExpression evaluate() {
        return null;
    }

    @Override
    public Object getVal() {
        return null;
    }

    @Override
    public Number getSize() {
        return 0;
    }
    
}
