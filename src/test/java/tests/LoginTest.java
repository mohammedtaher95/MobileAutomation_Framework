package tests;


import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends TestBase {

    LoginPage loginObject;

    @Test
    public void UserCanLogin()
    {
        loginObject = new LoginPage(driver);
        loginObject.userLogin("hatem@gmail.com", "Test123");
    }
}
