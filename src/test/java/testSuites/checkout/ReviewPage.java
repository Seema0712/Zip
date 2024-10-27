package testSuites.checkout;

import engine.ElementActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ReviewPage extends ElementActions {

    public ReviewPage(AppiumDriver driver) {
        super(driver);
    }

    CheckoutPage checkoutPage;

    By deliveryAddress = AppiumBy.xpath("//*[@content-desc=\"checkout delivery address\"]/*");
    By paymentDetails =  AppiumBy.xpath("//*[@content-desc=\"checkout payment info\"]/*");
    By placeOrder = AppiumBy.accessibilityId("Place Order button");
    By successMsg = AppiumBy.xpath("//*[@text=\"Thank you for your order\"]");
    By productRow = AppiumBy.accessibilityId("product row");

    public String getFullName() throws InterruptedException {
        Thread.sleep(2000);
        swipeDown(productRow,deliveryAddress);
       return getTextFromList(deliveryAddress,1);
    }

    public String getAddress(){
        return getTextFromList(deliveryAddress,2);
    }

    public String getCityState(){
        return getTextFromList(deliveryAddress,3);
    }

    public String  getCountryZip(){
        return getTextFromList(deliveryAddress,4);
    }

    public String getCardName(){
        swipeDown(deliveryAddress,paymentDetails);
        return getTextFromList(paymentDetails,1);
    }

    public String getCardNumber(){
        return getTextFromList(paymentDetails,2);
    }

    public String getExpiry(){
        return getTextFromList(paymentDetails,3);
    }


    public void clickPlaceOrder(){
        tapButton(placeOrder);
    }

    public void verifySuccessMsg(){
        isElementVisible(successMsg);
    }


}
