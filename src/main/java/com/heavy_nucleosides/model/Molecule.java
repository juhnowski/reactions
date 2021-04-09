package com.heavy_nucleosides.model;

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
