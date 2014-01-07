package seleniumTests.pages;

import junit.framework.Assert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    private final CharSequence wrongAgainPassword = "t";


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

        //sent wrongRegExpNickName captcha is dysplayed
        enterNickName(wrongRegExpNickNameShort);

        //check that loginInfo is dysplayed
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginInfo()));
        Assert.assertEquals("Wrong LoginInfoText","Никнейм должен удовлетворять регэкспу: \\w{3,15}",loginPage.getLoginInfo().getText());

        //check that captcha is dysplayed
        Assert.assertTrue(loginPage.getLoginCaptcha().isDisplayed());

        //check that password and againPassword fields have correct color
        Assert.assertEquals("Wrong password field color","rgba(255, 255, 255, 1)", loginPage.getPassword().getCssValue("background-color"));
        Assert.assertEquals("Wrong passwordAgain field color","rgba(255, 255, 255, 1)",loginPage.getPasswordAgain().getCssValue("background-color"));

        //sent password to password field
        enterPassword(password);

        //check that againPassword field has correct color
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));

        //sent password to againPassword field
        enterPasswordAgain(password);

        //check that againPassword field has correct color
        Assert.assertEquals("Wrong passwordAgain field color", "rgba(255, 255, 255, 1)", loginPage.getPasswordAgain().getCssValue("background-color"));

        //check that registration button is disaibled
        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        refreshPage();
    }

    @Test
    public void registrationWithWrongPasswordAgain(){
        clickLogin();

        enterNickName(unusedNickName);
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginInfo()));
        Assert.assertEquals("Wrong LoginInfoText","Приятно видеть новых людей!\n" +
                "Этот никнейм еще не занят.",loginPage.getLoginInfo().getText());
        enterPassword(password);
        enterPasswordAgain(wrongAgainPassword);
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        refreshPage();
    }

    @Test
    public void registrationWithWrongNickNameAndPassword(){
        clickLogin();

        enterNickName(wrongRegExpNickNameSymbol);
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginInfo()));
        Assert.assertEquals("Wrong LoginInfoText","Никнейм должен удовлетворять регэкспу: \\w{3,15}",loginPage.getLoginInfo().getText());
        enterPassword(password);
        enterPasswordAgain(wrongAgainPassword);
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        refreshPage();
    }

    @Test
    public void registrationWithoutClosingRegistrationForm() throws InterruptedException {
        clickLogin();
        //sent wrongRegExpNickName captcha is dysplayed
        enterNickName(wrongRegExpNickNameShort);

        //check that the loginInfo is dysplayed
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginInfo()));
        Assert.assertEquals("Wrong LoginInfoText","Никнейм должен удовлетворять регэкспу: \\w{3,15}",loginPage.getLoginInfo().getText());

        //check that the captcha is dysplayed
        Assert.assertTrue(loginPage.getLoginCaptcha().isDisplayed());

        //check that password and againPassword fields have correct color
        Assert.assertEquals("Wrong password field color","rgba(255, 255, 255, 1)", loginPage.getPassword().getCssValue("background-color"));
        Assert.assertEquals("Wrong passwordAgain field color","rgba(255, 255, 255, 1)",loginPage.getPasswordAgain().getCssValue("background-color"));

        //sent password to the password field
        enterPassword(password);

        //check that the againPassword field has correct color
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));

        //sent password to the againPassword field
        enterPasswordAgain(password);

        //check that the againPassword field has correct color
        Assert.assertEquals("Wrong passwordAgain field color", "rgba(255, 255, 255, 1)", loginPage.getPasswordAgain().getCssValue("background-color"));

        //check that registration button is disaibled
        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        enterNickName(unusedNickName);
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(),"Приятно видеть новых "));
        enterPassword(password);
        enterPasswordAgain(wrongAgainPassword);
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        enterNickName(wrongRegExpNickNameSymbol);
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.getLoginInfo(), "Никнейм должен удовлетворять регэкспу: \\w{3,15}"));
        enterPassword(password);
        enterPasswordAgain(wrongAgainPassword);
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());

        refreshPage();
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
    }

    private void enterNickName(CharSequence nickName) {
        loginPage.getNickName().click();
        loginPage.getNickName().clear();
        loginPage.getNickName().sendKeys(nickName);
    }


}
