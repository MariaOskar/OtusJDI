package com.blazedemo.test;

import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import com.blazedemo.BaseTest;
import com.blazedemo.common.FlightIsNotChosenException;
import com.blazedemo.data.DepartureEnum;
import com.blazedemo.data.DestinationEnum;
import com.blazedemo.listeners.TestListener;
import com.blazedemo.model.OrderParams;
import com.blazedemo.util.builder.OrderParamsBuilder;
import com.blazedemo.util.builder.RandomOrderParamsBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.otus.utils.Screenshot;
import ru.otus.utils.TestStorage;
import java.text.ParseException;
import static com.blazedemo.BlazeDemoSite.*;

@Listeners(value = {TestListener.class, VideoListener.class})
public class JDITest extends BaseTest {

    @Test
    @Issue("BD-100")
    @Video(name = "blazedemo_test")
    @Description("Поиск рейса и оформление заказа на blazedemo.com")
    public void test() throws ParseException, FlightIsNotChosenException {
        OrderParams orderParams = buildOrderParams(new RandomOrderParamsBuilder());
        float maxPrice = 300;
        choicePage.open();
        choicePage
                .selectRandomFromPort()
                .selectRandomToPort()
                .submit();
        Screenshot.take(); // для отчета Allure
        reservePage
                .checkOpenedPage()
                .checkHiddenFields()
                .selectFilteredFlight(maxPrice)
                .submitFlight();
        Screenshot.take(); // для отчета Allure
        purchasePage
                .checkFlightNumberText( TestStorage.getString("flightNum") )
                .checkAirlineText( TestStorage.getString("airline") )
                .checkPriceText( TestStorage.getFloat("price") )
                .checkTotalPrice( TestStorage.getFloat("price") )
                .fillForm(orderParams)
                .submit();
        Screenshot.take(); // для отчета Allure
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
        Screenshot.take(); // для отчета Allure
    }

    @Test
    public void negativeTest(){
        choicePage.open();
        choicePage
                .selectFrom(DepartureEnum.BOSTON.city())
                .selectTo(DestinationEnum.BUENOS_AIRES.city())
                .submit();
        reservePage
                .checkOpenedPage()
                .checkFromPortValue(DepartureEnum.MEXICO_CITY.city())
                .checkToPortValue(DestinationEnum.BERLIN.city())
        ;
    }

    private OrderParams buildOrderParams(OrderParamsBuilder builder){
        builder.createOrderParams();
        builder.build();
        return builder.getOrderParams();
    }
}
