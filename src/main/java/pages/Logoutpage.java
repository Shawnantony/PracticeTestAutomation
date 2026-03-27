package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Logoutpage {
	  WebDriver driver;

	    // Constructor
	    public Logoutpage(WebDriver driver)
	    {
	        this.driver = driver;
	    }
	    public void page()
	    {
	    	 if(driver.getPageSource().contains("Log out"))
	             driver.findElement(By.xpath("//a[normalize-space()='Log out']")).click();
	   	    else
	   	    	System.out.println("Unable to find logout button");
	    }
	    

			}

