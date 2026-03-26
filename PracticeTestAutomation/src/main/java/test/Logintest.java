package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Logintest {

    public static void main(String[] args) 
    {
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Enter credentials
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        String currentUrl = driver.getCurrentUrl();

        if(currentUrl.contains("logged-in-successfully")) 
        {
            System.out.println("URL verification passed");

           
            String successText = driver.findElement( By.xpath("//strong[contains(text(),'Congratulations')]")) .getText();

            if(successText.contains("Congratulations"))
            {
                System.out.println("Congratulations verified");
            }
            else
            {
                System.out.println("Congratulations not verified");
            }

            boolean logoutDisplayed = driver.findElement(By.xpath("//a[normalize-space()='Log out']")) .isDisplayed();

            if(logoutDisplayed)
            {
                System.out.println("Logout button verified");
            }
            else
            {
                System.out.println("Logout not verified");
            }
        } 
        else
        {
            boolean errormsg = driver.findElement(By.id("error")).isDisplayed();

            if(errormsg)
            {
                System.out.println("Error message is displayed");

                String text = driver.findElement(By.id("error")).getText();

                if(text.equals("Your username is invalid!"))
                {
                    System.out.println("Error text is Your username is invalid");
                }
                else
                {
                    System.out.println("Error text is Your password is invalid");
                }
            }
            else
            {
                System.out.println("Error message is not displayed");
            }
        }

        driver.quit();
    }
}