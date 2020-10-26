# Autotest-on-SeleniumWebDriver
Домашнее задание на позицию QA Automation, с использованием Java, Maven, Selenium WebDriver, Junit
Homework for the QA Automation position, using Java, Maven, Selenium WebDriver, Junit.
This test worked on Windows 10 and chrome browser ver. 86.0.4240.111

## Test script

1.	The user visits the Yandex website: www.yandex.ru
2.	Enters the phrase «расчет расстояний между городами» into the search line and starts the search
3.	Among the search results, the user searches for a result from the site «avtodispetcher.ru»
4.	Having found the desired result from this site - the user clicks on this result and goes to the site www.avtodispetcher.ru/distance/ 
5.	After making sure that the correct link has opened, the user enters the following values into the fields:
- Field «Откуда» - «Тула»
- Field «Куда» - «Санкт-Петербург»
- Field «Расход топлива» - «9»
- Field «Цена топлива» - «46»
6.	User clicks a button «Рассчитать» 
7.	The user checks that the calculated distance = 897 км, and the cost of fuel = 3726 руб.
8.	User clicks on «Изменить маршрут»
9.	In the form that opens, in the field «Через города» user enters «Великий Новгород» and clicks "Calculate"
10.	The user checks that the distance is now = 966 км, and the cost of fuel = 4002 руб. dir /fonts

## Preparation for launch test

To run this autotest, you need an installed and configured path variable for maven 3.6.3 or higher and jdk 14.
Also you need installed chrome browser ver. 86.0.4240.111 or old version.

## How to launch test

* Download this code, put in random folder
* Open the console and go to the folder where the pom.xml file is located
* Type: mvn -Dtest=TestWebsite test , and press Enter


If there are any problems with the operation of this autotest, or other questions, please write to me ilyaodin16@gmail.com
