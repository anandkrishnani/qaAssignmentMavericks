package mavericks.consulting.com.factory;

import com.microsoft.playwright.*;
import mavericks.consulting.com.exception.BrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    private Properties properties;
    private Browser browser;
    private static final Logger logger = LoggerFactory.getLogger(PlaywrightFactory.class);

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
        switch (properties.getProperty("browser").toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(properties.getProperty("headless"))));
                logger.info("============running on chromium========");
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(properties.getProperty("headless"))));
                logger.info("===========running on firefox==========");
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(properties.getProperty("headless"))));
                logger.info("===========running on safari==========");
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(Boolean.parseBoolean(properties.getProperty("headless"))));
                logger.info("===========running on chrome==========");
                break;
            default:
                logger.error("{} browser is not supported",properties.getProperty("browser"));
                throw new BrowserException("Browser not supported by this framework");
        }

        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate(properties.getProperty("base-uri"));
        return page;
    }
}
