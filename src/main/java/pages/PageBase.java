package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class PageBase {

    protected AndroidDriver<MobileElement> driver;

    public PageBase(AndroidDriver<MobileElement> driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    public void ClickButton(MobileElement button)
    {
        button.click();
    }

    protected static void Fill_in_Text(MobileElement textElement , String value)
    {
        textElement.sendKeys(value);
    }
}
