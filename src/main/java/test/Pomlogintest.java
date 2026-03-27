package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import pages.Pomloginpage;

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
    void testLogin() throws IOException, InterruptedException
    {
        FileInputStream file = new FileInputStream("E:\\validation.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int totRows = sheet.getLastRowNum();

        for(int r = 1; r <= totRows; r++)
        {
            String user = sheet.getRow(r).getCell(0).getStringCellValue();
            String pass = sheet.getRow(r).getCell(1).getStringCellValue();

            lp.enterUsername(user);
            lp.enterPassword(pass);
            lp.clickSubmit();

            String currentUrl = lp.getCurrentUrl();

            if(currentUrl.contains("logged-in-successfully"))
            {
                System.out.println("URL verification passed");

              
                String msg = lp.getSuccessMessage();
                System.out.println("Message: " + msg);

                if(msg.contains("Congratulations"))
                    System.out.println("Congratulations verified");
                else
                    System.out.println("Congratulations not verified");
            }
            else
            {
                if(lp.isErrorDisplayed())
                {
                    System.out.println("Error message is displayed");

                    String text = lp.getErrorText();

                    if(text.equals("Your username is invalid!"))
                        System.out.println("Error text is Your username is invalid");
                    else if(text.equals("Your password is invalid!"))
                        System.out.println("Error text is Your password is invalid");
                    else
                        System.out.println("Unknown error message");
                }
                else
                {
                    System.out.println("Error message is not displayed");
                }
            }

            
            driver.get("https://practicetestautomation.com/practice-test-login/");
        }

        workbook.close();
        file.close();
    }

    @AfterClass
    void tearDown()
    {
        driver.quit();
    }
}