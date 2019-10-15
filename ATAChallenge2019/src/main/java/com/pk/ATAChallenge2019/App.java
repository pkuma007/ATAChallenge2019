package com.pk.ATAChallenge2019;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.Proxy;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;



/**
 * Main Class for Tests
 *
 */
public class App 
{
	//Launching Browser based on conditions
	public  WebDriver launchBrowser(String browserType, String url) {
		WebDriver driver=null;
		if(browserType.equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserType.equals("FIREFOX"))  {
			System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
			Proxy proxy = new Proxy();
		    proxy.setProxyType(Proxy.ProxyType.AUTODETECT);
		    FirefoxOptions options = new FirefoxOptions();
		    options.setProxy(proxy);
			driver = new FirefoxDriver(options);
			
		}else {
			System.out.println("Please provide correct Driver browserType to launch");
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		return driver;
	}
    
	//Read data from Excel
	public String readExcel(String TestName) throws IOException{
		String output=null;
	    //Create an object of File class to open xlsx file
		String filePath = "TestData.xlsx";
	    File file =    new File(filePath);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook workbook = null;
	    //Check condition if the file is xlsx file
	    if(filePath.contains(".xlsx")){
	    	workbook = new XSSFWorkbook(inputStream);
	    }
	    else if(filePath.equals(".xls")){
	    	workbook = new HSSFWorkbook(inputStream);
	    }
	    //Read sheet inside the workbook by its name
	    Sheet sheet = workbook.getSheet("Sheet1");
	    //Find number of rows in excel file
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    //Create a loop over all the rows of excel file to read it
	    for (int i = 0; i < rowCount+1; i++) {
	        Row row = sheet.getRow(i);
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	        	if (row.getCell(j).getStringCellValue().equalsIgnoreCase(TestName)) {
	        		output = row.getCell(j+1).getStringCellValue();
	        		break;
	        	}
	            //System.out.print(row.getCell(j).getStringCellValue()+"|| ");
	        }
	        
	    } 
	    return output;
	 }  
   
}
