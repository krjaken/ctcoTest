package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersPage extends BasePage {

    @FindBy(xpath = "//li[contains(@class, 'menu-item')]/*[text() = 'Vacancies']")
    public WebElement vacanciesElement;

    public CareersPage(WebDriver driver) {
        super(driver);
        waitForPageVisible(vacanciesElement);
    }
}
