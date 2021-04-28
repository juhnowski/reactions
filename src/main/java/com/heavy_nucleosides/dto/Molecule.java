package com.heavy_nucleosides.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record Molecule(String id,
                      String name,
                      String dictRef){
    public Molecule(String id,
                    String name,
                    String dictRef){
        this.id = id;
        this.name = name;
        this.dictRef = dictRef;
    }
}
