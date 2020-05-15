package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.open;

public class CtcoSiteTest {

    @BeforeTest
    public void setUp() {
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browser = WebDriverRunner.CHROME;
    }

    @Test(testName = "Verify paragraph under Professional skills and qualification: contains exactly 5 skills")
    public void logInCtcoSite() {
        open("https://ctco.lv/en");
        WebElement careersMenuPointElement = $(By.xpath("//li[contains(@class, 'menu-item')]/*[text() = 'Careers']"));
        actions().moveToElement(careersMenuPointElement).moveToElement(careersMenuPointElement).build().perform();

        $(By.xpath("//li[contains(@class, 'menu-item')]/*[text() = 'Vacancies']")).click();

        $(By.xpath("//li[contains(@class, 'menu-item')]/*[text() = 'Test Automation Engineer']")).click();

        WebElement TestAutomationEngineerVacancyElement = $(By.xpath("//div[./h1[text()='Test Automation Engineer']]/div[@class='wysiwyg wysiwyg-2']"));

        WebElement skillRequirementElement = TestAutomationEngineerVacancyElement.findElement(By.xpath("./p[.='Professional skills and qualification:']/following-sibling::p[1]"));

        Assert.assertEquals(skillRequirementElement.getAttribute("outerHTML").split("<br>").length, 5);

    }
}
