package com.gmail.damianmajcherq.sicommad.build;

import com.gmail.damianmajcherq.sicommad.BaseNode;
import com.gmail.damianmajcherq.sicommad.Node;
import com.gmail.damianmajcherq.sicommad.ParameterParser;
import com.gmail.damianmajcherq.sicommad.ParameterParserProvider;
import javafx.util.Pair;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.ToDoubleBiFunction;


public class BuildCommandOptions {

    private String command;


    private ArrayList<ParameterParserProvider> providers;


    List<Node> tree;

    public BaseNode feed(Collection<Pair<Object , Method>> elements) {
        BaseNode base;

        return null;
    }

    protected void process(Object o , Method m) {
        List<Node> t = tree;
        ParameterParserProvider ppp;
        for (Parameter p : m.getParameters()) {
            if (p.isAnnotationPresent(AExternalProvided.class))
                continue;
            ppp = findProvider(m , p);
            if (ppp == null) {
//                TODO not found provider, whats next? should return value

            }
            ParameterParser pp = ppp.createParser(m , p);

            for (Node n : t) {
                if (n.getParser().equalsParser(pp)) {
                    t = n.getTree();
                    break;
                }
            }
//            TODO dokoncz
        }
    }


    @Nullable
    public ParameterParserProvider findProvider(Method m , Parameter p) {
        return providers.stream().filter( c->
        {
           return c.isProviderFor(m,p);
        }).findFirst().orElse(null);
    }

    public void addProvider(ParameterParserProvider p) {
        providers.add(p);
    }

    public BaseNode create() {
        sortProviders();

        return null;
//        TODO
    }


    public void sortProviders() {
        providers.sort(BuildCommandOptions::compare);
    }


    private static int compare(ParameterParserProvider a, ParameterParserProvider b) {
        if (a.parameterType().isAssignableFrom(b.parameterType())) {
            if (b.parameterType().isAssignableFrom(a.parameterType()))
                return 0;
            return 1;
        } else if (b.parameterType().isAssignableFrom(a.parameterType()))
            return -1;
        return 0;
    }




}
