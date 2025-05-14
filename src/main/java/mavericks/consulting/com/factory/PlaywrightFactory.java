package mavericks.consulting.com.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    Properties properties;

    /**
     *  This method is responsible for initializing the properties
     * @return properties
     */
    public Properties initProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    /**
     * This method is responsible for initializing the Browser
     * @param properties
     * @return Page instance
     */
    public Page initBrowser(Properties properties) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate(properties.getProperty("base-uri"));
        return page;
    }
}
