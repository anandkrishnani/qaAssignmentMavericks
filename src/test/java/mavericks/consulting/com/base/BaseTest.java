package mavericks.consulting.com.base;

import com.microsoft.playwright.Page;
import mavericks.consulting.com.factory.PlaywrightFactory;
import mavericks.consulting.com.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Properties;


public class BaseTest {

    private PlaywrightFactory playwrightFactory ;
    private Properties properties;
    private Page page;
    protected LoginPage loginPage;

    @BeforeTest
    public void setup(){
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.initProperties();
        page = playwrightFactory.initBrowser(properties);
        loginPage = new LoginPage(page);
    }

    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }

}
