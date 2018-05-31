package com.blazedemo.sections;

import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.support.FindBy;

public class Receipt extends Section {

    @JTable( root = @FindBy(css = "table.table"))
    Table table;

    public String getIdText (){
        return table.cell(2,1).getText();
    }

    public String getStatusText(){
        return table.cell(2,2).getText();
    }

    public String getAmountText(){
        return table.cell(2,3).getText();
    }

    public String getCardNumberVal(){
        return table.cell(2,4).getText();
    }

    public String getExpirationText(){
        return table.cell(2,5).getText();
    }

    public String getAuthCodeText(){
        return table.cell(2,6).getText();
    }

    public String getOrderDateValue(){
        return table.cell(2,7).getText();
    }
}
