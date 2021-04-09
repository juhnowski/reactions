package com.heavy_nucleosides.model;

public record ReactionAction(String action,
                             String phraseText,
                             Parameter parameter
                             ) {
    public ReactionAction(String action,
                          String phraseText,
                          Parameter parameter
    ) {
        this.action = action;
        this.phraseText = phraseText;
        this.parameter = parameter;
    }
}
