package utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import tests.TestBase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Report{

    public static ExtentReports extent;
    public static ExtentTest logger;
    ScreenShotHelper helper;

    public void setUpReport()
    {
        extent = new ExtentReports("Reports/index.html");
        extent.addSystemInfo("Framework Type", "Appium POM Framework");
        extent.addSystemInfo("Author", "Mohammed Taher");
        extent.addSystemInfo("Environment", "Android");
        extent.addSystemInfo("App", "QACart-ToDo App");
    }

    public void FinishReport()
    {
        extent.flush();
    }

    public void LogTest(Method method)
    {
        logger = extent.startTest(method.getName());
    }

    public void SaveResults(ITestResult result, AndroidDriver<MobileElement> driver)
    {
        if(result.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(LogStatus.PASS, "Test Passed");
        }
        else if(result.getStatus() == ITestResult.FAILURE)
        {
            logger.log(LogStatus.FAIL, "Test Failed");
            logger.log(LogStatus.FAIL, result.getThrowable());
            String fullPath = System.getProperty("user.dir") + String.valueOf(ScreenShotHelper.CaptureScreenshot(driver, result.getName()));
            logger.log(LogStatus.FAIL, logger.addScreenCapture(fullPath));
        }
        else
        {
            logger.log(LogStatus.SKIP, "Test Skipped");
        }
    }

}
