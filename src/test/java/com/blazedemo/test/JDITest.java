package com.blazedemo.test;

import com.blazedemo.BaseTest;
import com.blazedemo.common.FlightIsNotChosenException;
import com.blazedemo.model.OrderParams;
import com.blazedemo.util.builder.OrderParamsBuilder;
import com.blazedemo.util.builder.RandomOrderParamsBuilder;
import org.testng.annotations.Test;
import ru.otus.utils.TestStorage;

import java.text.ParseException;
import java.util.*;
import static com.blazedemo.BlazeDemoSite.*;

public class JDITest extends BaseTest {

    @Test
    public void test() throws ParseException, FlightIsNotChosenException {
        OrderParams orderParams = buildOrderParams(new RandomOrderParamsBuilder());
        float maxPrice = 300;
        choicePage.open();
        choicePage
                .selectRandomFromPort()
                .selectRandomToPort()
                .submit();
        reservePage
                .checkOpenedPage()
                .checkHiddenFields()
                .selectFilteredFlight(maxPrice)
                .submitFlight();
        purchasePage
                .checkFlightNumberText( TestStorage.getString("flightNum") )
                .checkAirlineText( TestStorage.getString("airline") )
                .checkPriceText( TestStorage.getFloat("price") )
                .checkTotalPrice( TestStorage.getFloat("price") )
                .fillForm(orderParams)
                .submit();
        confirmationPage
                .waitUntilPageLoaded() //данный wait предназначен только для браузера Microsoft EDGE, который не дожидается загрузки страницы
                .checkStatusText("PendingCapture")
                .checkAmountText("USD")
                .checkExpirationText(orderParams.getMonth()+" /"+orderParams.getYear())
                .checkAuthCodeText("888888")
                .checkIdText()
                .checkCardNumber(orderParams.getCardNumber())
                .checkOrderDate()
        ;
    }

    private OrderParams buildOrderParams(OrderParamsBuilder builder){
        builder.createOrderParams();
        builder.build();
        return builder.getOrderParams();
    }
}
