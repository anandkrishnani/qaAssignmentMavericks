package mavericks.consulting.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import mavericks.consulting.com.utilities.ElementUtilities;

import java.util.List;

public class CartPage {
    private Page page;
    private ElementUtilities elementUtilities;
    public CartPage(Page page) {
        this.page =page;
        elementUtilities =new ElementUtilities(page);
    }

    public List<String> getProductList(){
        elementUtilities.getLocator("//*[@id='tbodyid']/tr[1]").waitFor(new Locator.WaitForOptions().setTimeout(5000));
        return page.locator("//tbody[@id='tbodyid']/tr/td[2]").allInnerTexts();

    }
}
