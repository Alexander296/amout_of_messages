package com.chuykov.web;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class FindMessagesTest {

    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static MailPage mailPage;
    public static WebDriver driver;
    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.opera.driver", ConfProperties.getProperty("operadriver"));
        //создание экземпляра драйвера
        driver = new OperaDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        mailPage = new MailPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void findMailTest() {
        //получение доступа к методам класса LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек
        //вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //вводим пароль
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();

        profilePage.clickEntryMenu();
        profilePage.entryMail();
        Assert.assertEquals(mailPage.findAmountOfMessages(), 3);

        mailPage.clickWriteMessage();
        mailPage.inputAddress("alexandr.chuykov@yandex.ru");
        mailPage.inputTheme("Simbirsoft Тестовое задание");
        mailPage.inputText(Integer.toString(mailPage.findAmountOfMessages()));
        mailPage.clickSendMessage();
        //mailPage.clickRefreshMessages();
        //Assert.assertEquals(mailPage.findAmountOfMessages(), 4);
    }



    /**
     * осуществление выхода из аккаунта с последующим закрытием окна браузера
     */
    @AfterClass
    public static void tearDown() {
        mailPage.clickEntryMenu();
        mailPage.userLogout();
        driver.quit();
    }
}
