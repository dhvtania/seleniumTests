package seleniumTests.pages.loginPageTests;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumTests.pages.MainPage;
import seleniumTests.pages.loginPages.GoogleAuthenticationPage;
import seleniumTests.pages.loginPages.LoginPage;
import seleniumTests.pages.loginPages.TwitterAuthenticationPage;

/**
 * Created by MikhailN on 1/14/14.
 */
public class LoginPageSocialButtonsAuthenticationTest extends LoginPageTestBase {
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
    public void registrationWithClosingAuthenticationFormUsingGoogleAccount() throws InterruptedException {
        clickLogin();

        //switch to uLogin iframe
        switchToULoginIFrame();

        //Save window handler for developerslfe.ru page
        String windowHandleBefore = webDriver.getWindowHandle();

        //click google button
        loginPage.getGoogleButton().click();

        //switch to google Authentication window
        switchToNewOpenedWindow();

        //close authentication window
        webDriver.close();
        webDriver.switchTo().window(windowHandleBefore);

        refreshPage();

        Assert.assertEquals("Вход", mainPage.getLoginButton().getText());
    }



    @Test(enabled = true)
    public void registrationWithCancelOnSiteUsingGoogleAccount() throws InterruptedException {
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
        googleAuthenticationPage.getEmailField().sendKeys("DLSeleniumTests@gmail.com");

        //Enter password
        googleAuthenticationPage.getPasswordField().click();
        googleAuthenticationPage.getPasswordField().sendKeys("DLSeleniumTest");

        //Click sign in button
        googleAuthenticationPage.getSignInButton().click();

        //Switch back to developerslfe.ru page
        webDriver.switchTo().window(windowHandleBefore);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("password")));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getNickName()));

        loginPage.getNickName().sendKeys("\\");
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(), "Никнейм должен удовлетворять регэкспу: \\w{3,15}"));

        loginPage.getNickName().clear();
        loginPage.getNickName().sendKeys("selenium");
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(),"Приятно видеть новых людей!\n" +
                "Этот никнейм еще не занят."));

        loginPage.getNickName().clear();
        loginPage.getNickName().sendKeys("RegSelenium");
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(),"Этот никнейм уже занят.\n" +
                "Выберите другой"));

        loginPage.getCloseButton().click();

        Assert.assertEquals("Вход", mainPage.getLoginButton().getText());
    }

    @Test(enabled = true)
    public void registrationWithClosingAuthenticationFormUsingTwitterAccount() throws InterruptedException {
        clickLogin();

        //switch to uLogin iframe
        switchToULoginIFrame();

        //Save window handler for developerslfe.ru page
        String windowHandleBefore = webDriver.getWindowHandle();

        //click google button
        loginPage.getTwitterButton().click();

        //switch to google Authentication window
        switchToNewOpenedWindow();

        //close authentication window
        twitterAuthenticationPage.getChancelButton().click();
        webDriver.close();

        webDriver.switchTo().window(windowHandleBefore);

        Assert.assertEquals("Вход", mainPage.getLoginButton().getText());
    }

    @Test(enabled = true)
    public void registrationWithCancelOnSiteUsingTwitterAccount() throws InterruptedException {
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
    //    twitterAuthenticationPage.getEmailField().click();
        twitterAuthenticationPage.getEmailField2().sendKeys("registerdseleniumtest@gmail.com");

        //Enter password
        twitterAuthenticationPage.getPasswordField().click();
        twitterAuthenticationPage.getPasswordField().sendKeys("DLSeleniumTest");

        //Click sign in button
        twitterAuthenticationPage.getSignInButton().click();

        //Switch back to developerslfe.ru page
        webDriver.switchTo().window(windowHandleBefore);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("password")));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getNickName()));

        loginPage.getNickName().sendKeys("\\");
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(), "Никнейм должен удовлетворять регэкспу: \\w{3,15}"));

        loginPage.getNickName().clear();
        loginPage.getNickName().sendKeys("selenium");
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(),"Приятно видеть новых людей!\n" +
                "Этот никнейм еще не занят."));

        loginPage.getNickName().clear();
        loginPage.getNickName().sendKeys("RegSelenium");
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(),"Этот никнейм уже занят.\n" +
                "Выберите другой"));

        loginPage.getCloseButton().click();

        Assert.assertEquals("Вход", mainPage.getLoginButton().getText());
    }










}
