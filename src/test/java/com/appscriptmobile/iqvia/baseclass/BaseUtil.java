package com.appscriptmobile.iqvia.baseclass;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.appscriptmobile.iqvia.util.Constants;
import com.appscriptmobile.iqvia.util.ExtentManager;
import com.appscriptmobile.iqvia.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class BaseUtil {
	
	public Xls_Reader xls=new Xls_Reader(Constants.DATA_XLS_PATH);
	public WebDriver driver;
	public ExtentReports extent=ExtentManager.getInstance();
	public ExtentTest test;
	public static Xls_Reader datatable;
	public DesiredCapabilities cap=null;
	public AndroidDriver<AndroidElement> aDriver;

	/**
	 * @Desc: Launch an App
	 * @throws InterruptedException
	 *  @author Hareesh HB
	 */
	public void launchApp() throws InterruptedException{
		File app = new File(Constants.APK_PATH);
	     DesiredCapabilities capabilities =DesiredCapabilities.android();
	     capabilities.setCapability("deviceName",Constants.DEVICE_NAME);
	     capabilities.setCapability("platformVersion", Constants.DEVICE_VERSION);
	     capabilities.setCapability("platformName",Constants.PLATFORM);
		 capabilities.setCapability("app", app.getAbsolutePath());
		try {
			test.log(LogStatus.INFO, "Launching Retail Banking App ");
			driver = new AndroidDriver<AndroidElement>(new URL(Constants.HUB_URL), capabilities);
		   aDriver = (AndroidDriver<AndroidElement>)driver;
		} catch (MalformedURLException e) {
			test.log(LogStatus.FAIL, "Application did not launch "+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Application did not launch"+ e.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
		test.log(LogStatus.PASS, "App Launched Successfully ");	
		takeScreenshot();
	}
	
	/*public void takeScreenshot(){
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath="./screenshots/"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)aDriver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File("/screenshots", screenshotFile);

		try {
			//FileUtils.copyFile(scrFile, new File(filePath));
			FileUtils.copyFile(scrFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));
	}*/ 
	
	public void takeScreenshot(){
		Date d = new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
		String path=Constants.SCREENSHOT_PATH+screenshotFile;
		// take screenshot
		File scrFile = ((TakesScreenshot)aDriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,"Snapshot below: ("+screenshotFile+")"+
				 test.addScreenCapture(path));
	}
	
	/*@AfterMethod
	public void quit(){
		if(rep!=null){
			rep.endTest(test);
			rep.flush();
		}
		if(driver!=null)
			driver.quit();
		
	}*/
	
	/**
	 * @Desc Reads the locators from Excel Sheet
	 * @param key
	 * @return
	 * @throws IOException
	 * @author Hareesh HB
	 */
	public WebElement readObject(String key) throws IOException{
		Wait<WebDriver> wait= new WebDriverWait(driver,Constants.MAX_WAIT_TIME_SECONDS);
		String strControllerPath = System.getProperty("user.dir")
				+ "\\objrepo\\OR.xlsx";
		datatable = new Xls_Reader(strControllerPath);
		Object[][] data= datatable.getData("Elements");
		for(int row=0; row<data.length; row++){
			try {
				if(data[row][0].equals(key)){
					String identifier= (String) data[row][1];
					String locator= (String) data[row][2];
					if(identifier.equalsIgnoreCase("xpath")){
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
						return driver.findElement(By.xpath(locator));
					} else if(identifier.equalsIgnoreCase("id")){
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
						return driver.findElement(By.id(locator));
					} else if(identifier.equalsIgnoreCase("name")){
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
						return driver.findElement(By.name(locator));
					}
				}
			} catch (NoSuchElementException e) {
				reportFailure(e.getMessage());
			}
			catch (StaleElementReferenceException e) {
				reportFailure(e.getMessage());
			}
			catch (Exception e) {
				reportFailure(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * @Desc Takes the Screenshot
	 * @author Hareesh HB
	 */
	public void takeScreenShot(){
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath="./screenshots/"+screenshotFile;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File(Constants.REPORT_PATH+"/screenshots", screenshotFile);

		try {
			FileUtils.copyFile(scrFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));
	} 
	
	/**
	 * @Desc Captures Extent report log with screenshot 
	 * @param failureMessage
	 * @author Hareesh HB
	 */
	public void reportFailure(String failureMessage){
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}
	
	/**
	 * @Desc Captures Extent report log with screenshot 
	 * @param failureMessage
	 * @author Hareesh HB
	 */

	public void reportPass(String successMessage){
		test.log(LogStatus.PASS, successMessage);
		takeScreenShot();
	}
}
