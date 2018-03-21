package com.appscriptmobile.iqvia.util;

import java.util.Hashtable;

public class DataUtil {
	
	/**
	 * @Desc Read the data from excel sheet- To Run Test Scripts for multiple iterations
	 * @param xls
	 * @param testCaseName
	 * @return Object[][]
	 * @author Hareesh HB
	 */

	public static Object[][] getData(Xls_Reader xls, String testCaseName){
		String sheetName=Constants.TESTDATA_SHEET;
		int testStartRowNum=1; 
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)){
			testStartRowNum++;
		}
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
		Object[][] data = new Object[rows][1];
		int dataRow=0;
		Hashtable<String,String> table=null;
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key=xls.getCellData(sheetName,cNum,colStartRowNum);
				String value= xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
			}
			data[dataRow][0] =table;
			dataRow++;
		}
		return data;
	}

	
	/**
	 * @Desc Checks that test case RunMode is Y or N
	 * @param xls
	 * @param testCaseName
	 * @return boolean 
	 * @author Hareesh HB
	 */
	public static boolean isTestExecutable(Xls_Reader xls, String testCaseName){
		int rows = xls.getRowCount(Constants.TESTCASES_SHEET);
		for(int rNum=2;rNum<=rows;rNum++){
			String tcid = xls.getCellData(Constants.TESTCASES_SHEET, "TCID", rNum);
			if(tcid.equals(testCaseName)){
				String runmode = xls.getCellData(Constants.TESTCASES_SHEET, "RunMode", rNum);
				if(runmode.equals("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}
}
