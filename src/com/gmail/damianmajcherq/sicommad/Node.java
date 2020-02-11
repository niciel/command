package com.gmail.damianmajcherq.sicommad;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class Node {

    private List<Node> nodes;

    private ParameterParser parser;


    private CommandNodeExecutor executor;

    private int nodeDeep;

    public ProcessResult process(@NotNull InvokeArray commandArgs  , @NotNull String[] args) {
        if (nodeDeep +1 >= args.length) {
            return new ProcessResult(this , commandArgs);
        }
        Node n = parse(commandArgs , args[nodeDeep+1]);
        if (n == null) {
//            TODO zakonczyc brak dalszych
        }
        return n.process(commandArgs , args);
    }

    @Nullable
    public Node parse(@NotNull InvokeArray invoke , @NotNull String s ) {
        return nodes.stream().filter( p ->
                {
                    if (p.getParser() != null) {
                        return p.getParser().parse(invoke , s);
                    }
                    return false;
                }
                ).findFirst().orElse(null);
    }

    @Nullable
    public ParameterParser getParser() {
        return parser;
    }

    public List<Node> getTree(){
        return nodes;
    }

}
