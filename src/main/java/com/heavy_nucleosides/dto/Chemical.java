package com.heavy_nucleosides.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
