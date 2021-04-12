package com.heavy_nucleosides.model;

import java.util.List;

public record Chemical(Molecule molecule,
                       String smiles,
                       String inchi,
                       String entityType) {
    public Chemical(Molecule molecule,
                    String smiles,
                    String inchi,
                    String entityType) {
        this.molecule = molecule;
        this.smiles = smiles;
        this.inchi = inchi;
        this.entityType = entityType;
    }
}
