package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

@Slf4j
public class VacancyPage extends BasePage {
    @FindBy(xpath = "//div[contains(@class,'animated fadeIn active')]")
    public WebElement mainElement;

    public VacancyPage(WebDriver driver) {
        super(driver);
        waitForPageVisible(mainElement);
    }

    public String getVacancyHeader() {
        Optional<WebElement> optionalWebElement = actionHelper.findElement(By.xpath("//div[contains(@class,'animated fadeIn active')]/div[@class='text-block']/h1"));

        log.info("optionalWebElement is: " + optionalWebElement.isPresent());

        return optionalWebElement.isPresent() ? optionalWebElement.get().getAttribute("innerHTML") : "";
    }
}
