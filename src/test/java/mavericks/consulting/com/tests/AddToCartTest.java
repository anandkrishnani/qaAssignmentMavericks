package mavericks.consulting.com.tests;

import mavericks.consulting.com.base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AddToCartTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(AddToCartTest.class);
    @DataProvider
    public Object[][] productCartDataProvider() {
        return new Object[][]
                {
                        {"Samsung galaxy s6", "Sony vaio i5", "Apple monitor 24"}

                };
    }

    @Test(dataProvider = "productCartDataProvider")
    public void multipleProductCartTest(String product1, String product2, String product3) {
        homePage.doLogin(properties.getProperty("login"), properties.getProperty("password"));
        productPage = homePage.selectProduct(product1);
        productPage.addToCart();
        productPage.navigateToHomePage();
        homePage.navigateToCategory("Laptops");
        productPage = homePage.selectProduct(product2);
        productPage.addToCart();
        productPage.navigateToHomePage();
        homePage.navigateToCategory("Monitors");
        productPage = homePage.selectProduct(product3);
        productPage.addToCart();
        productPage.navigateToHomePage();
        cartPage = homePage.navigateToCartPage();
        List<String> cartProductList = cartPage.getProductList();
        for ( String s: cartProductList){
            logger.info("{} Product added to the cart",s);
        }
        Assert.assertTrue(cartProductList.contains(product1));
        Assert.assertTrue(cartProductList.contains(product2));
        Assert.assertTrue(cartProductList.contains(product3));
        cartPage.deleteCartItems();
    }
}
