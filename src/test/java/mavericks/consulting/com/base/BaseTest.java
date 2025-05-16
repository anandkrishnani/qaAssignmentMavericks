package mavericks.consulting.com.base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import mavericks.consulting.com.factory.PlaywrightFactory;
import mavericks.consulting.com.pages.CartPage;
import mavericks.consulting.com.pages.HomePage;
import mavericks.consulting.com.pages.ProductPage;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {

    private PlaywrightFactory playwrightFactory;
    protected Properties properties;
    private Page page;
    protected HomePage homePage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    protected APIRequestContext context;
    protected Playwright playwright;

    @BeforeTest
    @Parameters({"testType","browser"})
    public void setup(@Optional("UI") String testType, @Optional String browserName) {
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.initProperties();
        playwright = Playwright.create();
        APIRequest request = playwright.request();
        context = request.newContext();
        if (testType.equalsIgnoreCase("UI")) {
            properties.setProperty("browser",browserName);
            page = playwrightFactory.initBrowser(properties);
            homePage = new HomePage(page);
        }
    }

    @AfterSuite
    @Parameters({"testType"})
    public void tearDown(@Optional("UI") String testType) {
        if (testType.equalsIgnoreCase("UI")) {
            page.context().browser().close();
        }
        playwright.close();
    }

}
