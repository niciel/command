package com.gmail.damianmajcherq.sicommad;

public class ExecutionResult {

    public final static String NOT_ENOUGH_PARAMETERS = "Not Enough Parameters";


    final public boolean failed;
    final public String message;

    public ExecutionResult(boolean failed, String message) {
        this.failed = failed;
        this.message = message;
    }




}
