package com.blazedemo.sections;

import com.blazedemo.data.CardTypeEnum;
import com.blazedemo.model.OrderParams;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;
import ru.otus.utils.TestHelper;

import java.util.List;

public class OrderForm extends Section {
    @FindBy(css = "select[name=cardType]")
    public IDropDown<CardTypeEnum> cardTypeDropdown;

    @FindBy(css = "input[name=inputName]")
    TextField inputName;
    @FindBy(css = "input[name=address]")
    TextField address;
    @FindBy(css = "input[name=city]")
    TextField city;
    @FindBy(css = "input[name=state]")
    TextField state;
    @FindBy(css = "input[name=zipCode]")
    TextField zipCode;
    @FindBy(css = "input[name=creditCardNumber]")
    TextField creditCardNumber;
    @FindBy(css = "input[name=creditCardMonth]")
    TextField creditCardMonth;
    @FindBy(css = "input[name=creditCardYear]")
    TextField creditCardYear;
    @FindBy(css = "input[name=nameOnCard]")
    TextField nameOnCard;

    @FindBy(css = "input[type=submit]")
    Button submit;

    public String getCardTypeValueRandom(){
        List<String> options = cardTypeDropdown.getValues();
        return options.get(TestHelper.random(0, options.size() - 1));
    }

    public void selectCardType(String value){
        cardTypeDropdown.select(value);
    }

    public void fillForm(OrderParams orderParams){
        inputName.clear();
        inputName.sendKeys(orderParams.getName());
        address.clear();
        address.sendKeys(orderParams.getAddress());
        city.clear();
        city.sendKeys(orderParams.getCity());
        state.clear();
        state.sendKeys(orderParams.getState());
        zipCode.clear();
        zipCode.sendKeys(orderParams.getState());
        creditCardNumber.clear();
        creditCardNumber.sendKeys(orderParams.getCardNumber());
        creditCardMonth.clear();
        creditCardMonth.sendKeys(orderParams.getMonth());
        creditCardYear.clear();
        creditCardYear.sendKeys(orderParams.getYear());
        nameOnCard.clear();
        nameOnCard.sendKeys(orderParams.getNameOnCard());
    }

    public void submit(){
        submit.click();
    }
}
