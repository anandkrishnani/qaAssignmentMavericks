package mavericks.consulting.com.tests;

import mavericks.consulting.com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        String actualNameOfUser = homePage.doLogin(properties.getProperty("login"), properties.getProperty("password"));
        Assert.assertEquals(actualNameOfUser, "Welcome " + properties.getProperty("login"));
        homePage.doLogout();
    }

    @DataProvider
    public Object[][] getLoginData() {
        return new Object[][]{
                {"ddd", " "},
                {" ", " ddd"},
                {" asgdgd", "3%$#"},
                {"@#$%", "werfd"},
                {" ", " "},
//                {null, null}
        };
    }

    @Test(dataProvider = "getLoginData")
    public void negativeLoginTest(String login, String password) {
        String actualString = homePage.doLogin(login, password);
        Assert.assertEquals(actualString, null);

    }

    @Test
    public void logoutTest() {
        homePage.doLogin(properties.getProperty("login"), properties.getProperty("password"));
        boolean SignUpLocatorisVisible = homePage.doLogout();
        Assert.assertTrue(SignUpLocatorisVisible);
    }
}
