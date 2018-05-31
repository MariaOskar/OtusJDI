package com.blazedemo;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeSuite;
import java.util.Locale;
import java.util.TimeZone;

public class BaseTest extends TestNGBase {
    @BeforeSuite()
    public static void setUp(){
        WebSite.init(BlazeDemoSite.class);

        // Устанавливаем часовой пояс, соответствующий временному поясу, в котором генерируется страница,
        // для получения аналогичного времени
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // Устанавливаем локаль, соответствующую дате, на странице с бронью
        Locale.setDefault(new Locale("en", "US"));
    }
}
