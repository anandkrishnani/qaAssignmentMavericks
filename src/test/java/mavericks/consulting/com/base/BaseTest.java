package mavericks.consulting.com.base;

import com.microsoft.playwright.Page;
import mavericks.consulting.com.factory.PlaywrightFactory;
import mavericks.consulting.com.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;


public class BaseTest {

    private PlaywrightFactory playwrightFactory;
    protected Properties properties;
    private Page page;
    protected HomePage homePage;

    @BeforeTest
    public void setup() {
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.initProperties();
        page = playwrightFactory.initBrowser(properties);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }

}
