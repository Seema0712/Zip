package testSuites.login;

import engine.Driver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testPages.LoginPage;

public class Login extends Driver {

    LoginPage  loginPage;

    @Test
    @Parameters({"userName","password"})
    public void loginToDemoApp(String userName,String password){
        System.out.println("Login to Demo app Test");
        loginPage = new LoginPage(driver);
        loginPage.clickOpenMenu();
        loginPage.clickLogin();
        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

}
