package com.appscriptmobile.iqvia.util;


import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	
	/**
	 * @Desc Extent report Configurations
	 * @return Extentreport Object
	 * @author Hareesh HB
	 */

	public static ExtentReports getInstance() {
		if (extent == null) {
			//Date d=new Date();
			//String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			 String fileName="index"+".html";
			 //String folderPath =Constants.REPORTS_PATH+"/"+dir.mkdir();
			String reportPath =Constants.REPORT_PATH+fileName;
			 
			extent = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+"/config/ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "2.53.0").addSystemInfo(
					"Environment", "TestEnv");
		}
		return extent;
	}
}
