package com.heavy_nucleosides.model;

import java.util.List;

public record Chemical(Molecule molecule,
                       List<Identifier> identifiers,
                       String entityType) {
    public Chemical(Molecule molecule,
                    List<Identifier> identifiers,
                    String entityType) {
        this.molecule = molecule;
        this.identifiers = identifiers;
        this.entityType = entityType;
    }
}
