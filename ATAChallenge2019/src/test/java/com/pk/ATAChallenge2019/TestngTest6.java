package com.pk.ATAChallenge2019;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
 * 6) Open in chrome browser https://www.woodlandworldwide.com/, 
 * now search for the items related to the below-given product names.
	1. Test1: Use the product name “Bags”
	2. Test2: Use product name “Shoes” and
	3. Test3: Use the product name “Tshirts”.
	4. These product names are to be saved in an Excel file, read the product names from this file
	and pass to the search box.
	5. Select the filter ‘High to Low’ and test whether the first 8 products are in descending order
	of the price.
	6. Write a script for Google chrome in TestNG using WebDriver.
*/
public class TestngTest6 {
	private WebDriver driver;
	App a = new App();
	
	@Test
	void woodlandworldwideTest() throws IOException {
		
		WebDriverWait wait=new WebDriverWait(driver, 50);
		// Read Data from Excel using for loop for all 3 products Bags,Shoes,Tshirts
		for(int ii=1;ii < 4;ii++) {
			String productName=a.readExcel("Test"+ii);
			System.out.println("Test Started for '"+productName+"'"); 
			//Click on Search Icon
			WebElement searchIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='searchForm']/div")));
			searchIcon.click();
			//Type PRoduct Name
			WebElement searchkey = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchkey")));
			searchkey.sendKeys(productName);
			//Click on Go
			WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='searchBtn' and @value='GO']")));
			searchBtn.click();
			//Click on Filter High to Low
			WebElement prcHTL_sort = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='prcHTL_sort']/following-sibling::label")));
			prcHTL_sort.click();
			//Search and Fetch Products Price.
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id='categoryProductList']/section//span[@class='mrp']")));
			List<WebElement> ProductListPrices = driver.findElements(By.xpath("//*[@id='categoryProductList']/section//span[@class='mrp']"));
			ArrayList<Integer> prices = new ArrayList<Integer>();
			for(int i=0; i < 8; i++) {
				String price = ProductListPrices.get(i).getText();
				prices.add(Integer.parseInt(price.replace("R ", "").trim()));
			}
			ArrayList<Integer> prices1=prices;
			/*
			 * System.out.println("Prices Before Decending Order"); for(Integer a: prices1)
			 * { System.out.println(a); }
			 */
			Collections.sort(prices,Collections.reverseOrder());
			/*
			 * System.out.println("Prices After Decending Order"); for(Integer a: prices) {
			 * System.out.println(a); }
			 */
			Assert.assertTrue(prices.equals(prices1), "Prices Not appeared in decending Order on Website for "+productName);
		}
		
	}
	
	
	@BeforeClass
	public void beforeClass() {
		//Launch Url: https://www.woodlandworldwide.com/
		driver = a.launchBrowser("CHROME","https://www.woodlandworldwide.com/");
	}	
	@AfterClass
	public void afterClass() {
		//Close Browser
		driver.quit();
	}
}
