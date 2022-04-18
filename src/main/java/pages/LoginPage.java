package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends PageBase{

    public LoginPage(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@index = '1']")
    private MobileElement EmailField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index = '2']")
    private MobileElement PasswordField;

    @AndroidFindBy(xpath = "//*[@text = 'Login']")
    private MobileElement LoginButton;

    public void userLogin(String Email, String Password)
    {
        Fill_in_Text(EmailField, Email);
        Fill_in_Text(PasswordField, Password);
        ClickButton(LoginButton);
    }
}
