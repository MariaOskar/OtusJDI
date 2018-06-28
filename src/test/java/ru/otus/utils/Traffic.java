package ru.otus.utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.jdi.uitests.web.settings.WebSettings.getDriver;

public class Traffic {

    public static void write(){
        StringBuffer buffer = new StringBuffer();
        LogEntries logEntries = getDriver().manage().logs().get(LogType.PERFORMANCE);
        for(LogEntry logEntry: logEntries){
            buffer.append(logEntry.getLevel()+" "+logEntry.getMessage()+"\r\n");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        try {
            String content = buffer.toString();
            FileUtils.write(new File("target\\traffic\\"+format.format(new Date())+".txt"),content,"UTF-8");
            Allure.addAttachment("Трафик", "text/plain",content,"txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
