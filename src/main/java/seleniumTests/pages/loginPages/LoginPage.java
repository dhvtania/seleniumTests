package seleniumTests.pages.loginPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import seleniumTests.pages.Page;

/**
 * Created by MikhailN on 06.01.14.
 */
public class LoginPage extends Page {


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.CSS, using = ".simplemodal-container .nickname")
    private WebElement nickName;

    public WebElement getNickName() {
        return nickName;
    }


    @FindBy(how = How.CSS, using = ".simplemodal-container .logininfo")
    private WebElement loginInfo;

    public WebElement getLoginInfo() {
        return loginInfo;
    }


    @FindBy(how = How.ID, using = "loginCaptcha")
    private WebElement loginCaptcha;

    public WebElement getLoginCaptcha() {
        return loginCaptcha;
    }


    @FindBy(how = How.CSS, using = ".simplemodal-container .password .loginFormInput")
    private WebElement password;

    public WebElement getPassword() {
        return password;
    }


    @FindBy(how = How.CSS, using = ".simplemodal-container .passwordagain .loginFormInput")
    private WebElement passwordAgain;

    public WebElement getPasswordAgain() {
        return passwordAgain;
    }


    @FindBy(how = How.CSS, using = ".simplemodal-container .submitButton")
    private WebElement submitButton;

    public WebElement getSubmitButton() { return submitButton; }


    @FindBy(how = How.CSS, using = ".loginCaptcha .captchaText")
    private WebElement captchaInputField;

    public WebElement getCaptchaInputField() { return submitButton; }


    @FindBy(how = How.CSS, using = "iframe[id*='easyXDM_default']")
    private WebElement uLoginIFrame;

    public WebElement getULoginIFrame() {
        return uLoginIFrame;
    }

    @FindBy(how = How.CSS, using = "#simplemodal-container #nicknameForm .modalCloseImg")
    private WebElement closeButtonSocialRegistration;

    public WebElement getCloseButtonSocialRegistration() {
        return closeButtonSocialRegistration;
    }

    @FindBy(how = How.CSS, using = "a.modalCloseImg")
    private WebElement closeButton;

    public WebElement getCloseButton() {
        return closeButton;
    }


    @FindBy(how = How.CSS, using = "#google")
    private WebElement googleButton;

    public WebElement getGoogleButton() {
        return googleButton;
    }


    @FindBy(how = How.CSS, using = "#vkontakte")
    private WebElement vKButton;

    public WebElement getVKButton() {
        return vKButton;
    }


    @FindBy(how = How.CSS, using = "#twitter")
    private WebElement twitterButton;

    public WebElement getTwitterButton() {
        return twitterButton;
    }


    @FindBy(how = How.CSS, using = "#facebook")
    private WebElement facebookButton;

    public WebElement getFacebookButton() {
        return facebookButton;
    }

}
