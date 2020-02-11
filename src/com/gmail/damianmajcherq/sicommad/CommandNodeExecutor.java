package com.gmail.damianmajcherq.sicommad;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CommandNodeExecutor {

    private Object objectHandle;
    private Method method;



    public ExecutionResult execute(InvokeArray inv) {
        ExecutionResult res = null;
        try {
            method.invoke(objectHandle , inv.getArray());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return res;
    }
}
