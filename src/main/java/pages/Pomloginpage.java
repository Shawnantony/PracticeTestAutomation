package pages;

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
    By successMsg = By.xpath("//strong[contains(text(),'Congratulations student. You successfully logged i')]");  

    // Actions
    public void enterUsername(String user) throws InterruptedException
    {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        Thread.sleep(2000);
    }

    public void enterPassword(String pass) throws InterruptedException
    {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        Thread.sleep(2000);
    }

    public void clickSubmit() throws InterruptedException
    {
        driver.findElement(submitBtn).click();
        Thread.sleep(2000);   
    }

    // Validations
    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    public boolean isErrorDisplayed()
    {
        return driver.findElement(errorMsg).isDisplayed();
    }

    public String getErrorText()
    {
        return driver.findElement(errorMsg).getText();
    }

   
    public String getSuccessMessage()
    {
        return driver.findElement(successMsg).getText();
    }
}