package com.heavy_nucleosides.model;

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
