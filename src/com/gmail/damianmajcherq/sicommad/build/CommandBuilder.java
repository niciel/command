package com.gmail.damianmajcherq.sicommad.build;

import com.gmail.damianmajcherq.sicommad.BaseNode;
import com.gmail.damianmajcherq.sicommad.CommandMethod;
import com.gmail.damianmajcherq.sicommad.CommandNodeExecutor;
import javafx.util.Pair;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


public class CommandBuilder {

    private ArrayList<Pair<Object , Class>> objects;
    private HashSet<String> duplications;

    private boolean deepSearch = false;
    private boolean excludeStatic = true;

    private HashMap<String,BuildCommandOptions> commands;



    public void build() {
        HashMap<String , List<Pair<Object ,Method>>> mainMap = new HashMap<>();

        List<Pair<Object ,Method>> list;
        Map<String , List<Method>> map;

        BaseNode bn;
        for (Pair<Object , Class> p : objects) {
            map = extractAllMethods(p.getValue());
            for (Map.Entry<String,List<Method>> e : map.entrySet()) {
                list = mainMap.get(e.getKey());
                if (list == null) {
                    list = new ArrayList<>();
                    mainMap.put(e.getKey() , list);
                }
                for (Method m : e.getValue()) {
                    list.add( new Pair<>(p.getKey() , m));
                }
            }
        }

        BuildCommandOptions bco ;
        for (Map.Entry<String , List<Pair<Object , Method>>> e : mainMap.entrySet()) {
            bco = commands.get(e.getKey());
            bn = bco.feed(e.getValue());
        }


    }

    @NonNls
    public Map<String , List<Method>> extractAllMethods(Class clazz) {
        HashMap<String , List<Method>> methods = new HashMap<>();
        List<Method> list;
        CommandMethod commandMethod;
        for (Method m : clazz.getDeclaredMethods()) {
            commandMethod = m.getAnnotation(CommandMethod.class);
            if (commandMethod == null) {
                continue;
            }
            if ( ! commands.containsKey(commandMethod.command())) {
//                TODO
                continue;
            }
            if (Modifier.isStatic(m.getModifiers()) &&  excludeStatic) {
                continue;
            }
            list = methods.get(commandMethod.command());
            if (list == null) {
                list = new ArrayList<>();
                methods.put(commandMethod.command() , list);
            }
            list.add(m);
        }
        return methods;
    }



    public boolean add(@NonNls Object o) {
        Class clazz;
        Object obj;
        if (o instanceof Class)
        {
            clazz = (Class) o;
            obj = null;
        }
        else
        {
            clazz = o.getClass();
            obj = o;
        }
        return add(obj ,clazz);
    }
    public boolean add(@Nullable Object o ,@NonNls Class clazz) {
        if (duplications.contains(clazz.getName())) {
//            TODO
            return false;
        }
        objects.add(new Pair<>(o , clazz));
        duplications.add(clazz.getName());
        if (deepSearch) {
            if (clazz.getSuperclass().getName().contentEquals(Object.class.getName()))
                return true;
            return add(o , clazz.getSuperclass());
        }
        return true;
    }

}
