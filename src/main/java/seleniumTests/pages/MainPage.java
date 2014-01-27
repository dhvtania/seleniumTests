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

    @FindBy(how = How.CSS, using = "ul li a[href='/latest/0']")
    private WebElement latest;

    public WebElement getLatest() {
        return latest;
    }

    @FindBy(how = How.CSS, using = "ul li a[href='/daily/0']")
    private WebElement top;

    public WebElement getTop() {
        return top;
    }

    @FindBy(how = How.CSS, using = "ul li a[href='/hot/0']")
    private WebElement hot;

    public WebElement getHot() {
        return hot;
    }

    @FindBy(how = How.CSS, using = "ul li a[href='/random']")
    private WebElement randomizer;

    public WebElement getRandomizer() {
        return randomizer;
    }

    @FindBy(how = How.CSS, using = "ul li a[href='/upload']")
    private WebElement upload;

    public WebElement getUpload() {
        return upload;
    }


    @FindBy(how = How.CSS, using = "h3.comment.pageTitle")
    private WebElement hThreeCommentPageTitle;

    public WebElement getHThreeCommentPageTitle() {
        return hThreeCommentPageTitle;
    }

    @FindBy(how = How.CSS, using = "h4.comment.pageTitle")
    private WebElement hFourCommentPageTitle;


    public WebElement getHFourCommentPageTitle() {
        return hFourCommentPageTitle;
    }

}
