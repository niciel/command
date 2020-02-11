package com.gmail.damianmajcherq.sicommad;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public interface ParameterParserProvider {


    public ParameterParser createParser(Method m , Parameter p );

    public boolean isProviderFor(Method m , Parameter p) ;

    public Class parameterType();

}
