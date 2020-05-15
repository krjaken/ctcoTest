package helpers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import webdriver.WebDriverBuilder;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Slf4j
public class WaitHelper {
    private WebDriver driver;

    public WaitHelper() {
        this.driver = WebDriverBuilder.getInstance().getDriver();
    }

    public void waitForElementClickable(WebElement element) {
        getWaiter().until(elementToBeClickable(element));
    }

    public void waitForElementVisible(WebElement element, String errorMessage) {
        getWaiter(errorMessage).until(visibilityOf(element));
    }

    private FluentWait<WebDriver> getWaiter(String errorMessage) {
        return getWaiter()
                .withMessage(errorMessage + " Wait timeout: " + 4 + " seconds");
    }

    private FluentWait<WebDriver> getWaiter() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(4))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(InvalidElementStateException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }
}
