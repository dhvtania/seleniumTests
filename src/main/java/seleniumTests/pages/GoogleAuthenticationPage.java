package seleniumTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by MikhailN on 1/14/14.
 */
public class GoogleAuthenticationPage extends Page {

    public GoogleAuthenticationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.ID, using = "Email")
    private WebElement emailField;

    public WebElement getEmailField() {
        return emailField;
    }


    @FindBy(how = How.ID, using = "Passwd")
    private WebElement passwordField;

    public WebElement getPasswordField() {
        return passwordField;
    }


    @FindBy(how = How.ID, using = "signIn")
    private WebElement signInButton;

    public WebElement getSignInButton() {
        return signInButton;
    }



}
