package com.blazedemo.data;

public enum DestinationEnum {
    BUENOS_AIRES("Buenos Aires"),
    ROME("Rome"),
    LONDON("London"),
    BERLIN("Berlin"),
    NEW_YORK("New York"),
    DUBLIN("Dublin"),
    CAIRO("Cairo");

    private String city;

    DestinationEnum(String city) {
        this.city = city;
    }

    public String city() {
        return city;
    }
}
