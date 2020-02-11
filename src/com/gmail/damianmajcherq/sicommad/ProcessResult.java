package com.gmail.damianmajcherq.sicommad;

import java.util.List;

public class ProcessResult {

    public Node lastMatchNode;
    public InvokeArray toInvoke;

    public ProcessResult(Node lastMatchNode , InvokeArray invoke) {
        this.lastMatchNode = lastMatchNode;
        this.toInvoke = invoke;
    }
}
