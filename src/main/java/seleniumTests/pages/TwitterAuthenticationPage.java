package seleniumTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by MikhailN on 14.01.14.
 */
public class TwitterAuthenticationPage extends Page {

    public TwitterAuthenticationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.CSS, using = "label[for='username_or_email']")
    private WebElement emailField;

    public WebElement getInputEmailField() {
        return emailField;
    }


    @FindBy(how = How.ID, using = "password")
    private WebElement passwordField;

    public WebElement getPasswordField() {
        return passwordField;
    }


    @FindBy(how = How.ID, using = "allow")
    private WebElement signInButton;

    public WebElement getSignInButton() {
        return signInButton;
    }


    @FindBy(how = How.ID, using = "cancel")
    private WebElement chancelButton;


    public WebElement getChancelButton() {
        return chancelButton;
    }

    @FindBy(how = How.ID, using = "username_or_email")
    private WebElement emailField2;

    public WebElement getEmailField2() {
        return emailField2;
    }
}
