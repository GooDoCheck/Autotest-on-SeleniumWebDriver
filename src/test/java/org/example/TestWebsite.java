package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestWebsite {
    public static YandexStartPage yandexStartPage;
    public static YandexSearchPage yandexSearchPage;
    public static TestWebsiteMainPage testWebsiteMainPage;
    public static TestWebsiteResultPage1 testWebsiteResultPage1;
    public static TestWebsiteResultPage2 testWebsiteResultPage2;
    public static WebDriver driver;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver.exe");
        driver = new ChromeDriver();
        yandexStartPage = new YandexStartPage(driver);
        yandexSearchPage = new YandexSearchPage(driver);
        testWebsiteMainPage = new TestWebsiteMainPage(driver);
        testWebsiteResultPage1 = new TestWebsiteResultPage1(driver);
        testWebsiteResultPage2 = new TestWebsiteResultPage2(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("startPage"));}


    @Test
    public void autoTestWebsite () throws NullPointerException {
        yandexStartPage.inputSearchQuery(ConfProperties.getProperty("text"));
        yandexStartPage.clickSearchBtn();
        Set<String> oldWindowsSet = driver.getWindowHandles();
        yandexSearchPage.entryToWebsite();

        String newWindow = (new WebDriverWait(driver, 10))
            .until((ExpectedCondition<String>) driver -> {
                    assert driver != null;
                    Set<String> newWindowsSet = driver.getWindowHandles();
                    newWindowsSet.removeAll(oldWindowsSet);
                    return newWindowsSet.size() > 0 ?
                        newWindowsSet.iterator().next() : null;
                }
            );

        driver.switchTo().window(newWindow);

        Assert.assertTrue(testWebsiteMainPage.url().contains(ConfProperties.getProperty("searchPage")));

        testWebsiteMainPage.scrollToClcBtn();
        testWebsiteMainPage.inputFrom(ConfProperties.getProperty("fromTown"));
        testWebsiteMainPage.inputTo(ConfProperties.getProperty("toTown"));
        testWebsiteMainPage.inputCosumption(ConfProperties.getProperty("fuelConsumption"));
        testWebsiteMainPage.inputPrice(ConfProperties.getProperty("fuelPrice"));
        testWebsiteMainPage.clickCalcBtn();

        Assert.assertEquals(ConfProperties.getProperty("firstTotalDistance"), testWebsiteResultPage1.getTotalDistance());
        Assert.assertTrue(testWebsiteResultPage1.getCoast().contains(ConfProperties.getProperty("firstFuelCost")+" руб."));

        testWebsiteResultPage1.moveToConfigBtn();
        testWebsiteResultPage1.clickConfigRoute();
        testWebsiteResultPage1.inputCrossTown(ConfProperties.getProperty("crossTown"));
        testWebsiteResultPage1.moveToCalcBtn();
        testWebsiteResultPage1.clickCalcBtn();

        Assert.assertEquals(ConfProperties.getProperty("SecondTotalDistance"), testWebsiteResultPage2.getTotalDistance());
        Assert.assertTrue(testWebsiteResultPage2.getCoast().contains(ConfProperties.getProperty("SecondFuelCost")+" руб."));


    }

    @AfterClass
    public static void tearDown () {
        driver.quit();
    }
}