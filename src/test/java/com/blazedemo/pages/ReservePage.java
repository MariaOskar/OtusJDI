package com.blazedemo.pages;

import com.blazedemo.sections.FlightsList;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

@JPage(url="/reserve.php")
public class ReservePage extends WebPage {
    public FlightsList flightsList;

    private By fromInputLocator = By.cssSelector("input[name=fromPort]");
    private By toInputLocator = By.cssSelector("input[name=toPort]");

    public WebElement getToPortInput(){
        return getDriver().findElement(toInputLocator);
    }

    public WebElement getFromPortInput(){
        return getDriver().findElement(fromInputLocator);
    }

    public String getFromPortValue(){
        return getFromPortInput().getAttribute("value");
    }

    public String getToPortValue(){
        return getToPortInput().getAttribute("value");
    }

    public ReservePage selectRandomFlight(){
        flightsList.selectRandomFlight();
        return this;
    }

    public ReservePage selectFilteredFlight(float maxPrice){
        flightsList.selectFilteredFlight(maxPrice);
        return this;
    }

    public ReservePage submitFlight(){
        flightsList.submitFlight();
        return this;
    }

    public ReservePage saveFlightNumToMap(Map map, String key){
        map.put(key, flightsList.getFlightNum());
        return this;
    }

    public ReservePage savePriceToMap(Map map, String key){
        map.put(key, flightsList.getPrice());
        return this;
    }

    public ReservePage saveAirlineCoToMap(Map map, String key){
        map.put(key, flightsList.getAirlineCo());
        return this;
    }

    public ReservePage checkOpenedPage(){
        this.checkOpened();
        return this;
    }

    public ReservePage checkFromPortValue(String fromPortValue){
        assertEquals(getFromPortValue(), fromPortValue);
        return this;
    }

    public ReservePage checkToPortValue(String toPortValue){
        assertEquals(getToPortValue(), toPortValue);
        return this;
    }
}
