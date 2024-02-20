package io.mathlark.general.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.exceptions.WrongParameterTypeException;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.expressions.FunctionDefExpression;
import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.StringExpression;

/**
 * FuncDef(Name of Function)
 * -----------------
 * Internal Java Class Name: FuncDefFunc
 * 
 * Define an anonymous function defined by name. Functions 
 * defined this way are stored in a local function registry
 * 
 * Examples:
 * >> FuncDef("f")
 * f
 * >> f
 * f
 * >> f(1)
 * f(1)
 * >> Map(f, [1, 2, 3])
 * [f(1), f(2), f(3)]
 */
public class FuncDefFunc implements LarkCallable {
    @Override
    public IExpression evaluate(IExpression... expressions) {
        StringExpression funcName = (StringExpression) expressions[0];
        return new FunctionDefExpression(funcName).evaluate();
    }

    @Override
    public void checkArgs(IExpression... expressions)
            throws WrongParameterLengthException, WrongParameterTypeException {
        
    }

    @Override
    public String getName() {
        return "FuncDef";
    }
    
}
