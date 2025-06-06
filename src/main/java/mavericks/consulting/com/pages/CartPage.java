package mavericks.consulting.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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

    public void deleteCartItems() {
        List<Locator> cartlist = page.locator("//*[@id='tbodyid']/tr/td[4]/a").all();
        for (Locator l : cartlist) {
            l.click();
            l.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        }
    }

    public String getProductPrice(String product){
       String locatorString = "(//tbody[@id='tbodyid']/tr/td[text()='"+product+"']/following-sibling::td)[1]";
       return elementUtilities.getText(locatorString);
    }
}
