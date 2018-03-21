package com.appscriptmobile.iqvia.util;

public class Constants {

	// App parameters
	public static final String APK_PATH = System.getProperty("user.dir")+"\\apk\\RetailBanking_StaticApp.apk";
	public static final String DEVICE_NAME ="Redmi";
	public static final String DEVICE_VERSION = "6.0.1";
	public static final String PLATFORM = "Android";
	public static final String HUB_URL = "http://127.0.0.1:4723/wd/hub";	
	public static final String REPORT_PATH = System.getProperty("user.dir")+"\\report\\";
	public static final String XLS_PATH = System.getProperty("user.dir")+"\\testdata\\Data.xlsx";

	public static final String SCREENSHOT_PATH = System.getProperty("user.dir")+"\\Report\\screenshots\\";
	
	
	
public static final String GRID_RUN = "N";
	
	public static final String OBJECT_REPO_PATH=System.getProperty("user.dir")+"/objrepo/OR.xlsx";
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"/testdata/Data.xlsx";
	
	public static final String TESTDATA_SHEET = "TestData_And_RunMode";
	public static final String TESTCASES_SHEET = "TestCases_And_RunMode";
	public static final String RUNMODE_COL = "RunMode";
	
	public static final String IE_DRIVER_EXE=System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe";
	public static final String CHROME_DRIVER_EXE=System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
	public static final String CHROME_DRIVER_EXE_Linux = System.getProperty("user.dir")+"\\drivers\\chromedriver";
	public static final String FIREFOX_DRIVER_EXE=System.getProperty("user.dir")+"/drivers/geckodriver.exe";
	public static final String FIREFOX_DRIVER_EXE_Linux = System.getProperty("user.dir")+"\\drivers\\geckodriver";
	
	public static final int MAX_WAIT_TIME_SECONDS = 20;
}
