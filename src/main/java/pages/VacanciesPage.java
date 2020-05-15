package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

public class VacanciesPage extends BasePage {
    @FindBy(xpath = "//div[./h1[text()='Vacancies']]")
    public WebElement vacanciesElement;

    public VacanciesPage(WebDriver driver) {
        super(driver);
        waitForPageVisible(vacanciesElement);
    }

    public Optional<WebElement> findVacancyElement(By by) {
        return actionHelper.findElement(by);
    }
}
