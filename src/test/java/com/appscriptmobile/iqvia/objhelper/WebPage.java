package com.appscriptmobile.iqvia.objhelper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.appscriptmobile.iqvia.baseclass.BaseUtil;
import com.appscriptmobile.iqvia.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WebPage extends BaseUtil {

	public WebPage(WebDriver driver,ExtentTest test)
	{
		this.driver=driver;
		this.test=test;
	}

	public static Xls_Reader xls=new Xls_Reader(System.getProperty("user.dir")
			+ "\\testdata\\Data.xlsx");

	/**
	 * @Desc Enter a value into the text field
	 * @param key
	 * @param value
	 * @throws IOException
	 * @author Hareesh HB
	 */
	public void type(String key, String value) throws IOException {
		readObject(key).sendKeys(value);
		test.log(LogStatus.INFO, "Entered  "+key+"  as "+value);
	}


	/**
	 * @Desc Clear the text field
	 * @param key
	 * @throws IOException
	 * @author Hareesh HB
	 */
	public void Clear(String key) throws IOException {
		readObject(key).clear();
		test.log(LogStatus.INFO, "Cleared the field ==> "+key);
	}


	/**
	 * @Desc Click on the Web element
	 * @param key
	 * @throws IOException
	 * @author Hareesh HB
	 */
	public void click(String key) throws IOException {
		readObject(key).click();
		test.log(LogStatus.INFO, "Clicked on "+key+" button");
	}


	/**
	 * @Desc Selects the value from drop down
	 * @param key
	 * @param value
	 * @throws IOException
	 * @author Hareesh HB
	 */
	public void selectByVisibleText(String key, String value) throws IOException{
		try {
			Select select= new Select(readObject(key));
			select.selectByVisibleText(value);
			test.log(LogStatus.INFO, "Selected  "+value+" from drop down");
		} catch (Exception e) {
			reportFailure(value +" NOT found");
		}
	}


	/**
	 * @Desc Selects the value from drop down
	 * @param key
	 * @param value
	 * @throws IOException
	 * @author Hareesh HB
	 */
	public void selectByValue(String key, String value) throws IOException{
		try {
			Select select= new Select(readObject(key));
			select.selectByValue(value);
			test.log(LogStatus.INFO, "Selected  "+value+" from drop down");
		} catch (Exception e) {
			reportFailure(value +" NOT found");
		}
	}


	/**
	 * @Desc Validates that element displayed or not
	 * @param key
	 * @throws IOException
	 * @author Hareesh HB
	 */

	public void isElementPresent(String key) throws IOException
	{
		if(readObject(key).isDisplayed())
		{
			reportPass(key+ " field found");
		}
		else
		{
			reportFailure(key+ " field NOT found");
		}
	}

	/**
	 * @Desc Keyboard -Tab action performed
	 * @throws AWTException
	 * @throws InterruptedException
	 * @author Hareesh HB
	 */

	public void keyTab() throws AWTException, InterruptedException{
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		test.log(LogStatus.INFO, "Keyboard - TAB key pressed");
	}


	/**
	 * @Desc Keyboard - Enter action performed
	 * @throws AWTException
	 * @throws InterruptedException
	 * @author Hareesh HB
	 */
	public void keyEnter() throws AWTException, InterruptedException{
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		test.log(LogStatus.INFO, "Keyboard - ENTER key pressed");
	}

	/**
	 * @Desc Page Refresh
	 *  @author Hareesh HB
	 */
	public void refreshPage()
	{
		driver.navigate().refresh();
		test.log(LogStatus.INFO, "Refreshed the page");
	}


	/**
	 * @Desc Switch To Child Window
	 *  @author Hareesh HB
	 */

	public void switchToChildWindow()
	{   
		String parent = driver.getWindowHandle();
		Set<String>	windowHandles= driver.getWindowHandles();
		Iterator<String> itr= windowHandles.iterator();
		while(itr.hasNext())
		{
			String child_window=itr.next();
			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				test.log(LogStatus.INFO, "Switched to Child Window: "+driver.switchTo().window(child_window).getTitle());
			}
		}
	}

	/**
	 * @Desc Switch To Parent Window
	 * @author Hareesh HB
	 */
	public void switchToParentWindow()
	{   
		String parent = driver.getWindowHandle();
		Set<String>	windowHandles= driver.getWindowHandles();
		Iterator<String> itr= windowHandles.iterator();
		while(itr.hasNext())
		{
			String child_window=itr.next();
			if(!parent.equals(child_window))
			{
				driver.switchTo().window(parent);
				test.log(LogStatus.INFO, "Switched to Parent Window: "+driver.switchTo().window(parent).getTitle());
			}
		}
	}
}

