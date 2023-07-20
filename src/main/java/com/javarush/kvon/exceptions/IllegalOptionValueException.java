package com.javarush.kvon.exceptions;

public class IllegalOptionValueException extends RuntimeException {

    public final static String MSG = "Value of option isn't correct. Value has to be 1 or 2.";

    public IllegalOptionValueException() {
        super(MSG);
    }
}
