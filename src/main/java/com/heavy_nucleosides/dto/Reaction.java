package com.heavy_nucleosides.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record Reaction(Source source,
                       String reactionSmiles,
                       List<Product> productList,
                       List<Reactant> reactantList,
                       List<Spectator> spectatorList,
                       List<ReactionAction> reactionActionList){
    public Reaction(Source source,
                    String reactionSmiles,
                    List<Product> productList,
                    List<Reactant> reactantList,
                    List<Spectator> spectatorList,
                    List<ReactionAction> reactionActionList) {
        this.source = source;
        this.reactionSmiles = reactionSmiles;
        this.productList = productList;
        this.reactantList = reactantList;
        this.spectatorList = spectatorList;
        this.reactionActionList = reactionActionList;
    }
}