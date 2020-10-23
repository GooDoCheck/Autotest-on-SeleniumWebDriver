package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestWebsiteResultPage1 {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public TestWebsiteResultPage1(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }
    /**
     * определение локатора вывода значения "Расстояние"
     */
    @FindBy(xpath = "//span[@id ='totalDistance']")
    private WebElement totalDistance;
    /**
     * определение локатора вывода значения "Стоимость топлива"
     */
    @FindBy(xpath = "//form/p")
    private WebElement totalCoastFuel;
    /**
     * определение локатора кнопки "Настроить маршрут"
     */
    @FindBy(xpath = "//span[@class ='anchor']")
    private WebElement configRoute;
    /**
     * определение локатора поля ввода "Через города"
     */
    @FindBy(xpath = "//input[@name='v']")
    private WebElement crossTownField;
    /**
     * определение локатора кнопки "Рассчитать"
     */
    @FindBy(xpath = "//input[@type='submit' and @value='Рассчитать']")
    private WebElement calcBtn;

        /**
         * метод для ввода города в поле "Через города"
         */
        public void inputCrossTown(String text) {
            crossTownField.sendKeys(text); }
        /**
         * метод для нажатия кнопки кнопки "Настроить маршрут"
         */
        public void clickConfigRoute() {
            configRoute.click(); }
        /**
         * метод для нажатия кнопки кнопки "Рассчитать"
         */
        public void clickCalcBtn() {
            calcBtn.click(); }
        /**
         * метод для получения рассчитанного растояния
         */
        public String getTotalDistance() {
            //int distance = Integer.parseInt(totalDistance.getText(), 10);
            String distance = totalDistance.getText();
            return distance; }
        /**
         * метод для получения данных из блока рассчета стоимости топлива
         */
        public String getCoast() {
            String coast = totalCoastFuel.getText();
            return coast; }
        /**
         * метод прокручивания страницы до видимости элемента - configRoute
         */
        public void moveToConfigBtn(){
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
                    ,configRoute); }
        /**
         * метод прокручивания страницы до видимости элемента - calcBtn
         */
        public void moveToCalcBtn(){
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
                    ,calcBtn); }
}
