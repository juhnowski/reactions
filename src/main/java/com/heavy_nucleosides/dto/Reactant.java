package com.heavy_nucleosides.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record Reactant(String role,
                       String count,
                       Molecule molecule,
                       List<Amount> amounts,
                       String smiles,
                       String inchi,
                       String entityType
                       ) {
    public Reactant(String role,
             String count,
             Molecule molecule,
             List<Amount> amounts,
             String smiles,
             String inchi,
             String entityType
    ) {
        this.role = role;
        this.count = count;
        this.molecule = molecule;
        this.amounts = amounts;
        this.smiles = smiles;
        this.inchi = inchi;
        this.entityType = entityType;
    }
}
