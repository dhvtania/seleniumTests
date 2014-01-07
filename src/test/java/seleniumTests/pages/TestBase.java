package seleniumTests.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import seleniumTests.util.PropertyLoader;
import seleniumTests.webdriver.WebDriverFactory;

/*
 * Base class for all the test classes
 * 
 * @author Sebastiano Armeli-Battana
 */

public class TestBase {
    protected WebDriver webDriver;
    protected WebDriverWait wait;

    protected String websiteUrl;

    protected String browser;

    @BeforeClass
    public void init() {
        websiteUrl = PropertyLoader.loadProperty("site.url");
        browser = PropertyLoader.loadProperty("browser.name");
        webDriver = WebDriverFactory.getInstance(browser);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, 60);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    protected void refreshPage(){
        webDriver.navigate().refresh();
    }
}
