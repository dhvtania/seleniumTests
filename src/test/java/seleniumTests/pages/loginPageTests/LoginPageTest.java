package seleniumTests.pages.loginPageTests;

import junit.framework.Assert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumTests.pages.MainPage;
import seleniumTests.pages.loginPages.LoginPage;

/**
 * Created by MikhailN on 06.01.14.
 */
public class LoginPageTest extends LoginPageTestBase  {


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
    public void registrationWithWrongNickName() throws InterruptedException {
        //click "Вход" button
        clickLogin();

        enterWrongRegExpNickName(wrongRegExpNickNameShort);

        //sent password to password field
        enterPassword(password);

        enterCorrectAgainPassword(password);

        //enterCaptcha(captcha);

        //check that registration button is disaibled
        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        refreshPage();

    }

    @Test
    public void registrationWithWrongPasswordAgain(){
        clickLogin();

        enterCorrectUnusedNickName(unusedNickName);
        
        enterPassword(password);
        
        enterWrongPasswordAgain(wrongPasswordAgain);

        //enterCaptcha(captcha);

        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        refreshPage();
    }

    @Test
    public void registrationWithWrongNickNameAndPasswordAgain(){
        clickLogin();

        enterWrongRegExpNickName(wrongRegExpNickNameSymbol);

        enterPassword(password);

        enterWrongPasswordAgain(wrongPasswordAgain);

        //enterCaptcha(captcha);

        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        refreshPage();
    }

    @Test
    public void registrationWithoutClosingRegistrationForm() throws InterruptedException {
        clickLogin();

        enterWrongRegExpNickName(wrongRegExpNickNameShort);
        //sent wrongRegExpNickName captcha is dysplayed

        //sent password to the password field
        enterPassword(password);

        //sent password to the againPassword field
        enterCorrectAgainPassword(password);

        //enterCaptcha(captcha);

        //check that registration button is disaibled
        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        clearLoginPage();

        enterCorrectUnusedNickName(unusedNickName);

        enterPassword(password);

        enterWrongPasswordAgain(wrongPasswordAgain);

        //enterCaptcha(captcha);

        Assert.assertFalse("Registration button should be disabled", loginPage.getSubmitButton().isEnabled());

        clearLoginPage();

        //Can't use enterWrongRegExpNickName
        enterNickName(wrongRegExpNickNameSymbol);
        //check that loginInfo is dysplayed
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginInfo()));
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(), "Никнейм должен удовлетворять регэкспу: \\w{3,15}"));
        //check that captcha is dysplayed
        Assert.assertTrue(loginPage.getLoginCaptcha().isDisplayed());
        //check that password and againPassword fields have correct color
        Assert.assertEquals("Wrong password field color","rgba(255, 255, 255, 1)", loginPage.getPassword().getCssValue("background-color"));
        Assert.assertEquals("Wrong passwordAgain field color","rgba(255, 28, 8, 0.317647)" ,loginPage.getPasswordAgain().getCssValue("background-color"));

        enterPassword(password);

        enterWrongPasswordAgain(wrongPasswordAgain);

        //enterCaptcha(captcha);

        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        refreshPage();
    }


    @Test(enabled = false) //test is ignored until captcha is not permanent
    public void registrationWithoutErrors(){
        Assert.assertEquals("Wrong button text", "Вход", mainPage.getLoginButton().getText());
        clickLogin();
        enterCorrectUnusedNickName(unusedNickName);
        enterPassword(password);
        enterCorrectAgainPassword(password);
        //enterCaptcha();
        Assert.assertTrue("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());
        loginPage.getSubmitButton().click();
        Assert.assertTrue("User panel is not dysplayed", mainPage.getUserPanel().isDisplayed());
        Assert.assertEquals("Wrong nickname on user panel", unusedNickName, mainPage.getProfileLink().getText());
        Assert.assertTrue("Exit is not dysplayed", mainPage.getExit().isDisplayed());
    }
}
