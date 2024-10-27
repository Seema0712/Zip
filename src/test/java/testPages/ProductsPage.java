package testPages;

import engine.ElementActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.text.DecimalFormat;

public class ProductsPage extends ElementActions {

    public ProductsPage(AppiumDriver driver) {
        super(driver);
    }

    By sortButton = AppiumBy.accessibilityId("sort button");
    By priceDescending = AppiumBy.accessibilityId("priceDesc");
    By item;
    By addItem = AppiumBy.accessibilityId("counter plus button");
    By addToCartButton = AppiumBy.accessibilityId("Add To Cart button");
    By catalog = AppiumBy.accessibilityId("menu item catalog");
    By cartIcon = AppiumBy.accessibilityId("cart badge");
    By itemForSwipe = AppiumBy.xpath("//*[@text=\"Sauce Labs Bolt T-Shirt\"]");
    By colorCircle;
    By amountCountValue = AppiumBy.androidUIAutomator("new UiSelector().description(\"counter amount\").childSelector(new UiSelector())");
    By itemPrice = AppiumBy.accessibilityId("product price");

    public String getTotalItemPrice() {
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedValue = df.format(totalItemPrice);
        return formattedValue;
    }

    public String getTotalQty() {
        return String.valueOf(totalQty);
    }

    float totalItemPrice = 0;
    int totalQty = 0;

    public void setIdentifierItem(String itemName){

        item = AppiumBy.xpath("//*[@text=\""+itemName+"\"]");
    }

    public void setIdentifierColor(String color){
        colorCircle  = AppiumBy.accessibilityId(""+color+" circle");
    }

    public void clickSortButton(){
        tapButton(sortButton);
    }

    public void clickPriceDescending(){
        tapButton(priceDescending);
    }

    public void selectItem(String itemName,int qty,String color){
        setIdentifierItem(itemName);
        if(isElementVisible(item))
        {
            System.out.println("Item is displayed");
            tapButton(item);
            clickAddItem(qty,color);
        }
        else if(swipeDown(itemForSwipe,item)){
            System.out.println("Item is displayed after swipe");
            tapButton(item);
            clickAddItem(qty,color);
        }
       else if(swipeUp(itemForSwipe,item)){
            System.out.println("Item is displayed after swipe");
            tapButton(item);
            clickAddItem(qty,color);
        }
       else{
            System.out.println("Item is not displayed");
        }

    }

    public void clickAddItem(int qty,String color){
        setIdentifierColor(color);
        totalItemPrice += qty*(Float.parseFloat(getText(itemPrice).replace("$","")));
        totalQty +=qty;
        swipeDown(colorCircle,amountCountValue);
        int counterValue =  Integer.valueOf(getText(amountCountValue));
        if(counterValue==qty)
        {
            System.out.println("Counter is same as qty required");
        }
        else{
            int newQty = qty  - counterValue;
            while(newQty>0){
                tapButton(addItem);
                newQty--;
            }
            System.out.println("Counter is modified according to qty");
        }

    }

    public void clickAddToCart(){
        tapButton(addToCartButton);
    }

    public void clickCatalog(){
        tapButton(catalog);
    }

    public void clickCartIcon() throws InterruptedException {
        Thread.sleep(3000);
        tapButton(cartIcon);
    }

    public void selectColor(String colorName){
        setIdentifierColor(colorName);
        tapButton(colorCircle);
    }

}
