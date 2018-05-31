package com.blazedemo.sections;

import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.utils.TestHelper;

import java.util.List;
import java.util.stream.Collectors;

public class FlightsList extends Section {

    private static  final By FLIGHT = By.cssSelector("input[name=flight]");
    private static  final By PRICE = By.cssSelector("input[name=price]");
    private static  final By AIRLINE = By.cssSelector("input[name=airline]");
    private static  final By SUBMIT = By.cssSelector("td input[type=submit]");

    private WebElement flight;

    @FindBy(css = "table.table tbody tr")
    private List<WebElement> flights;

    public String getFlightNum(){
        if (flight!= null) return flight.findElement(FLIGHT).getAttribute("value");
        return null;
    }

    public float getPrice(){
        if (flight!=null) return Float.parseFloat(flight.findElement(PRICE).getAttribute("value"));
        return 0;
    }

    public String getAirlineCo(){
        if (flight!=null) return flight.findElement(AIRLINE).getAttribute("value");
        return null;
    }

    public void submitFlight(){
        if (flight!=null) flight.findElement(SUBMIT).click();
    }

    public void selectFilteredFlight(float maxPrice){
        // фильтрация вылетов с помощью Java 8
        List<WebElement> filteredFlights = flights
                .stream()
                .filter((webElement)->{
                    String priceToken = webElement.findElement(PRICE).getAttribute("value");
                    float price = Float.parseFloat(priceToken.replace("$",""));
                    return price < maxPrice;
                }).collect(Collectors.toList());
        if(filteredFlights.size()>0){
            flight = filteredFlights.get(0);
        }
    }

    public void selectRandomFlight(){
        flight = flights.get(TestHelper.random(0, flights.size() - 1));
    }
}
