package com.blazedemo.sections;

import com.blazedemo.common.FlightIsNotChosenException;
import com.blazedemo.common.TableRowIndexOutOfBoundException;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.support.FindBy;
import ru.otus.utils.TestHelper;
import ru.otus.utils.TestStorage;
import java.util.ArrayList;
import java.util.List;

public class FlightsList extends Section {

    @JTable(root = @FindBy(css = "table.table"))
    Table table;

    private int rowNum = 0;

    public String getFlightNum() throws FlightIsNotChosenException {
        checkIfFlightNumExists();
        return table.cell(2,rowNum).getText();
    }

    public float getPrice() throws FlightIsNotChosenException, TableRowIndexOutOfBoundException {
        checkIfFlightNumExists();
        return getPrice(rowNum);
    }

    public float getPrice( int row ) throws TableRowIndexOutOfBoundException {
        if( row < 1 || row > table.rows().count())
            throw new TableRowIndexOutOfBoundException("В таблице нет строки с индексом "+row);
        return Float.parseFloat(table.cell(6, row).getText().substring(1));
    }

    public String getAirlineCo() throws FlightIsNotChosenException {
        checkIfFlightNumExists();
        return table.cell(3,rowNum).getText();
    }

    public void submitFlight() throws FlightIsNotChosenException {
        checkIfFlightNumExists();
        table.cell(1,rowNum).click();
    }

    public void selectFilteredFlight(float maxPrice){
        List<Integer> filteredFlights = new ArrayList<Integer>();
        try {
            for(int i=1; i <= table.rows().count(); i++){
                if (getPrice(i)<maxPrice){
                    filteredFlights.add(i);
                }
            }
            if(filteredFlights.size()>0){
                rowNum = filteredFlights.get(0);
                TestStorage.put("airline", getAirlineCo());
                TestStorage.put("price", getPrice());
                TestStorage.put("flightNum", getFlightNum());
            }
        } catch (FlightIsNotChosenException e) {
            e.printStackTrace();
        } catch (TableRowIndexOutOfBoundException e) {
            e.printStackTrace();
        }
    }

    public void selectRandomFlight(){
        rowNum = TestHelper.random(1,table.rows().count());
        try {
            TestStorage.put("airline", getAirlineCo());
            TestStorage.put("price", getPrice());
            TestStorage.put("flightNum", getFlightNum());
        } catch (FlightIsNotChosenException e) {
            e.printStackTrace();
        } catch (TableRowIndexOutOfBoundException e) {
            e.printStackTrace();
        }
    }

    public void checkIfFlightNumExists() throws FlightIsNotChosenException {
        if (rowNum == 0) throw new FlightIsNotChosenException("Не выбран рейс");
    }
}