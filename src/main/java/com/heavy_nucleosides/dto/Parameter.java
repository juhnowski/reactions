package com.heavy_nucleosides.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
