package pages;

import helpers.ActionHelper;
import helpers.WaitHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public abstract class BasePage {

    private WebDriver driver;
    public ActionHelper actionHelper;
    public WaitHelper waitHelper;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.actionHelper = new ActionHelper();
        this.waitHelper = new WaitHelper();
    }

    protected void waitForPageVisible(WebElement element) {
        String pageName = getClass().getSimpleName();
        log.info("Wait page: {}", pageName);

        String errorMessage = "Page " + pageName + " was not loaded";
        waitHelper.waitForElementVisible(element, errorMessage);

        log.info("Page {} loaded", pageName);
    }
}
