package io.mathlark.parser.expressions;

import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.SymbolTables;
import lombok.Getter;

public class VarDefExpression implements IExpression {
    private @Getter String symbol;

    public VarDefExpression(String symbol) {
        this.symbol = symbol;
    }

    public Object getVal() {
        return String.format("Defined variable %s", this.symbol);
    }

    public String toString() {
        return this.symbol;
    }

    @Override
    public IExpression evaluate() {
        if (SymbolTables.isGlobal(symbol)) {
            System.out.println(String.format("Cannot redefine inbuilt symbol \"%s\"", symbol));
            return GlobalSymbolRegistry.UNDEFINED;
        }
        SymbolTables.addLocal(symbol, new VarExpression(symbol));
        return new StringExpression((String) getVal());
    }

    @Override
    public Number getSize() {
        return 0;
    }
}
