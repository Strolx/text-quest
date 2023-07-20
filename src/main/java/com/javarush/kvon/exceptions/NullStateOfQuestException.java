package com.javarush.kvon.exceptions;

public class NullStateOfQuestException extends RuntimeException {
    public static final String MSG = "State of quest can't be null";

    public NullStateOfQuestException() {
        super(MSG);
    }
}
