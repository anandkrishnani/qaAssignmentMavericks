package mavericks.consulting.com.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ElementUtilities {

    Page page;

    public ElementUtilities(Page page) {
        this.page = page;

    }

    public String getText(String locatorString) {
        return getLocator(locatorString).textContent();
    }

    public void fill(String locatorString, String value) {
        getLocator(locatorString).fill(value);
    }

    public void click(String locatorString) {
        page.locator(locatorString).click();
    }

    public Locator getLocator(String locatorString) {
        return page.locator(locatorString);
    }

}
