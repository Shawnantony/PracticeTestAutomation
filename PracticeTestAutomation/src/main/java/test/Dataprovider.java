package test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Dataprovider
 {
	    WebDriver driver;

	    @BeforeClass
	    void setup()
	    {
	        driver = new ChromeDriver();
	        driver.get("https://practicetestautomation.com/practice-test-login/");
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    }
	    @DataProvider(name = "loginData")
	    Object[][] getData()
	    {
	        return new Object[][]
	        {
	            {"student", "Password123"},   // valid
	            {"wronguser", "Password123"}, // invalid username
	            {"student", "wrongpass"}      // invalid password
	        };
	    }

	    @Test(dataProvider = "loginData")
	    void testLogin(String username, String password)
	    {
	        driver.findElement(By.xpath("//input[@id='username']")).clear();
	        driver.findElement(By.xpath("//input[@id='password']")).clear();

	        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
	        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	        driver.findElement(By.xpath("//button[@id='submit']")).click();

	        String currentUrl = driver.getCurrentUrl();

	        if(currentUrl.contains("logged-in-successfully"))
	        {
	            System.out.println("Login successful for: " + username);

	            String successText = driver.findElement( By.xpath("//strong[contains(text(),'Congratulations')]")).getText();

	            if(successText.contains("Congratulations"))
	                System.out.println("Congratulations verified");

	            boolean logoutDisplayed = driver.findElement( By.xpath("//a[normalize-space()='Log out']")).isDisplayed();

	            if(logoutDisplayed)
	                System.out.println("Logout button verified");

	            // logout for next iteration
	            driver.findElement(By.xpath("//a[normalize-space()='Log out']")).click();
	        }
	        else
	        {
	            System.out.println("Login failed for: " + username);

	            boolean errormsg = driver.findElement( By.xpath("//div[@id='error']")).isDisplayed();

	            if(errormsg)
	            {
	                String text = driver.findElement( By.xpath("//div[@id='error']")).getText();

	                System.out.println("Error message: " + text);
	            }
	        }
	    }

	    @AfterClass
	    void tearDown()
	    {
	        driver.quit();
	    }
	}


