package mavericks.consulting.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import mavericks.consulting.com.utilities.ElementUtilities;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductPage {
    private Page page;
    private ElementUtilities elementUtilities;
    public ProductPage(Page page) {
        this.page =page;
        elementUtilities =new ElementUtilities(page);
    }

    public void addToCart() {
        //assertThat(elementUtilities.getLocator("//a[text()='Add to cart']")).isEnabled();
        elementUtilities.getLocator("//a[text()='Add to cart']").waitFor(new Locator.WaitForOptions().setTimeout(5000));
        elementUtilities.click("//a[text()='Add to cart']");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void navigateToHomePage(){
        elementUtilities.click("//a[@href='index.html' and @class='nav-link']");
    }
}
