package engine;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.PerformsActions;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.input.model.TouchPoint;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ElementActions {

    AppiumDriver driver;
    WebDriverWait wait;
    WebElement webElement;

    public ElementActions(AppiumDriver driver){
        this.driver = driver;
    }

    public WebElement isMobileElementDisplayed(By element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
}

    public void tapButton(By objectToTap){
        webElement = isMobileElementDisplayed(objectToTap);
        try {
            webElement.click();
        }
        catch (Exception e){
            System.out.println("Exception while tapping button in"+e.getMessage());
        }
    }

    public void enterInput(By objectToFind, String value){
        webElement = isMobileElementDisplayed(objectToFind);
        try {
            webElement.sendKeys(value);
        }
        catch (Exception e){
            System.out.println("Exception while entering value in"+e.getMessage());
        }
    }

    public boolean isElementVisible(By objectToFind){
        try{
            return isMobileElementDisplayed(objectToFind).isDisplayed();
        }
        catch (Exception e){
            return false;
        }

    }

    public boolean swipeDown(By objectForSwipe,By objectToFind) {
        // Get screen size for dynamic swipe
        Dimension size = driver.manage().window().getSize();

        // Calculate swipe coordinates
        int startX = size.width / 2; // Center X
        int startY = (int) (size.height * 0.8); // Start near bottom
        int endY = (int) (size.height * 0.2); // End near top

        // Perform the swipe up action
        swipeScreen(objectForSwipe, startX, startY, startX, endY);

        return isElementVisible(objectToFind);
    }

    public boolean swipeUp(By objectForSwipe, By objectToFind) {
        // Get screen size for dynamic swipe
        Dimension size = driver.manage().window().getSize();

        // Calculate swipe coordinates
        int startX = size.width / 2; // Center X
        int endY = (int) (size.height * 0.8); // Start near bottom
        int startY = (int) (size.height * 0.2); // End near top

        // Perform the swipe up action
        swipeScreen(objectForSwipe, startX, startY, startX, endY);

        return isElementVisible(objectToFind);
    }


    public void swipeScreen(By objectToFind, int startX, int startY, int endX, int endY) {
        // Create an Actions instance
        Actions actions = new Actions(driver);

        // Perform the swipe
        actions
                .moveToElement(isMobileElementDisplayed(objectToFind) )// Move to any visible element to start the swipe
                .clickAndHold()
                .moveByOffset(startX, startY)
                .moveByOffset(endX - startX, endY - startY)
                .release()
                .perform();
    }

    public String getText(By objectToFind){
       return isMobileElementDisplayed(objectToFind).getText();
    }

    public String getTextFromList(By locator,int index){
        List<WebElement> element = driver.findElements(locator);
        return element.get(index).getText();
    }


}
