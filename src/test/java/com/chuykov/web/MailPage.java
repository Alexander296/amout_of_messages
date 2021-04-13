package com.chuykov.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailPage {

    public WebDriver driver;

    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(text(), 'Simbirsoft Тестовое задание')]")
    private List<WebElement> titleListOfMessage;

    @FindBy(xpath = "//*[contains(text(), 'Написать')]/..")
    private WebElement writeMessageBtn;

    @FindBy(xpath = "//*[contains(@class, 'composeYabbles')]")
    private WebElement addressField;

    @FindBy(xpath = "//*[contains(@class, 'composeTextField ComposeSubject-TextField')]")
    private WebElement themeField;

    @FindBy(xpath = "/html/body/div[2]/div[10]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[3]/div[2]/div/div/div[1]/div/div/div/div")
    private WebElement textField;

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[10]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div/div[1]/div[1]/button")
    private WebElement sendBtn;

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[7]/div/div[3]/div[2]/div[2]/div/div/span")
    private WebElement refreshMessagesBtn;

    /**
     * определение локатора меню пользователя
     */
    @FindBy(xpath = "//*[@id=\"cke_97_0contents\"]/div/div")
    private WebElement userMenu;
    /**
     * определение локатора кнопки выхода из аккаунта
     */
    @FindBy(xpath = "//*[contains(text(), 'Выйти из аккаунта')]/..")
    private WebElement logoutBtn;

    public void clickRefreshMessages() {
        refreshMessagesBtn.click();
    }

    public void clickSendMessage() {
        sendBtn.click();
    }

    public void inputText(String theme) {
        textField.sendKeys(theme);
    }

    public void inputTheme(String theme) {
        themeField.sendKeys(theme);
    }

    public void inputAddress(String address) {
        addressField.sendKeys(address);
    }

    public int findAmountOfMessages() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Simbirsoft Тестовое задание')]")));
        return titleListOfMessage.size();
    }

    public void clickWriteMessage() {
        writeMessageBtn.click();
    }

    /**
     * метод для нажатия кнопки меню пользователя
     */
    public void clickEntryMenu() {
        userMenu.click();
    }

    /**
     * метод для нажатия кнопки выхода из аккаунта
     */
    public void userLogout() {
        logoutBtn.click();
    }
}
