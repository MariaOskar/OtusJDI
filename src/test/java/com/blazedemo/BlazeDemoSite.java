package com.blazedemo;

import com.blazedemo.pages.ChoicePage;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;

@JSite(value = "http://blazedemo.com")
public class BlazeDemoSite extends WebSite {
    public static ChoicePage choicePage;
    public static ConfirmationPage confirmationPage;
    public static PurchasePage purchasePage;
    public static ReservePage reservePage;
}
