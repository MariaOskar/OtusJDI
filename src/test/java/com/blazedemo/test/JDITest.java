package com.blazedemo.test;

import com.blazedemo.BaseTest;
import com.blazedemo.model.OrderParams;
import com.blazedemo.util.builder.OrderParamsBuilder;
import com.blazedemo.util.builder.RandomOrderParamsBuilder;
import org.testng.annotations.Test;
import java.text.ParseException;
import java.util.*;
import static com.blazedemo.BlazeDemoSite.*;

public class JDITest extends BaseTest {

    @Test
    public void test() throws ParseException {
        Map<String,Object> values = new HashMap<>();
        OrderParams orderParams = buildOrderParams(new RandomOrderParamsBuilder());
        float maxPrice = 300;

        choicePage.open();
        choicePage
                .saveFromPortValueToMap(values, "fromPortValue")
                .saveToPortValueToMap(values, "toPortValue")
                .selectFrom( (String) values.get("fromPortValue") )
                .selectTo( (String) values.get("toPortValue") )
                .submit();

        reservePage
                .checkOpenedPage()
                .checkFromPortValue( (String) values.get("fromPortValue") )
                .checkToPortValue( (String) values.get("toPortValue") )
                .selectFilteredFlight(maxPrice)
                .saveAirlineCoToMap(values,"airline")
                .savePriceToMap(values,"price")
                .saveFlightNumToMap(values,"flightNum")
                .submitFlight();

        purchasePage
                .saveCardTypeValueToMap(values,"cardTypeValue")
                .checkFlightNumberText( (String) values.get("flightNum") )
                .checkAirlineText( (String) values.get("airline") )
                .checkPriceText( (float) values.get("price") )
                .checkTotalPrice( (float) values.get("price") )
                .selectCardType( (String) values.get("cardTypeValue") )
                .fillForm(orderParams)
                .submit();

        Date date = new Date();

        confirmationPage
                .waitUntilPageLoaded() //данный wait предназначен только для браузера Microsoft EDGE, который не дожидается загрузки страницы
                .checkOpenedPage()
                .checkStatusText("PendingCapture")
                .checkAmountText("USD")
                .checkExpirationText(orderParams.getMonth()+" /"+orderParams.getYear())
                .checkAuthCodeText("888888")
                .checkIdText()
                .checkCardNumber(orderParams.getCardNumber())
                .checkOrderDate(date)
        ;
    }

    private OrderParams buildOrderParams(OrderParamsBuilder builder){
        builder.createOrderParams();
        builder.build();
        return builder.getOrderParams();
    }
}
