package seleniumTests.pages;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Abstract class representation of a Page in the UI. Page object pattern
 * 
 *
 */
public abstract class Page {

    private final WebDriverWait wait;
    protected WebDriver webDriver;

	/*
	 * Constructor injecting the WebDriver interface
	 * 
	 * @param webDriver
	 */
	public Page(WebDriver webDriver) {
		this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public String getTitle() {
		return webDriver.getTitle();
	}

    public void waitVisibility(final WebElement element) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return element.isDisplayed();
            }
        });
    }
    public void waitNotMoving(final WebElement element) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                Point location = element.getLocation();
                Point newLocation = element.getLocation();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return location.equals(newLocation);
            }
        });
    }
    public void waitInvisibility(final WebElement element) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return !element.isDisplayed();
            }
        });
    }

    public void waitClass(final WebElement element, final String className) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return element.getAttribute("class").contains(className);
            }
        });
    }

}
