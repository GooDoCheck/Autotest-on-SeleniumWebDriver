package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexSearchPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public YandexSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }
    /**
     * определение локатора нужного поискового запроса
     */
     @FindBy(xpath="//b[contains(.,'avtodispetcher.ru')]")
     private WebElement linkToWebsite;
    /**
     * метод для нажатия кнопки меню пользователя
     */
    public void entryToWebsite() {
        linkToWebsite.click(); }

}
