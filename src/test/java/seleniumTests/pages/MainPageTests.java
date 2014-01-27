package seleniumTests.pages;

import junit.framework.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumTests.pages.loginPages.LoginPage;

/**
 * Created by MikhailN on 06.01.14.
 */
public class MainPageTests extends TestBase {
    MainPage mainPage;
    LoginPage loginPage;

    @BeforeClass
    public void testInit() {

        // Load the page in the browser
        webDriver.get(websiteUrl);
        mainPage = PageFactory.initElements(webDriver, MainPage.class);
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
    }

    @AfterClass
    public void closeBrowserWindow(){
        webDriver.close();
    }

    @Test
    public void topLinksTest() throws InterruptedException {
        mainPage.getLatest().click();
        Assert.assertEquals(websiteUrl+"latest/0", webDriver.getCurrentUrl() );
        Assert.assertEquals("Wrong comment page title for latest page","// Последние", mainPage.getHThreeCommentPageTitle().getText());

        mainPage.getTop().click();
        Assert.assertEquals("Wrong comment page title for top page","// Лучшие:" +
                " за сутки,"+ " за 7 дней" +"," + " за месяц" + "," + " за всё время", mainPage.getHThreeCommentPageTitle().getText());
        Assert.assertEquals(websiteUrl+"daily/0", webDriver.getCurrentUrl());

        mainPage.getHot().click();
        Assert.assertEquals(websiteUrl+"hot/0", webDriver.getCurrentUrl());
        Assert.assertEquals("Wrong comment page title for latest page","// Горячие", mainPage.getHThreeCommentPageTitle().getText());

        mainPage.getRandomizer().click();
        Assert.assertEquals("// Комментарии "+"по рейтингу"+"," +
                " "+"по дате", mainPage.getHFourCommentPageTitle().getText());

        mainPage.getUpload().click();
        loginPage.getCloseButton().click();




        mainPage.getHot().click();
    }

}
