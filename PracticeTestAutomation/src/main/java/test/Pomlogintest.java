package test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Pomlogintest 
{
    WebDriver driver;
    Pomloginpage lp;

    @BeforeClass
    void setup()
    {
        driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

         lp = new Pomloginpage(driver);
    }

    @Test
    void testLogin()
    {
        lp.enterUsername("student");
        lp.enterPassword("Password123");
        lp.clickSubmit();

        String currentUrl = lp.getCurrentUrl();

        if(currentUrl.contains("logged-in-successfully"))
        {
            System.out.println("URL verification passed");

            if(lp.getPageSource().contains("Congratulations"))
                System.out.println("Congratulations verified");
            else
                System.out.println("Congratulations not verified");

            if(lp.getPageSource().contains("Log out"))
                System.out.println("Logout button verified");
            else
                System.out.println("Logout not verified");
        }
        else
        {
            if(lp.isErrorDisplayed())
            {
                System.out.println("Error message is displayed");

                String text = lp.getErrorText();

                if(text.equals("Your username is invalid!"))
                    System.out.println("Error text is Your username is invalid");
                else
                    System.out.println("Error text is Your password is invalid");
            }
            else
            {
                System.out.println("Error message is not displayed");
            }
        }
    }

    @AfterClass
    void tearDown()
    {
        driver.quit();
    }
}



