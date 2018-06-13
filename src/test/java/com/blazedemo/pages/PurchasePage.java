package com.blazedemo.pages;

import com.blazedemo.model.OrderParams;
import com.blazedemo.sections.OrderForm;
import com.blazedemo.sections.PurchaseInformation;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import ru.otus.utils.TestHelper;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@JPage(url="/purchase.php")
public class PurchasePage extends WebPage {
    public PurchaseInformation info;
    public OrderForm form;

    public PurchasePage checkFlightNumberText(String flightNum){
        assertEquals(info.getFlightNumberText(), "Flight Number: " + flightNum);
        return this;
    }

    public PurchasePage checkAirlineText(String airline){
        assertEquals(info.getAirlineText(), "Airline: " + airline);
        return this;
    }

    public PurchasePage checkPriceText(float price){
        assertEquals(info.getPriceText(), "Price: " + price);
        return this;
    }

    public PurchasePage checkTotalPrice(float price){
        float taxes = info.getTaxes();
        float totalCost = info.getTotalCost();
        assertEquals(totalCost, TestHelper.roundFloat(price + taxes));
        return this;
    }

    public PurchasePage selectCardType(String value){
        form.selectCardType(value);
        return this;
    }

    public PurchasePage fillForm(OrderParams orderParams){
        form.fillForm(orderParams);
        return this;
    }

    public void submit(){
        form.submit();
    }
}
