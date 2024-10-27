package testSuites.checkout;

import engine.ElementActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CheckoutPage extends ElementActions {


    public CheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    By fullName = AppiumBy.accessibilityId("Full Name* input field");
    By addressLine1 = AppiumBy.accessibilityId("Address Line 1* input field");
    By addressLine2 = AppiumBy.accessibilityId("Address Line 2 input field");
    By city = AppiumBy.accessibilityId("City* input field");
    By state = AppiumBy.accessibilityId("State/Region input field");
    By zipCode = AppiumBy.accessibilityId("Zip Code* input field");
    By country = AppiumBy.accessibilityId("Country* input field");
    By toPayment = AppiumBy.accessibilityId("To Payment button");
    By cardNumber  = AppiumBy.accessibilityId("Card Number* input field");
    By expirationDate = AppiumBy.accessibilityId("Expiration Date* input field");
    By securityCode = AppiumBy.accessibilityId("Security Code* input field");
    By reviewOrder = AppiumBy.accessibilityId("Review Order button");

    String content;

    public JSONObject getTestData() {
        return checkoutDetails;
    }

    JSONObject testData;
    JSONObject checkoutDetails;

    public JSONObject getPaymentDetails() {
        return paymentDetails;
    }

    JSONObject paymentDetails;

    public void clickToPayment(){
        tapButton(toPayment);
    }

    public void enterAddressDetails() {
        try
        {
            content = new String(Files.readAllBytes(Paths.get("src/test/resources/testData.json")));
            testData = new JSONObject(content);
            checkoutDetails = testData.getJSONObject("checkoutDetails");
            enterInput(fullName,checkoutDetails.getString("FullName"));
            enterInput(addressLine1,checkoutDetails.getString("AddressLine1"));
            enterInput(addressLine2,checkoutDetails.getString("AddressLine2"));
            enterInput(city, checkoutDetails.getString("City"));
            enterInput(state,checkoutDetails.getString("State"));
            swipeDown(city,zipCode);
            enterInput(zipCode,checkoutDetails.getString("Zipcode"));
            enterInput(country,checkoutDetails.getString("Country"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void enterPaymentDetails() {
        try
        {
            paymentDetails = testData.getJSONObject("paymentDetails");
            Thread.sleep(2000);
            enterInput(fullName,paymentDetails.getString("FullName"));
            enterInput(cardNumber,paymentDetails.getString("CardNumber"));
            enterInput(expirationDate,paymentDetails.getString("ExpirationDate"));
            enterInput(securityCode, paymentDetails.getString("SecurityCode"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void clickReviewOrder(){
        tapButton(reviewOrder);
    }


}
