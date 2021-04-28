package com.heavy_nucleosides.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.HashMap;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
