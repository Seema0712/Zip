package testPages;

import engine.ElementActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;

public class LoginPage extends ElementActions {

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    By openMenu = AppiumBy.accessibilityId("open menu");
    By logIn = AppiumBy.accessibilityId("menu item log in");
    By username = AppiumBy.accessibilityId("Username input field");
    By password = AppiumBy.accessibilityId("Password input field");
    By loginButton = AppiumBy.accessibilityId("Login button");

    public void clickOpenMenu(){
        tapButton(openMenu);
    }

    public void clickLogin(){
        tapButton(logIn);
    }

    public void enterUsername(String userName){
        enterInput(username,userName);
    }

    public void enterPassword(String pswd){
        enterInput(password,pswd);
    }

    public void clickLoginButton(){
        tapButton(loginButton);
    }


}
