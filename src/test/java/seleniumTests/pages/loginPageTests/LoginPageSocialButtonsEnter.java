package seleniumTests.pages.loginPageTests;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumTests.pages.MainPage;
import seleniumTests.pages.Page;
import seleniumTests.pages.loginPages.GoogleAuthenticationPage;
import seleniumTests.pages.loginPages.LoginPage;
import seleniumTests.pages.loginPages.TwitterAuthenticationPage;

/**
 * Created by MikhailN on 1/15/14.
 */
public class LoginPageSocialButtonsEnter extends LoginPageTestBase {

    @BeforeClass
    public void testInit() {

        // Load the page in the browser
        webDriver.get(websiteUrl);
        mainPage = PageFactory.initElements(webDriver, MainPage.class);
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        twitterAuthenticationPage = PageFactory.initElements(webDriver, TwitterAuthenticationPage.class);
        googleAuthenticationPage = PageFactory.initElements(webDriver, GoogleAuthenticationPage.class);
    }

    @Test(enabled = true)
    public void enterUsingRegisteredGoogleAccount() throws InterruptedException {
        clickLogin();

        //switch to uLogin iframe
        switchToULoginIFrame();

        //Save window handler for developerslfe.ru page
        String windowHandleBefore = webDriver.getWindowHandle();

        //click google button
        loginPage.getGoogleButton().click();

        //switch to google Authentication window
        switchToNewOpenedWindow();

        //Enter email
        googleAuthenticationPage.getEmailField().click();
        googleAuthenticationPage.getEmailField().sendKeys("registerdseleniumtest@gmail.com");

        //Enter password
        googleAuthenticationPage.getPasswordField().click();
        googleAuthenticationPage.getPasswordField().sendKeys("DLSeleniumTest");

        //Click sign in button
        googleAuthenticationPage.getSignInButton().click();

        //Switch back to developerslfe.ru page
        webDriver.switchTo().window(windowHandleBefore);

        //Check that you logged on with correct profile name.
        wait.until(ExpectedConditions.textToBePresentInElement(mainPage.getProfileLink(), "RegSelenium"));
        Assert.assertEquals("Wrong profile name", "RegSelenium", mainPage.getProfileLink().getText());

        mainPage.getExit().click();
        wait.until(ExpectedConditions.visibilityOf(mainPage.getLoginButton()));
        Assert.assertEquals("Вход", mainPage.getLoginButton().getText());
    }

    @Test
    public void enterUsingRegisteredTwitterAccount() throws InterruptedException {
        clickLogin();

        //switch to uLogin iframe
        switchToULoginIFrame();

        //Save window handler for developerslfe.ru page
        String windowHandleBefore = webDriver.getWindowHandle();

        //click google button
        loginPage.getTwitterButton().click();

        //switch to google Authentication window
        switchToNewOpenedWindow();

        //Enter email

        twitterAuthenticationPage.getEmailField2().sendKeys("DLSeleniumTests@gmail.com");

        //Enter password
        twitterAuthenticationPage.getPasswordField().click();
        twitterAuthenticationPage.getPasswordField().sendKeys("DLSeleniumTest");

        //Click sign in button
        twitterAuthenticationPage.getSignInButton().click();

        //Switch back to developerslfe.ru page
        webDriver.switchTo().window(windowHandleBefore);

        //Check that you logged on with correct profile name.
        wait.until(ExpectedConditions.textToBePresentInElement(mainPage.getProfileLink(), "RegSelenium"));
        Assert.assertEquals("Wrong profile name", "DLRegTwttr", mainPage.getProfileLink().getText());

        mainPage.getExit().click();
        wait.until(ExpectedConditions.visibilityOf(mainPage.getLoginButton()));
        Assert.assertEquals("Вход", mainPage.getLoginButton().getText());
    }

}
