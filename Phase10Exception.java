package com.mycompany.phase10;

public class Phase10Exception extends RuntimeException {

    /** Default */
    public Phase10Exception() {}

    /** Phase10Exception with the given message */
    public Phase10Exception(String message) {
        super(message);
    }

    /** Phase10Exception with throwable */
    public Phase10Exception(Throwable arg0) {
        super(arg0);
    }

    /** Phase10Exception with the given message and throwable */
    public Phase10Exception(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /** Phase10Exception with the given message and throwable and actual and expected */
    public Phase10Exception(String arg0, Throwable arg1, boolean arg2,
        boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
