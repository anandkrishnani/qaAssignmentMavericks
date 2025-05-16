package mavericks.consulting.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import mavericks.consulting.com.utilities.ElementUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
    private Page page;
    private ElementUtilities elementUtilities;
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
    public HomePage(Page page) {
        this.page =page;
        elementUtilities = new ElementUtilities(page);
    }

    public String getPageTitle(){
       return page.title();
    }

    public String getPageUrl(){
        return page.url();
    }

    public String doLogin(String username, String password){
        elementUtilities.click("#login2");
        elementUtilities.fill("#loginusername",username);
        elementUtilities.fill("#loginpassword", password);
        elementUtilities.click("//button[text() = 'Log in']");
        try{
           Locator nameOfUser= elementUtilities.getLocator("#nameofuser");
            nameOfUser.waitFor(new Locator.WaitForOptions().setTimeout(5000).setState(WaitForSelectorState.VISIBLE));
            String actualLoginString = elementUtilities.getText("#nameofuser");
            return actualLoginString;
        }
        catch (PlaywrightException e){
            logger.info("Login unsuccessful");
            elementUtilities.click("//div[@id='logInModal']//button[text()='Close']");
            return null ;
        }
    }

    public boolean doLogout(){
        elementUtilities.click("#logout2");
        boolean isVisible = elementUtilities.getLocator("#signin2").isVisible();
        return isVisible;
    }

    public ProductPage selectProduct( String product){
        elementUtilities.click(String.format("//a[text()='%s']",product));
        return new ProductPage(page);
    }

    public void navigateToCategory(String category){
        elementUtilities.getLocator(String.format("//a[text()='%s']",category)).waitFor(new Locator.WaitForOptions().setTimeout(5000));
        elementUtilities.click(String.format("//a[text()='%s']",category));
    }

    public CartPage navigateToCartPage(){
        elementUtilities.click("#cartur");
        return new CartPage(page);
    }
}
