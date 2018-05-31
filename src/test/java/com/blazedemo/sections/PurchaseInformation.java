package com.blazedemo.sections;

import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

public class PurchaseInformation extends Section {
    @FindBy( xpath = "//p[contains(text(), 'Flight Number')]")
    Label flightNumberP;
    @FindBy (xpath = "//p[contains(text(), 'Airline')]")
    Label airlineP;
    @FindBy (xpath = "//p[contains(text(), 'Price')]")
    Label priceP;
    @FindBy (xpath = "//p[contains(text(), 'Arbitrary Fees and Taxes')]")
    Label taxesP;
    @FindBy (xpath = "//p[contains(text(), 'Total Cost')]")
    Label totalCostP;

    public String getFlightNumberText(){
        return flightNumberP.getText();
    }

    public String getAirlineText(){
        return airlineP.getText();
    }

    public String getPriceText(){
        return priceP.getText();
    }

    public float getTaxes(){
        return Float.parseFloat(taxesP.getText().replace("Arbitrary Fees and Taxes: ", ""));
    }

    public float getTotalCost() {
        return Float.parseFloat(totalCostP.getText().replace("Total Cost: ", ""));
    }
}
