package com.heavy_nucleosides.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
