package seleniumTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by MikhailN on 06.01.14.
 */
public class MainPage extends Page {
    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.CSS, using = ".x-panel-body .x-grid-with-row-lines .x-grid-view .x-grid-table .x-grid-row")
    private List<WebElement> gridRowList;


    @FindBy(how = How.ID, using = "loginbutton")
    private WebElement loginButton;

    public WebElement getLoginButton(){
        return loginButton;
    }


    @FindBy(how = How.ID, using = "loginForm")
    private WebElement loginForm;

    public WebElement getLoginForm(){
        return loginForm;
    }


    @FindBy(how = How.CSS, using = "#userpanel")
    private WebElement userPanel;

    public WebElement getUserPanel() { return userPanel; }


    @FindBy(how = How.CSS, using = "#userpanel a")
    private WebElement profileLink;

    public WebElement getProfileLink() {
        return profileLink;
    }


    @FindBy(how = How.CSS, using = "#userpanel .rating")
    private WebElement rating;

    public WebElement getRating() {
        return rating;
    }


    @FindBy(how = How.CSS, using = "#userpanel .exit")
    private WebElement exit;

    public WebElement getExit() {
        return exit;
    }


}
