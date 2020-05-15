package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//li[contains(@class, 'menu-item')]/*[text() = 'Careers']")
    public WebElement careersMenuPointElement;

    public MainPage(WebDriver driver) {
        super(driver);
        waitForPageVisible(careersMenuPointElement);
    }

}
