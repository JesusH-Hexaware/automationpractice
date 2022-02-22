package Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.base;

import java.io.IOException;

public class Listener extends base implements ITestListener {
    public static Logger log = LogManager.getLogger(base.class.getName());

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Start test " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Passed test " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Failed test " + result.getName() + " screenshot captured!");

        WebDriver driver = null;

        String testMethodName = result.getMethod().getMethodName();

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            getScreenShotPath(testMethodName, driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Skipped test " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Start test execution " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finish test execution " + context.getName());
    }
}
