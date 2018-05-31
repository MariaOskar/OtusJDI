package com.blazedemo.model;

import lombok.Data;

public @Data class OrderParams {
    public String name;
    public String address;
    public String city;
    public String state;
    public String zipCode;
    public String cardNumber;
    public String month;
    public String year;
    public String nameOnCard;

    public OrderParams() { }
}
