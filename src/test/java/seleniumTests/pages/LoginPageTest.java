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
    private final CharSequence wrongRegExpNickName = "ab";
    private final CharSequence password = "test";
    private final CharSequence unusedNickName = "unusedNickName";
    private final CharSequence wrongAginPassword = "t";

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
        enterNickName(wrongRegExpNickName);

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
        RefreshPage();
    }

    @Test
    public void RegistrationWithWrongPasswordAgain(){
        clickLogin();
        enterNickName(unusedNickName);
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginInfo()));
        Assert.assertEquals("Wrong LoginInfoText","Приятно видеть новых людей!\n" +
                "Этот никнейм еще не занят.",loginPage.getLoginInfo().getText());
        enterPassword(password);
        enterPasswordAgain(wrongAginPassword);
        Assert.assertEquals("Wrong password again color", "rgba(255, 28, 8, 0.317647)", loginPage.getPasswordAgain().getCssValue("background-color"));
        Assert.assertFalse("Registration button should be disabled",loginPage.getSubmitButton().isEnabled());
        RefreshPage();


    }

    private void clickLogin() {
        mainPage.getLoginButton().click();
        Assert.assertTrue(mainPage.getLoginForm().isDisplayed());
    }

    private void enterPasswordAgain(CharSequence password) {
        loginPage.getPasswordAgain().click();
        loginPage.getPasswordAgain().sendKeys(password);
    }

    private void enterPassword(CharSequence password) {
        loginPage.getPassword().click();
        loginPage.getPassword().sendKeys(password);
    }

    private void enterNickName(CharSequence wrongRegExpNickName) {
        loginPage.getNickName().click();
        loginPage.getNickName().sendKeys(wrongRegExpNickName);
    }


}
