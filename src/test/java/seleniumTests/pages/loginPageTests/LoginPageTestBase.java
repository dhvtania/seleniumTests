package seleniumTests.pages.loginPageTests;

import junit.framework.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import seleniumTests.pages.MainPage;
import seleniumTests.pages.TestBase;
import seleniumTests.pages.loginPages.FacebookAuthenticationPage;
import seleniumTests.pages.loginPages.GoogleAuthenticationPage;
import seleniumTests.pages.loginPages.LoginPage;
import seleniumTests.pages.loginPages.TwitterAuthenticationPage;

/**
 * Created by MikhailN on 07.01.14.
 */
public class LoginPageTestBase extends TestBase {
    MainPage mainPage;
    LoginPage loginPage;
    GoogleAuthenticationPage googleAuthenticationPage;
    TwitterAuthenticationPage twitterAuthenticationPage;
    FacebookAuthenticationPage facebookAuthenticationPage;
    protected final CharSequence wrongRegExpNickNameShort = "ab";
    protected final CharSequence wrongRegExpNickNameSymbol = "\\asdf";
    protected final CharSequence password = "test";
    protected final CharSequence unusedNickName = "unusedNickName";
    protected final CharSequence wrongPasswordAgain = "t";
    protected final CharSequence captcha = "FG4h";

    protected void switchToNewOpenedWindow() {
        for(String winHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(winHandle);
        }
    }

    protected void clickLogin() {
        mainPage.getLoginButton().click();
        Assert.assertTrue(mainPage.getLoginForm().isDisplayed());
    }

    protected void enterPasswordAgain(CharSequence password) {
        loginPage.getPasswordAgain().click();
        loginPage.getPasswordAgain().sendKeys(password);
    }

    protected void enterPassword(CharSequence password) {
        loginPage.getPassword().click();
        loginPage.getPassword().sendKeys(password);
        //check that againPassword field has correct color
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
    }

    protected void enterNickName(CharSequence nickName) {
        loginPage.getNickName().click();
        loginPage.getNickName().clear();
        loginPage.getNickName().sendKeys(nickName);
    }

    protected void enterCorrectUnusedNickName(CharSequence nickName) {
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

    protected void enterWrongRegExpNickName(CharSequence wrongNickName) {
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

    protected void enterCorrectAgainPassword(CharSequence password) {
        //sent password to againPassword field
        enterPasswordAgain(password);

        //check that againPassword field has correct color
        Assert.assertEquals("Wrong passwordAgain field color", "rgba(255, 255, 255, 1)", loginPage.getPasswordAgain().getCssValue("background-color"));
    }

    protected void enterWrongPasswordAgain(CharSequence wrongPasswordAgain) {
        enterPasswordAgain(wrongPasswordAgain);

        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
    }

    protected void clearLoginPage() {
        loginPage.getNickName().clear();
        loginPage.getPassword().clear();
        loginPage.getPasswordAgain().clear();
        //loginPage.getLoginCaptcha().clear();
    }

    protected void enterCaptcha(CharSequence captcha) {
        loginPage.getCaptchaInputField().sendKeys(captcha);
    }

    protected void switchToULoginIFrame() {
        webDriver.switchTo().frame(loginPage.getULoginIFrame());
    }

}
