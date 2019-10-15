package com.pk.ATAChallenge2019;

import com.pk.ATAChallenge2019.App;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/*Test written using Test NG 
 * @author : Pravin Kumar
 * 1) Using TestNG and WebDriver script,
 * open https://www.meripustak.com/ 
 * in Google Chrome. do the below:
	1. Print the width and height of the logo
	2. Under Follow us section on the bottom, extract and print the href of ‘twitter’ social media
	icon (Right a script in such a way, if the position of ‘twitter’ icon is changed tomorrow in the
	social media icons, our script should work)
	3. Click on the shopping cart when an item in the cart is 0
	4. Assert the message in the shopping cart table “No Item is Added In Cart yet. Cart is
	Empty!!!”
	5. Add anyone java book in cart
	6. Verify if this message exists in the shopping cart table “No Item is Added In Cart yet. Cart is
	Empty!!!”
*/
public class TestngTest1 {
	private WebDriver driver;
	
	@Test
	void meripustakTest() {
		// Print MeriPustak Logo Height and Width
		WebDriverWait wait=new WebDriverWait(driver, 50);
		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='mpstkLogo']/img")));
		System.out.println("MeriPustak Logo Size: Width="+logo.getSize().getWidth()+", Height="+logo.getSize().getHeight());
		// Print Twiter social media href value
		WebElement twitter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@class='fa fa-twitter sky_blue']/parent::a")));
		System.out.println("Twiter social media href="+twitter.getAttribute("href"));
		
		//Clcik on Shopping Cart with 0 item
		driver.findElement(By.xpath("//a[text()='Shopping Cart']")).click();
		//Check Cart Details Message page appear
		WebElement cartTableMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[contains(text(),'No Item is Added In Cart yet')]")));
		Assert.assertEquals(cartTableMessage.getText(), "No Item is Added In Cart yet.Cart is Empty!!!");
		//Click on Continue Shopping
		driver.findElement(By.xpath("//a[text()='Continue Shopping']")).click();
		
		//Search JavaBook
		WebElement txtsearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtsearch")));
		txtsearch.sendKeys("Core Java");
		WebElement btnsearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnsearch")));
		btnsearch.click();
		WebElement javabook = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//a[contains(text(),'Core Java: An Integrated Approach')]")));
		javabook.click();
		WebElement addtocart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ContentPlaceHolder1_AddtoCart")));
		addtocart.click();
		//Verify message 'No Item is Added In Cart yet.Cart is Empty' not appered
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//a[text()='Continue Shopping']")));
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'No Item is Added In Cart yet')]"));
		Assert.assertTrue(list.size() == 0, "No Item is Added In Cart yet. Message found!" );
	}
	
	
	@BeforeClass
	public void beforeClass() {
		App a = new App();
		//Launch Url: https://www.meripustak.com/
		driver = a.launchBrowser("CHROME","https://www.meripustak.com/");
	}	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
