package com.gmail.damianmajcherq.sicommad.ParameterProviders;

import com.gmail.damianmajcherq.sicommad.InvokeArray;
import com.gmail.damianmajcherq.sicommad.ParameterParser;
import com.gmail.damianmajcherq.sicommad.ParameterParserProvider;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class ProviderString implements ParameterParserProvider {

    @Override
    public ParameterParser createParser(Method m, Parameter p) {

        return new StringProvider();
    }

    @Override
    public boolean isProviderFor(Method m, Parameter p) {
        return p.getType().isAssignableFrom(String.class);
    }

    @Override
    public Class parameterType() {
        return String.class;
    }


    public class StringProvider implements ParameterParser {


        @Override
        public boolean parse(InvokeArray commandArgs, String s) {
            commandArgs.add(s);
            return true;
        }

        @Override
        public List<String> predict(String s) {
            return new ArrayList<>();
        }
    }
}
