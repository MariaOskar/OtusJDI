package com.blazedemo.data;

public enum  DepartureEnum {
    PARIS("Paris"),
    PHILADELPHIA("Philadelphia"),
    BOSTON("Boston"),
    PORTLAND("Portland"),
    SAN_DIEGO("San Diego"),
    MEXICO_CITY("Mexico City"),
    SAN_PAOLO("São Paolo");

    private String city;

    DepartureEnum (String city) {
        this.city = city;
    }

    public String city() {
        return city;
    }
}
