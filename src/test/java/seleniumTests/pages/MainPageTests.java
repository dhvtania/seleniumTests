package seleniumTests.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;

/**
 * Created by MikhailN on 06.01.14.
 */
public class MainPageTests extends TestBase {
    MainPage mainPage;

    @BeforeClass
    public void testInit() {

        // Load the page in the browser
        webDriver.get(websiteUrl);
        mainPage = PageFactory.initElements(webDriver, MainPage.class);
    }

}
