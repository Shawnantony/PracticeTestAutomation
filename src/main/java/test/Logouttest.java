package test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.Logoutpage;
import pages.Pomloginpage;

public class Logouttest {
	 WebDriver driver;
	    

	    @BeforeClass
	    void setup() throws InterruptedException
	    {
	        driver = new ChromeDriver();
	        driver.get("https://practicetestautomation.com/practice-test-login/");
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Pomloginpage plp=new Pomloginpage(driver);
            plp.enterUsername("student");
            plp.enterPassword("Password123");
            plp.clickSubmit();
	        Logoutpage lp = new Logoutpage(driver);
	        lp.page();
	        
	    }
	    @Test
	    void testlogout()
	    {
	  
	    	if(driver.getCurrentUrl().equals("https://practicetestautomation.com/practice-test-login/"))
	    	{
	    		System.out.println("Logout Successful");
	    	}
	    	else
	    	{
	    		System.out.println("Logout unsuccessful");
	    	}
	    }

 @AfterClass
void tearDown()
{
    driver.quit();
}
 }
