package testSuites.products;

import engine.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;
import testPages.LoginPage;
import testPages.ProductsPage;
import testSuites.cart.CartPage;
import testSuites.checkout.CheckoutPage;
import testSuites.checkout.ReviewPage;

public class Products extends Driver {

    ProductsPage productsPage;
    LoginPage loginPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ReviewPage reviewPage;
    String item1 = "Sauce Labs Onesie";
    String item2 = "Sauce Labs Backpack";
    String color = "black";

    @Test(priority = 0)
    public void productCatalogue() throws InterruptedException {
        productsPage = new ProductsPage(driver);
        loginPage = new LoginPage(driver);
        //sorting items
        productsPage.clickSortButton();
        productsPage.clickPriceDescending();
        //select sauce labs onesie
        productsPage.selectItem(item1,2,color);
        productsPage.clickAddToCart();
        productsPage.selectColor(color);
        //select sauce labs onesie  with different color
        productsPage.clickAddItem(3,color);
        productsPage.clickAddToCart();
        loginPage.clickOpenMenu();
        productsPage.clickCatalog();
        //select sauce labs Bolt T-shirt
        productsPage.selectItem(item2,1,color);
        productsPage.clickAddToCart();
        loginPage.clickOpenMenu();
        productsPage.clickCatalog();
        //click cart  Icon
        productsPage.clickCartIcon();
    }

    @Test(dependsOnMethods = "productCatalogue")
    public void cartCalculations(){
        cartPage = new CartPage(driver);
        cartPage.verifyCartPage();
        //verify the qty and price in cart page
        Assert.assertEquals(productsPage.getTotalQty(),cartPage.getCartQty().replace("items","").replace(" ",""));
        Assert.assertEquals(    "$"+productsPage.getTotalItemPrice(),cartPage.getTotalPrice());
        cartPage.clickCheckout();
    }

    @Test(dependsOnMethods = "cartCalculations")
    public void checkout(){
        checkoutPage = new CheckoutPage(driver);
        //enter address
        checkoutPage.enterAddressDetails();
        checkoutPage.clickToPayment();
        //enter payment details
        checkoutPage.enterPaymentDetails();
        checkoutPage.clickReviewOrder();
    }

    @Test(dependsOnMethods = "checkout")
    public void reviewAndPlaceOrder() throws InterruptedException {
        reviewPage = new ReviewPage(driver);
        //Verify the details entered in checkout screen is matching
        Assert.assertEquals(checkoutPage.getTestData().getString("FullName"),reviewPage.getFullName());
        Assert.assertEquals((checkoutPage.getTestData().getString("AddressLine1")+", "+checkoutPage.getTestData().getString("AddressLine2")),reviewPage.getAddress());
        Assert.assertEquals((checkoutPage.getTestData().getString("City")+", "+checkoutPage.getTestData().getString("State")),reviewPage.getCityState());
        Assert.assertEquals((checkoutPage.getTestData().getString("Country")+", "+checkoutPage.getTestData().getString("Zipcode")),reviewPage.getCountryZip());
        Assert.assertEquals(checkoutPage.getPaymentDetails().getString("FullName"),reviewPage.getCardName());
        Assert.assertEquals(checkoutPage.getPaymentDetails().getString("CardNumber"),reviewPage.getCardNumber());
        Assert.assertEquals("Exp: "+checkoutPage.getPaymentDetails().getString("ExpirationDate"),reviewPage.getExpiry());
        reviewPage.clickPlaceOrder();
        reviewPage.verifySuccessMsg();
   }

}


//change it  getTotalQty -3 ,6
//price 45.97 its 69.94