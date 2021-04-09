package com.heavy_nucleosides.model;

public record Amount(String propertyType,
                     String normalizedValue,
                     String value
                     ) {
    public Amount(String propertyType,
                  String normalizedValue,
                  String value
    ){
        this.propertyType = propertyType;
        this.normalizedValue = normalizedValue;
        this.value = value;
    }
}
