package com.appscriptmobile.iqvia.pagehelper;

import com.appscriptmobile.iqvia.objhelper.WebPage;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginPage extends WebPage{
	
	public LoginPage(AndroidDriver<AndroidElement> aDriver, ExtentTest test){
		 super(aDriver,test);
		}
	

}
