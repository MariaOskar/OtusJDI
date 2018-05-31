package com.blazedemo.data;

public enum CardTypeEnum {
    VISA("Visa","visa"),
    AMEX("American Express","amex"),
    DINERSCLUB("Diner's Club","dinersclub");

    private String value;
    private String label;

    CardTypeEnum(String label,String value){
        this.label = label;
        this.value = value;
    }

    public String label() {
        return label;
    }

    public String value() {
        return value;
    }
}
