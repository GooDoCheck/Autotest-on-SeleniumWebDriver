package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        // определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver.exe");
        // создание экземпляра драйвера для каждого класса
        driver = new ChromeDriver();
        yandexStartPage = new YandexStartPage(driver);
        yandexSearchPage = new YandexSearchPage(driver);
        testWebsiteMainPage = new TestWebsiteMainPage(driver);
        testWebsiteResultPage1 = new TestWebsiteResultPage1(driver);
        testWebsiteResultPage2 = new TestWebsiteResultPage2(driver);
        // окно разворачивается на полный экран
        driver.manage().window().maximize();
        // задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // получение ссылки на страницу
        driver.get(ConfProperties.getProperty("startPage"));}
        /**
         * тестовый метод для осуществления поиска страницы
         */
        @Test
        public void autoTestWebsite () throws NullPointerException {
            //Для получения доступа к элементам странницы, согласно шаблона проектирования Page Object, для каждой страницы
            //написан класс, в котором определяются локаторы необходимых элементов, и прописаны методы для взаимодействия с ними
            //Вводим поисковой запрос в поле ввода главной страницы Яндекса, при помощи метода inputSearchQuery
            //текст для запроса мы получаем из файла conf.properties
            yandexStartPage.inputSearchQuery(ConfProperties.getProperty("text"));
            //нажимаем на кнопку "Найти"
            yandexStartPage.clickSearchBtn();
            //Запоминаем дескриптор текущего окна в переменную oldWindowsSet
            Set<String> oldWindowsSet = driver.getWindowHandles();
            //Ищем необходимый поисковой запрос на первой страннице поиска, и кликаем по нему
            yandexSearchPage.entryToWebsite();
            //Записываем дескрипторы всех окон открытых в браузере после 10 секунд ожидания,
            //определяем какой из них дескриптор нового окна и записываем в переменную newWindow
            String newWindow = (new WebDriverWait(driver, 10))
                    .until((ExpectedCondition<String>) driver -> {
                                assert driver != null;
                                Set<String> newWindowsSet = driver.getWindowHandles();
                        newWindowsSet.removeAll(oldWindowsSet);
                        return newWindowsSet.size() > 0 ?
                                newWindowsSet.iterator().next() : null;
                    }
                    );
            //Переключаем фокус драйвера на дескриптор окна записанного в переменную newWindow
            driver.switchTo().window(newWindow);

            //Запускаем проверку что открылась нужная нам веб странница, сравнивая url текущего окна
            // с url записанного в переменную searchPage из conf.properties
            Assert.assertTrue(testWebsiteMainPage.url().contains(ConfProperties.getProperty("searchPage")));
            //Выполняем прокрутку страницы до кнопки "Рассчитать", что бы кнопка с выполнением расчета оказалась
            //на видном месте экрана, во избежание клика по всплывающим окнам рекламы
            testWebsiteMainPage.scrollToClcBtn();
            //Вводим тестовые данные в поля ввода тестируемого сайта:
            //В поле "Откуда" записывается значение из переменной city1 из conf.properties
            testWebsiteMainPage.inputFrom(ConfProperties.getProperty("fromTown"));
            //В поле "Куда" записывается значение из переменной city2 из conf.properties
            testWebsiteMainPage.inputTo(ConfProperties.getProperty("toTown"));
            //В поле "Расход топлива" записывается значение из переменной fuelConsumption
            testWebsiteMainPage.inputCosumption(ConfProperties.getProperty("fuelConsumption"));
            //В поле "Цена топлива" записывается значение из переменной fuelPrice
            testWebsiteMainPage.inputPrice(ConfProperties.getProperty("fuelPrice"));
            //нажимаем кнопку "Рассчитать"
            testWebsiteMainPage.clickCalcBtn();

            //Методом getTotalDistance() получаем рассчитанное значение расстояния
            // и сравниваем со значением в переменной totalDistance1
            Assert.assertEquals(ConfProperties.getProperty("firstTotalDistance"), testWebsiteResultPage1.getTotalDistance());
            //Методом getCoast() получаем текст из блока рассчета стоимости топлива
            // и в нем ищем значение записанное в переменной fuelCost1 из conf.properties
            Assert.assertTrue(testWebsiteResultPage1.getCoast().contains(ConfProperties.getProperty("firstFuelCost")+" руб."));
            //Прокручиваем страницу до кнопки "Настроить маршрут"
            testWebsiteResultPage1.moveToConfigBtn();
            //Нажимаем на кнопку
            testWebsiteResultPage1.clickConfigRoute();
            //Вводим город Великий Новгород в поле "Через города"
            testWebsiteResultPage1.inputCrossTown(ConfProperties.getProperty("crossTown"));
            //Прокручиваем страницу до кнопки "Рассчитать"
            testWebsiteResultPage1.moveToCalcBtn();


            //Нажимаем на кнопку "Рассчитать"
            testWebsiteResultPage1.clickCalcBtn();

            //Методом getTotalDistance() получаем рассчитанное значение расстояния
            // и сравниваем со значением в переменной totalDistance2
            Assert.assertEquals(ConfProperties.getProperty("SecondTotalDistance"), testWebsiteResultPage2.getTotalDistance());
            //Методом getCoast() получаем значения из текстового блока рассчета стоимости топлива
            // и ищем необходимое значение записанное в переменной fuelCost1
            Assert.assertTrue(testWebsiteResultPage2.getCoast().contains(ConfProperties.getProperty("SecondFuelCost")+" руб."));


        }
        /**
         * осуществление выхода из аккаунта с последующим закрытием окна браузера
         */
        @AfterClass
        public static void tearDown () {
            driver.quit();
        }
    }