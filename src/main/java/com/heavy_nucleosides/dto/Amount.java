package com.heavy_nucleosides.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
