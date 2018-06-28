package ru.otus.utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.jdi.uitests.web.settings.WebSettings.getDriver;

public class Screenshot {
    public static void take(){
        WebDriver driver = getDriver();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String filename = "screenshot_"+format.format(new Date())+".png";
            FileUtils.copyFile(scrFile, new File("target\\screenshots\\"+filename));
            byte[] content= FileUtils.readFileToByteArray(scrFile);
            Allure.addByteAttachmentAsync( filename, "image/png", ()->content );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
