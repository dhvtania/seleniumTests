package seleniumTests.pages.loginPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import seleniumTests.pages.Page;

/**
 * Created by MikhailN on 18.01.14.
 */
public class FacebookAuthenticationPage extends Page{

    public FacebookAuthenticationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.ID, using = "email")
    private WebElement emailField;

    public WebElement getEmailField() {
        return emailField;
    }


    @FindBy(how = How.ID, using = "pass")
    private WebElement passwordField;

    public WebElement getPasswordField() {
        return passwordField;
    }


    @FindBy(how = How.ID, using = "loginbutton")
    private WebElement signInButton;

    public WebElement getSignInButton() {
        return signInButton;
    }
}
