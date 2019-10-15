package com.pk.ATAChallenge2019;



import java.util.List;

/*2) Using Junit and WebDriver script, open https://www.cii.in/OnlineRegistration.aspx in Firefox and
do the below
1. Select “Number of Attendees” as 3
2. Assert the row count is 3
3. Select 1st-row title as ‘Admiral’(Please note use different method for every selection)
4. Select 2nd-row title as ‘CA’ (Please note use different method for every selection)
5. Select 3rd-row title as ‘CS’(Please note use different method for every selection)
6. Print all the options that are available in the title
*/
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JUnitTest2 {
	private static WebDriver driver;
	static App a = new App();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Launch Url: https://www.cii.in/OnlineRegistration.aspx
		driver = a.launchBrowser("FIREFOX","https://www.cii.in/OnlineRegistration.aspx");
	}
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		//close Browser
		driver.quit();
	}

	@Test
	public void ciiOnlineRegistartionTest() {
		WebDriverWait wait=new WebDriverWait(driver, 50);
		//Select Attendees
		WebElement drpAttendee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("drpAttendee")));
		Select dropdown = new Select(drpAttendee);
		dropdown.selectByValue("3");  
		//Assert RowCount = 3
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='Gridview1']//tr[contains(@style,'color:#333333;')]")));
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id='Gridview1']//tr[contains(@style,'color:#333333;')]"));
		Assert.assertEquals(3, rows.size());
		
		//Select 1st-row title as ‘Admiral’
		WebElement firstrow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='Gridview1_ctl02_drpTitle']")));
		Select dropdown1 = new Select(firstrow);
		dropdown1.selectByIndex(1);  
	
		//Select 2nd-row title as ‘CA’
		WebElement secondrow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='Gridview1_ctl03_drpTitle']")));
		Select dropdown2 = new Select(secondrow);
		dropdown2.selectByValue("CA"); 
		
		//Select 3rd-row title as ‘CS’
		WebElement thirdrow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='Gridview1_ctl04_drpTitle']")));
		Select dropdown3 = new Select(thirdrow);
		dropdown3.selectByVisibleText("CS");  
		
		//Printing All Options of Title Dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='Gridview1_ctl02_drpTitle']/option")));
		List<WebElement> options = driver.findElements(By.xpath("//*[@id='Gridview1_ctl02_drpTitle']/option"));
		System.out.println("Printing All Options of Title Dropdown");
		for(int i=1; i<options.size(); i++) {
		    System.out.println(options.get(i).getText());
		}
    }

}
