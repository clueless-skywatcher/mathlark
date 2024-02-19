package io.mathlark.lists.funcs;

import java.util.Arrays;

import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;

/**
 * List(Any number of expressions)
 * -----------------
 * Internal Java Class Name: ListFunc
 * 
 * Create a list of expressions of any kind.
 * Same as using [x, y, z]
 * 
 * Examples:
 * >> a = List(1, 2, 3)
 * [1, 2, 3]
 * >> a = [1, 2, 3]
 */   
public class ListFunc implements LarkCallable {
    @Override
    public IExpression evaluate(IExpression... expressions) {
        return new ListExpression(Arrays.asList(expressions)).evaluate();
    }

    @Override
    public void checkArgs(IExpression... expressions) {}

    @Override
    public String getName() {
        return "List";
    }
    
}
