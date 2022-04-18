package tests;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected FileInputStream inputStream;
    protected Properties prop;
    protected AndroidDriver<MobileElement> driver;
    protected AppiumDriverLocalService service;

    @BeforeSuite
    public void startAppiumServer()
    {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }


    @Parameters({"deviceName", "platformName","platformVersion"})
    @BeforeClass
    public void startDriver(@Optional("Pixel 3") String deviceName, @Optional("Android") String platformName, @Optional("12") String platformVersion) throws IOException {

        File propFile = new File("src/main/resources/config.properties");

        inputStream = new FileInputStream(propFile);
        prop = new Properties();
        prop.load(inputStream);

        File androidApp = new File(prop.getProperty("androidAppPath"));

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
        caps.setCapability(MobileCapabilityType.APP, androidApp.getAbsolutePath());

        driver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("appiumServerLink")), caps);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @AfterClass
    public void stopDriver()
    {
        driver.quit();
    }

    @AfterSuite
    public void stopAppiumServer()
    {
        service.stop();
    }
}
