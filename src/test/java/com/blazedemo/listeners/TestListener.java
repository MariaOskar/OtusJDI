package com.blazedemo.listeners;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.otus.utils.PropertyHelper;
import ru.otus.utils.Screenshot;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.jdi.uitests.web.settings.WebSettings.getDriver;

public class TestListener implements ITestListener {
    Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        String screenshotFlag = PropertyHelper.getProperty("test.properties","test.screenshots");
        if(screenshotFlag!=null && screenshotFlag.equals("1")){
            Screenshot.take();
        }

        String logsFlag = PropertyHelper.getProperty("test.properties","test.logs");
        if(logsFlag!=null && logsFlag.equals("1")){

            logger.info(":::::::::::     " + iTestResult.getName()+"     :::::::::::");

            Throwable throwable = iTestResult.getThrowable();

            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            throwable.printStackTrace(printWriter);

            logger.error(throwable.getMessage());
            logger.error(stringWriter.getBuffer().toString());
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        String flag = PropertyHelper.getProperty("test.properties","test.traffic");
        if(flag!=null && flag.equals("1")){
            StringBuffer buffer = new StringBuffer();
            LogEntries logEntries = getDriver().manage().logs().get(LogType.PERFORMANCE);
            for(LogEntry logEntry: logEntries){
                buffer.append(logEntry.getLevel()+" "+logEntry.getMessage()+"\r\n");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            try {
                FileUtils.write(new File("target\\traffic\\"+format.format(new Date())+".txt"),buffer.toString(),"UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
