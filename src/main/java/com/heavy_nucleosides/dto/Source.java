package com.heavy_nucleosides.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record Source(String documentId,
                     String headingText,
                     String paragraphNum,
                     String paragraphText
                     ) {
    public Source(String documentId,
                  String headingText,
                  String paragraphNum,
                  String paragraphText
    ){
        this.documentId = documentId;
        this.headingText = headingText;
        this.paragraphNum = paragraphNum;
        this.paragraphText = paragraphText;
    }
    public static final String name = "source";
}
