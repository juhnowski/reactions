package com.heavy_nucleosides.dto;

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
