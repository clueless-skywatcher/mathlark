package io.mathlark.exceptions;

public class WrongParameterLengthException extends Exception {
    public WrongParameterLengthException(String message, Object... varArgs) {
        super(String.format(message, varArgs));
    }
}
