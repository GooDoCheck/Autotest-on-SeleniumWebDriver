package org.example;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexStartPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public YandexStartPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }
    /**
     * определение локатора поля ввода поискового запроса
     */
    @FindBy (xpath = "//*[@id=\"text\"]")
    private WebElement TextField;
    /**
     * определение локатора кнопки начала поиска
     */
    @FindBy(xpath="//button[@type='submit']")
    private WebElement SearchBtn;
    /**
     * метод для ввода поискового запроса в поле
     */
    public void inputSearchQuery(String text) {
        TextField.sendKeys(text); }
    /**
     * метод для осуществления нажатия кнопки поиска
     */
    public void clickSearchBtn() {
        SearchBtn.click();
        //findTextField.sendKeys(Keys.RETURN);
    }

}
