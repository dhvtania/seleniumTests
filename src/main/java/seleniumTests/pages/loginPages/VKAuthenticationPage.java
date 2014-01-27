package seleniumTests.pages.loginPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import seleniumTests.pages.Page;

/**
 * Created by MikhailN on 27.01.14.
 */
public class VKAuthenticationPage extends Page {

    public VKAuthenticationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.CSS, using = "input[name='email']")
    private WebElement emailField;

    public WebElement getEmailField() {
        return emailField;
    }


    @FindBy(how = How.CSS, using = "input[name='pass']")
    private WebElement passwordField;

    public WebElement getPasswordField() {
        return passwordField;
    }


    @FindBy(how = How.ID, using = "install_allow")
    private WebElement signInButton;

    public WebElement getSignInButton() {
        return signInButton;
    }
}
