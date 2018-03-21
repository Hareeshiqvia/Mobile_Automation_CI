package com.appscriptmobile.iqvia.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appscriptmobile.iqvia.baseclass.BaseUtil;
import com.appscriptmobile.iqvia.pagehelper.HomePage;
import com.appscriptmobile.iqvia.pagehelper.LoginPage;
import com.appscriptmobile.iqvia.util.Constants;
import com.appscriptmobile.iqvia.util.DataUtil;
import com.relevantcodes.extentreports.LogStatus;



public class LoginTest extends BaseUtil {

	String testCaseName="LoginTest";		//Change This
	String testCaseDesc = "Login To AppScript App"; //Change This

	//********************************DO NOT CHANGE ANYTHING-Line Num 24 to 39****************************************************

	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException
	{
		test=extent.startTest(testCaseName+"_"+data.get("Country")+" - "+data.get("Browser"),testCaseDesc);
		if(!DataUtil.isTestExecutable(xls, testCaseName) ||  data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as RunMode is N");
			throw new SkipException("Skipping the test as RunMode is N");
		}
		test.log(LogStatus.INFO,"Starting  "+ testCaseName +" Test Script Execution");
		//Launch the app
		launchApp();
		//***************************************DO NOT CHANGE ANYTHING**************************************************
		//go to Login Page
		HomePage home_page = new HomePage(aDriver,test);
		
	}

	@AfterMethod
	public void tearDown() throws InterruptedException, IOException {
		if(driver!=null)
		{
			try
			{
				LoginPage login_page=new LoginPage(aDriver,test);
				//login_page.logOut();
				driver.quit();
			}
			catch(Exception e)
			{
				driver.quit();
			}

			if(extent !=null) {
				extent.endTest(test);
				extent.flush();
			}
		}
	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(xls, testCaseName);
	}
}
