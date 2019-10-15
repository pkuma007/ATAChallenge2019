package com.pk.ATAChallenge2019;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*3) Using Junit and WebDriver script, open https://www.premierleague.com/ in Firefox and do the
below
1. Click on Tables from the header
2. From the tables, open ‘Arsenal’ club in a new window via context-click
3. Find official website URL on the page and print on the console from the newly opened
window
4. Print the page title of the newly opened window
5. Go back to the main window
6. Print the page title again
*/

public class JUnitTest3 {
	
	private static WebDriver driver;
	static App a = new App();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Launch Url: https://www.premierleague.com/
		driver = a.launchBrowser("FIREFOX","https://www.premierleague.com/");
	}
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		//close Browser
		driver.quit();
	}

	@Test
	public void premierLeagueTest() throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, 50);
		//Click on Tables from the header
		WebElement TablesMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@role='menubar']//a[text()='Tables']")));
		//TablesMenu.click();
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(TablesMenu).click().build().perform();
		//Fetch currentTab 
		String oldTab = driver.getWindowHandle();
		//From the tables, open ‘Arsenal’ club in a new window via context-click
		Actions actions2 = new Actions(driver);
		WebElement ArsenalName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-ui-tab='First Team']//span[@class='long' and text()='Arsenal']")));
		//actions2.contextClick(ArsenalName).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		actions2.keyDown(Keys.SHIFT).click(ArsenalName).keyUp(Keys.SHIFT).build().perform();
		Thread.sleep(10000);
		//Fetch new Tab window
		ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
		Tabs.remove(oldTab);
		String newTab = Tabs.get(0);
		
	    //change focus to new tab
	    driver.switchTo().window(newTab);
	    
		//Find official website URL on the page and print on the console from the newly opened window
	    WebElement officialWebsite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Official Website')]/following::a[contains(text(),'www.arsenal.com')]")));
	    System.out.println("Official Website URL from new Window :"+officialWebsite.getAttribute("href"));
		
		//Print the page title of the newly opened window
	    System.out.println("Page Title of newly opened window: "+driver.getTitle());
		//Go back to the main window. //change focus to old tab
	    driver.switchTo().window(oldTab);
		//Print the page title again
	    System.out.println("Page Title of old Window: "+driver.getTitle());
	}
}
