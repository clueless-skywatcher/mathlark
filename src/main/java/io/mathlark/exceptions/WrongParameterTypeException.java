package io.mathlark.exceptions;

import java.util.List;
import java.util.StringJoiner;

import io.mathlark.parser.expressions.IExpression;

public class WrongParameterTypeException extends Exception {
    public WrongParameterTypeException(String message, Object... varArgs) {
        super(String.format(message, varArgs));
    }

    public WrongParameterTypeException(Class<? extends IExpression> expected, int pos, Class<? extends IExpression> actual) {
        this("Expected %s at position %d, got %s", expected.getSimpleName(), pos, actual.getSimpleName());
    }

    public WrongParameterTypeException(List<Class<? extends IExpression>> expected, int pos, Class<? extends IExpression> actual) {
        this("Expected %s at position %d, got %s", getJoiner(expected).toString(), pos, actual.getSimpleName());
    }

    private static StringJoiner getJoiner(List<Class<? extends IExpression>> expected) {
        StringJoiner classes = new StringJoiner(", ");
        for (Class<?> klass: expected) {
            classes.add(klass.getSimpleName());
        }
        return classes;
    }
}
