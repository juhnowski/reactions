package com.heavy_nucleosides.dto;

import java.util.List;

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
