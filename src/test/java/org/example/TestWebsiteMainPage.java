package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestWebsiteMainPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public TestWebsiteMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }
    /**
     * определение локатора поля ввода "откуда"
     */
    @FindBy(xpath = "//input[@name='from']")
    private WebElement fromField;
        /**
     * определение локатора поля ввода "куда"
     */
    @FindBy(xpath = "//input[@name='to']") //and @class='ui-autocomplete-input'
    private WebElement toField;
     /**
     * определение локатора поля ввода "Расход топлива"
     */
    @FindBy(xpath = "//input[@name = 'fc']") //@type='number' and
    private WebElement fuelConsumptionField;
    /**
     * определение локатора поля ввода "Цена топлива"
     */
    @FindBy(xpath = "//input[@name = 'fp']") //@type='number' and
    private WebElement fuelPriceField;
    /**
     * определение локатора кнопки "Рассчитать"
     */
    @FindBy(xpath = "//input[@type='submit' and @value='Рассчитать']")
    private WebElement calcBtn;

        /**
         * метод для ввода города в поле "откуда"
         */
        public void inputFrom(String text) {
            fromField.sendKeys(text); }
        /**
         * метод для ввода города в поле "куда"
         */
        public void inputTo(String text) {
            toField.sendKeys(text); }
        /**
         * метод для ввода числа в поле "Расход топлива" с предварительной очисткой поля от значения по умолчанию
         */
        public void inputCosumption(String text) {
            fuelConsumptionField.clear();
            fuelConsumptionField.sendKeys(text); }
        /**
         * метод для ввода числа в поле "Цена топлива" с предварительной очисткой поля от значения по умолчанию
         */
        public void inputPrice(String text) {
            fuelPriceField.clear();
            fuelPriceField.sendKeys(text); }
        /**
         * метод для нажатия кнопки кнопки "Рассчитать"
         */
        public void clickCalcBtn() {
            calcBtn.click(); }
        /**
         * метод для возврата url текущей странницы
         */
        public String url(){
            return driver.getCurrentUrl();}
        /**
         * метод прокручивания страницы до видимости элемента - calcBtn
         */
        public void scrollToClcBtn(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
            ,calcBtn);
        }

}
