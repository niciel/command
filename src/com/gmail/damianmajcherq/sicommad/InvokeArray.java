package com.gmail.damianmajcherq.sicommad;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class InvokeArray {

    private List<Object> objects;
    private List<Pair<Object , String>> special;


    public void add(Object o) {
        add(o);
    }

    public void add(Object o, String flag) {
        if (flag != null) {
            if (flag.isEmpty())
                add(o);
            else
                special.add(new Pair(o, flag));
            return;
        }
        add(o);
    }

    public int size() {
        return objects.size() + special.size();
    }

    public <T>  List<T> get(Class<T> type) {
        final List<T> list = new ArrayList<>();
        objects.forEach( c->{
            if (c.getClass().isAssignableFrom(type))
                list.add((T) c);
        });
        special.forEach( c->{
            if (c.getClass().isAssignableFrom(type))
                list.add((T) c);
        });
        return list;
    }

    public List<Object> getArray() {
        return  null;
    }






}
