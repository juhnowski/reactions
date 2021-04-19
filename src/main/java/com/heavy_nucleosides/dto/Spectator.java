package com.heavy_nucleosides.dto;

import java.util.List;

public record Spectator(String role,
                        Molecule molecule,
                        List<Amount> amounts,
                        String smiles,
                        String inchi,
                        String entityType) {
    public Spectator(String role,
                      Molecule molecule,
                      List<Amount> amounts,
                     String smiles,
                     String inchi,
                      String entityType) {
        this.role = role;
        this.molecule = molecule;
        this.amounts = amounts;
        this.smiles = smiles;
        this.inchi = inchi;
        this.entityType = entityType;
    }
    public static final String name = "spectator";
}
