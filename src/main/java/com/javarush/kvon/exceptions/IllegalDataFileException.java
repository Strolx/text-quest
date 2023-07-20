package com.javarush.kvon.exceptions;

public class IllegalDataFileException extends RuntimeException {

    public final static String MSG = "Data file aren't correct for create quest.";

    public IllegalDataFileException() {
        super(MSG);
    }
}
