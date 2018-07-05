package dummy.test;

import com.blazedemo.BaseTest;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static com.epam.jdi.uitests.web.settings.WebSettings.getDriver;


public class DummyTest extends BaseTest {
    @Test(description = "Фиктивный тест №2")
    @Description("Фиктивный тест для проверки выполнения тестов в многопоточном режиме")
    public void test(){
        getDriver().get("https://google.com/");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().findElement(By.cssSelector("input[name=q]")).sendKeys("Selenoid");
        getDriver().findElement(By.cssSelector("input[type=submit]:nth-of-type(1)")).submit();
    }
}
