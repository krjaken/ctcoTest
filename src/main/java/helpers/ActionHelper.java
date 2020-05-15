package helpers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import webdriver.WebDriverBuilder;

import java.util.Optional;

@Slf4j
public class ActionHelper {

    private final WebDriver driver;
    private final WaitHelper waitHelper;

    public ActionHelper() {
        this.driver = WebDriverBuilder.getInstance().getDriver();
        this.waitHelper = new WaitHelper();
    }

    public void clickElement(WebElement element) {
        waitHelper.waitForElementClickable(element);
        element.click();
    }

    public void moveOnElement(WebElement element) {
        waitHelper.waitForElementClickable(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.build().perform();
    }

    public Optional<WebElement> findElement(By by) {
        try {
            WebElement element = driver.findElement(by);
            return Optional.of(element);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<WebElement> findElement(WebElement findInElement, By by) {
        try {
            WebElement element = findInElement.findElement(by);
            return Optional.of(element);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }

        return Optional.empty();
    }
}
