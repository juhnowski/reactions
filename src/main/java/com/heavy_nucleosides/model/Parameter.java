package com.heavy_nucleosides.model;

public record Parameter(String propertyType,
                        String normalizedValue,
                        String value) {
    public Parameter(String propertyType,
                     String normalizedValue,
                     String value) {
        this.propertyType = propertyType;
        this.normalizedValue = normalizedValue;
        this.value = value;
    }
}
