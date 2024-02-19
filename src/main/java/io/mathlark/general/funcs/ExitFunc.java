package io.mathlark.general.funcs;

import io.mathlark.exceptions.WrongParameterLengthException;
import io.mathlark.funcs.LarkCallable;
import io.mathlark.parser.GlobalSymbolRegistry;
import io.mathlark.parser.expressions.IExpression;

/**
 * Exit()
 * -----------------
 * Internal Java Class Name: ExitFunc
 * 
 * Exits the current session
 * 
 * Examples:
 * >> Exit()
 * Session terminated
 */
public class ExitFunc implements LarkCallable {
    @Override
    public IExpression evaluate(IExpression... expressions) {
        try {
            checkArgs(expressions);
        } catch (Exception e) {
            System.out.println("Invalid function call: " + e.getMessage());
            return GlobalSymbolRegistry.UNDEFINED;
        }
        System.out.println("Session terminated");
        System.exit(0);
        return null;
    }

    @Override
    public void checkArgs(IExpression... expressions) throws WrongParameterLengthException {
        if (expressions.length != 0) {
            throw new WrongParameterLengthException("Expected 0 elements, got %d elements", expressions.length);
        }
    }

    @Override
    public String getName() {
        return "Exit";
    }
}
