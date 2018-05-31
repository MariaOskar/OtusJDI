package com.blazedemo.pages;

import com.blazedemo.sections.Receipt;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

@JPage(url="/confirmation.php")
public class ConfirmationPage extends WebPage {
    public static final int VALID_DIFFERENCE = 100;
    public Receipt receipt;

    public ConfirmationPage waitUntilPageLoaded(){
        WebDriverWait wait = new WebDriverWait(this.getDriver(),10);
        wait.until(ExpectedConditions.urlContains("confirmation.php"));
        return this;
    }

    public ConfirmationPage checkOpenedPage(){
        this.checkOpened();
        return this;
    }

    public ConfirmationPage checkStatusText(String statusText){
        assertEquals(statusText, receipt.getStatusText());
        return this;
    }

    public ConfirmationPage checkAmountText(String amountText){
        assertEquals(amountText, receipt.getAmountText());
        return this;
    }

    public ConfirmationPage checkExpirationText(String expirationText){
        assertEquals(expirationText, receipt.getExpirationText());
        return this;
    }

    public ConfirmationPage checkAuthCodeText(String authCodeText){
        assertEquals(authCodeText, receipt.getAuthCodeText());
        return this;
    }
    public ConfirmationPage checkIdText(){
        assertTrue(receipt.getIdText().trim().length() > 0);
        return this;
    }

    public ConfirmationPage checkCardNumber(String cardNumber){
        String ActualCardNumberToken = cardNumber.substring( cardNumber.length() - 4 );
        String CurrentCardNumberToken = receipt.getCardNumberVal().substring( receipt.getCardNumberVal().length() - 4);
        assertEquals( ActualCardNumberToken, CurrentCardNumberToken );
        return this;
    }

    public Date getOrderDate() throws ParseException {
        // Устанавливаем формат даты, для парсинга строки с датой на странице брони
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        // парсим дату полученную со страницы
        Date orderDate =  formatter.parse(receipt.getOrderDateValue());
        return orderDate;
    }

    public ConfirmationPage checkOrderDate(Date date) throws ParseException {
        // т.к. время генерации страницы с бронью может незначительно отличаться от времени полученного во время
        // выполнения теста, то нам приходится сравнивать разницу временных меток
        // нам нужно чтобы разница во времени составляла меньше 10 секунд
        //
        // вычитаем из времени, полученного со страницы время полученное при выполнении теста
        // т.к. время указывается в милисекундах, то делим полученную разницу на 1000
        // проверяем, чтобы разница временных меток была меньше 10 секунд
        assertTrue((Math.abs(getOrderDate().getTime() - date.getTime())/1000) < VALID_DIFFERENCE);

        return this;
    }

}
