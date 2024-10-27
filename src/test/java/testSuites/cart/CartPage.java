package testSuites.cart;

import engine.ElementActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class CartPage extends ElementActions {

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

   By cartTitle = AppiumBy.xpath("//*[@text=\"My Cart\"]");
   By totalPriceLocator = AppiumBy.accessibilityId("total price");
   By productPriceLocator = AppiumBy.xpath("(//android.widget.TextView[@content-desc=\"product price\"])");
   By cartQtyLocator = AppiumBy.accessibilityId("total number");
   String totalPrice = "";
   String totalQty = "";
   By proceedToCheckout = AppiumBy.accessibilityId("Proceed To Checkout button");

   public void verifyCartPage(){
       isElementVisible(cartTitle);
   }

   public String getTotalPrice(){
        return getText(totalPriceLocator);
   }

    public String getProductPrice(){
        return getText(productPriceLocator);
    }

    public String getCartQty(){
        return getText(cartQtyLocator);
    }

    public void clickCheckout(){
       tapButton(proceedToCheckout);
    }


}
