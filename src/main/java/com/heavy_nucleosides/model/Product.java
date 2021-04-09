package com.heavy_nucleosides.model;

import java.util.HashMap;
import java.util.List;

public record Product(Molecule molecule,
                      String smiles,
                      String inchi) {
    public Product(Molecule molecule,
                   String smiles,
                   String inchi) {
        this.molecule = molecule;
        this.smiles = smiles;
        this.inchi = inchi;
    }
}
