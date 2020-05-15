package selenium;

import helpers.ActionHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.MainPage;
import pages.VacanciesPage;
import pages.VacancyPage;
import webdriver.WebDriverBuilder;

import java.util.Optional;

@Slf4j
public class CtcoSiteTest {

    private WebDriver driver;
    private ActionHelper actionHelper;

    @BeforeTest
    public void setUp() {
        driver = WebDriverBuilder.getInstance().getDriver();
        actionHelper = new ActionHelper();
    }

    @AfterTest
    public void down() {
        driver.quit();
    }

    @Test(testName = "Verify paragraph under Professional skills and qualification: contains exactly 5 skills")
    public void logInCtcoSite() {
        driver.navigate().to("https://ctco.lv/en");
        MainPage mainPage = new MainPage(driver);
        actionHelper.moveOnElement(mainPage.careersMenuPointElement);
        CareersPage careersPage = new CareersPage(driver);
        actionHelper.clickElement(careersPage.vacanciesElement);
        VacanciesPage vacanciesPage = new VacanciesPage(driver);
        Optional<WebElement> vacancyElementOptional = vacanciesPage.findVacancyElement(By.xpath("//li[contains(@class, 'menu-item')]/*[text() = 'Test Automation Engineer']"));
        Assert.assertTrue(vacancyElementOptional.isPresent(), "vacancy 'Test Automation Engineer' is not a present");
        actionHelper.clickElement(vacancyElementOptional.get());
        VacancyPage vacancyPage = new VacancyPage(driver);
        Assert.assertEquals(vacancyPage.getVacancyHeader(), "Test Automation Engineer");

        Optional<WebElement> skillHeaderElementOptional = actionHelper.findElement(By.xpath("//div[./h1[text()='Test Automation Engineer']]/div[@class='wysiwyg wysiwyg-2']"));
        Assert.assertTrue(skillHeaderElementOptional.isPresent());

        Optional<WebElement> element = actionHelper.findElement(skillHeaderElementOptional.get(),
                By.xpath("./p[.='Professional skills and qualification:']/following-sibling::p[1]"));
        Assert.assertTrue(element.isPresent(), "Professional skills and qualification not exist");

        String outerHTML = element.get().getAttribute("outerHTML");
        System.out.println(outerHTML);
        Assert.assertEquals(outerHTML.split("<br>").length, 5);

    }
}
