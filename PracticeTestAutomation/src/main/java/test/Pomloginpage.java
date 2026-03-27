package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Pomloginpage 
{
    WebDriver driver;

    // Constructor
    public Pomloginpage(WebDriver driver)
    {
        this.driver = driver;
    }

    // Locators
    By username = By.xpath("//input[@id='username']");
    By password = By.xpath("//input[@id='password']");
    By submitBtn = By.xpath("//button[@id='submit']");
    By errorMsg = By.xpath("//div[@id='error']");

    // Actions
    public void enterUsername(String user)
    {
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass)
    {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickSubmit()
    {
        driver.findElement(submitBtn).click();
    }

    // Validations
    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    public String getPageSource()
    {
        return driver.getPageSource();
    }

    public boolean isErrorDisplayed()
    {
        return driver.findElement(errorMsg).isDisplayed();
    }

    public String getErrorText()
    {
        return driver.findElement(errorMsg).getText();
    }
}