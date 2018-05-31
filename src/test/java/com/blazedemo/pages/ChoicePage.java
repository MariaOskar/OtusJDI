package com.blazedemo.pages;

import com.blazedemo.data.DepartureEnum;
import com.blazedemo.data.DestinationEnum;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.openqa.selenium.support.FindBy;
import ru.otus.utils.TestHelper;
import java.util.List;
import java.util.Map;

@JPage(url="/")
public class ChoicePage extends WebPage {

    @FindBy(css = "input[type=submit]")
    private Button submit;

    @FindBy(css = "select[name=fromPort]")
    public IDropDown<DepartureEnum> departureDropdown;

    @FindBy(css = "select[name=toPort]")
    public IDropDown<DestinationEnum> destinationDropdown;

    public String getRandomFromOption(){
        List<String> options = departureDropdown.getValues();
        String value = options.get(TestHelper.random(0, options.size() - 1));
        return value;
    }

    public String getRandomToOption(){
        List<String> options = destinationDropdown.getValues();
        String value = options.get(TestHelper.random(0, options.size() - 1));
        return value;
    }

    public ChoicePage selectTo(String optionValue){
        destinationDropdown.select(optionValue);
        return this;
    }

    public ChoicePage selectFrom(String optionValue){
        departureDropdown.select(optionValue);
        return this;
    }

    public void submit(){
        submit.click();
    }

    public ChoicePage saveFromPortValueToMap(Map map, String key){
        map.put(key, getRandomFromOption());
        return this;
    }

    public ChoicePage saveToPortValueToMap(Map map, String key){
        map.put(key, getRandomToOption());
        return this;
    }
}
