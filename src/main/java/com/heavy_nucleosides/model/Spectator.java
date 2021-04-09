package com.heavy_nucleosides.model;

import java.util.List;

public record Spectator(String role,
                        Molecule molecule,
                        List<Amount> amounts,
                        List<Identifier> identifiers,
                        String entityType) {
    public Spectator(String role,
                      Molecule molecule,
                      List<Amount> amounts,
                      List<Identifier> identifiers,
                      String entityType) {
        this.role = role;
        this.molecule = molecule;
        this.amounts = amounts;
        this.identifiers = identifiers;
        this.entityType = entityType;
    }
    public static final String name = "spectator";
}
