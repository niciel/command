package com.gmail.damianmajcherq.sicommad;

import java.util.List;

public interface ParameterParser {

    public boolean parse(InvokeArray commandArgs , String s);

    public List<String> predict(String s);



    public default boolean equalsParser(ParameterParser pp) {
        if (pp.getClass().getName().contentEquals(this.getClass().getName())) {
            return equals(pp);
        }
        return false;
    }



}
