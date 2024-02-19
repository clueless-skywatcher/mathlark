package io.mathlark.parser.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import io.mathlark.parser.GlobalSymbolRegistry;

public class DictExpression implements IExpression {
    public Map<IExpression, IExpression> map;
    
    public DictExpression(Map<IExpression, IExpression> map) {
        this.map = map;
    }

    public IExpression getValue(IExpression keyExp) {
        if (!map.containsKey(keyExp)) {
            System.out.println("Key does not exist: " + keyExp);
            return GlobalSymbolRegistry.UNDEFINED;
        }
        return map.get(keyExp);
    }

    @Override
    public IExpression evaluate() {
        Map<IExpression, IExpression> evalMap = new HashMap<>();
        for (IExpression key : map.keySet()) {
            evalMap.put(key, map.get(key).evaluate());
        }
        this.map = evalMap;
        return new DictExpression(this.map);
    }

    @Override
    public Map<IExpression, IExpression> getVal() {
        return this.map;
    }

    public String toString() {
        StringJoiner entries = new StringJoiner(", ");
        for (Map.Entry<IExpression, IExpression> entry : this.map.entrySet()) {
            entries.add(String.format("%s = %s", entry.getKey().evaluate().getVal(), entry.getValue().evaluate().getVal()));
        }
        return String.format("{%s}", entries.toString());
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        Map<IExpression, IExpression> otherDict;
        if (other instanceof DictExpression) {
            otherDict = ((DictExpression) other).getVal();
        }
        else {
            otherDict = (Map<IExpression, IExpression>) other;
        }

        for (IExpression key: this.map.keySet()) {
            if (!this.map.get(key).equals(otherDict.get(key))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Number getSize() {
        return this.map.size();
    }
}
