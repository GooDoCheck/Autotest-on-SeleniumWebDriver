package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestWebsiteResultPage2 {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public TestWebsiteResultPage2(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }
    /**
     * определение локатора поля вывода "Расстояние"
     */
    @FindBy(xpath = "//span[@id ='totalDistance']")
    private WebElement totalDistance;
    /**
     * определение локатора поля вывода "Стоимость топлива"
     */
    @FindBy(xpath = "//form/p")
    private WebElement totalCoastFuel;
    /**
     * метод для получения рассчитанного растояния
     */
    public String getTotalDistance() {
        String distance = totalDistance.getText();
        return distance; }
    /**
     * метод для получения данных из блока рассчета стоимости топлива
     */
    public String getCoast() {
        String coast = totalCoastFuel.getText();
        return coast; }
}
