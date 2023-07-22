package page.seleniumtesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class LoginTest {
    private WebDriver driver;

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dilshan De Almeida\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        return new ChromeDriver();
    }

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }


    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    @Test//T1
    public void canNotLoginWithInvalidPassword() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("gabdimitrievski111@gmail.com", "wrong_password_test");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "The password you’ve entered is incorrect. Forgot Password"); // Invalid username or password

    }
    @Test//T2
    public void canNotLoginWithoutUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("", "");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "Invalid username or password");

    }

    @Test//T3
    public void shouldLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("gabdimitrievski111@gmail.com", "correct_password");
        assertTrue(new HomePage(driver).isLoaded());
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
