package com.pk.ATAChallenge2019;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/*4) Using Junit and WebDriver, open ‘https://www.hometown.in/’ in Firefox and do the below.
1. Select Electronics from ‘More’ menu
2. From Filter, section select the color as ‘Black’
3. When you go to the first product image, you will see Quick View option, click on that
4. Assert that product name contains Black in a name.
5. Close the Quick view window and verify if Black is also present in Applied filters 
*/

public class JUnitTest4 {

	private static WebDriver driver;
	static App a = new App();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Launch Url: https://www.hometown.in/
		driver = a.launchBrowser("FIREFOX","https://www.hometown.in/");
	}
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		//close Browser
		driver.quit();
	}

	@Test
	public void hometownTest() throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, 50);
		//Select Electronics from ‘More’ menu
		//By Pravin : Electronics Can't find in 'More' Menus so selecting from menu Bar
		try {
			WebElement nothanks = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='onesignal-popover-cancel-button']")));
			if(nothanks!=null) nothanks.click();
			Thread.sleep(2000);
			WebElement electronicsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Electronics')]")));
			electronicsMenu.click();
		}catch(ElementClickInterceptedException ee) {
			//close add
			WebElement nothanks = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='onesignal-popover-cancel-button']")));
			if(nothanks!=null) nothanks.click();
			WebElement closeQuickView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'styles_closeButton')]")));
			if(closeQuickView!=null) closeQuickView.click();
			WebElement electronicsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Electronics')]")));
			//electronicsMenu.click();
			Actions actions1 = new Actions(driver);
			actions1.moveToElement(electronicsMenu).click().build().perform();
		}
		
		WebElement SearchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search']")));
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(SearchBox).build().perform();
		//From Filter, section select the color as ‘Black’
		WebElement colorFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Filter By')]/following-sibling::*//button[contains(text(),'Color')]")));
		//Hover
		Actions actions2 = new Actions(driver);
		actions2.moveToElement(colorFilter).click().build().perform();
		Thread.sleep(2000);
		//select Black checkbox
		WebElement blackColor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='checkbox']/following-sibling::label[contains(text(),'Black')]")));
		blackColor.click();
		
		//When you go to the first product image, you will see Quick View option, click on that
		WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'ProductWrapper')][1]")));
		Actions actions3 = new Actions(driver);
		actions3.moveToElement(firstProduct).build().perform();
		Thread.sleep(2000);
		WebElement quickView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'ProductWrapper')][1]/button[contains(text(),'QUICK VIEW')][1]")));
		quickView.click();
		
		//Fetch Product Name from QuckView
		String productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1/a"))).getText();
		//Assert that product name contains Black in a name.
		Assert.assertEquals(true, productName.contains("Black"));
		
		//Close the Quick view window and verify if Black is also present in Applied filters
		WebElement closeQuickView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'styles_closeButton')]")));
		closeQuickView.click();
		WebElement BlackAppliedFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Applied Filters')]/following-sibling::*//li[contains(text(),'Black')]")));
		Assert.assertTrue("Black not present in Applied Filter", BlackAppliedFilter!=null);
		
		
	}

}
