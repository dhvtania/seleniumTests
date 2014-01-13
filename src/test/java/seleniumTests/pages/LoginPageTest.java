package seleniumTests.pages;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Set;

/**
 * Created by MikhailN on 06.01.14.
 */
public class LoginPageTest extends LoginPageTestBase  {
    MainPage mainPage;
    LoginPage loginPage;
    private final CharSequence wrongRegExpNickNameShort = "ab";
    private final CharSequence wrongRegExpNickNameSymbol = "\\asdf";
    private final CharSequence password = "test";
    private final CharSequence unusedNickName = "unusedNickName";
    private final CharSequence wrongPasswordAgain = "t";
    private final CharSequence captcha = "FG4h";

    @BeforeClass
    public void testInit() {

        // Load the page in the browser
        webDriver.get(websiteUrl);
        mainPage = PageFactory.initElements(webDriver, MainPage.class);
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
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

    @Test(enabled = false) //test is ignored until captcha is not permanent
    public void registrationUsingGoogleAccount() throws InterruptedException {
        Set <String> a;
        clickLogin();
        WebElement iframe = webDriver.findElement(By.cssSelector("iframe[id*='easyXDM_default']"));
        webDriver.switchTo().frame(iframe);
        webDriver.findElement(By.cssSelector("#google")).click();
        a = webDriver.getWindowHandles();
        Thread.sleep(1000);

    }

      
    private void clickLogin() {
        mainPage.getLoginButton().click();
        Assert.assertTrue(mainPage.getLoginForm().isDisplayed());
    }

    private void enterPasswordAgain(CharSequence password) {
        loginPage.getPasswordAgain().click();
        loginPage.getNickName().clear();
        loginPage.getPasswordAgain().sendKeys(password);
    }

    private void enterPassword(CharSequence password) {
        loginPage.getPassword().click();
        loginPage.getNickName().clear();
        loginPage.getPassword().sendKeys(password);
        //check that againPassword field has correct color
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
    }

    private void enterNickName(CharSequence nickName) {
        loginPage.getNickName().click();
        loginPage.getNickName().clear();
        loginPage.getNickName().sendKeys(nickName);
    }

    private void enterCorrectUnusedNickName(CharSequence nickName) {
        enterNickName(nickName);
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginInfo()));
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(),"Приятно видеть новых людей!\n" +
                "Этот никнейм еще не занят."));

        //check that captcha is dysplayed
        Assert.assertTrue(loginPage.getLoginCaptcha().isDisplayed());

        //check that password and againPassword fields have correct color
        Assert.assertEquals("Wrong password field color","rgba(255, 255, 255, 1)", loginPage.getPassword().getCssValue("background-color"));
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
    }

    private void enterWrongRegExpNickName(CharSequence wrongNickName) {
        //sent wrongRegExpNickName captcha is dysplayed
        enterNickName(wrongNickName);

        //check that loginInfo is dysplayed
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginInfo()));
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(), "Никнейм должен удовлетворять регэкспу: \\w{3,15}"));

        //check that captcha is dysplayed
        Assert.assertTrue(loginPage.getLoginCaptcha().isDisplayed());

        //check that password and againPassword fields have correct color
        Assert.assertEquals("Wrong password field color","rgba(255, 255, 255, 1)", loginPage.getPassword().getCssValue("background-color"));
        Assert.assertEquals("Wrong passwordAgain field color","rgba(255, 255, 255, 1)",loginPage.getPasswordAgain().getCssValue("background-color"));
    }

    private void enterCorrectAgainPassword(CharSequence password) {
        //sent password to againPassword field
        enterPasswordAgain(password);

        //check that againPassword field has correct color
        Assert.assertEquals("Wrong passwordAgain field color", "rgba(255, 255, 255, 1)", loginPage.getPasswordAgain().getCssValue("background-color"));
    }

    private void enterWrongPasswordAgain(CharSequence wrongPasswordAgain) {
        enterPasswordAgain(wrongPasswordAgain);

        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
    }

    private void clearLoginPage() {
        loginPage.getNickName().clear();
        loginPage.getPassword().clear();
        loginPage.getPasswordAgain().clear();
        //loginPage.getLoginCaptcha().clear();
    }

    private void enterCaptcha(CharSequence captcha) {
        loginPage.getCaptchaInputField().sendKeys(captcha);
    }






}
