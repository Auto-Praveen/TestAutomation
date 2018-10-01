package com.test.common;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.test.util.ReadExcel;

public class Driver extends KeyWords{

	public Driver() throws Exception {
		super();
	}

	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE){
			logger.fail(MarkupHelper.createLabel(result.getName()+" Test Case is FAILED", ExtentColor.RED));
			logger.fail(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			logger.pass(MarkupHelper.createLabel(result.getName()+" Test Case is PASSED",ExtentColor.GREEN));
		}
		else {
			logger.skip(MarkupHelper.createLabel(result.getName()+" Test Case is SKIPPED",ExtentColor.YELLOW));
			logger.skip(result.getThrowable());
		}
		extent.flush();
	}	

	@Test
	public void testcases() throws Exception {
		KeyWords obj = new KeyWords();
		logger = extent.createTest("Start Test");
		logger.log(Status.INFO, "Inside keywords class");
		ReadExcel re = new ReadExcel("E:\\MavenTestAutomation\\src\\main\\resources\\data.xlsx");
		//obj.launchBrowser("Chrome");
		//obj.enterText("EmailorPhone_ID", "praveeee@gmail.com");
		//obj.enterText("Password_ID", "hanumanji99%");
		//obj.click("Login_XPATH");
		int rows = re.getRowCount("Sheet1");

		for(int i=2;i<=rows;i++) {
			//System.out.println(re.getCellData("Sheet1", "Keyword", i));
			String keyword = re.getCellData("Sheet1", "Keyword", i);
			String locator = re.getCellData("Sheet1", "Locator", i);
			String data = re.getCellData("Sheet1", "Data", i);

			switch (keyword) {
			case "launchBrowser":
				KeyWords.launchBrowser();
				break;
			case "enterText":
				TextBox.enterText(locator, data);
				break;
			case "click":
				KeyWords.click(locator);
				break;
			}

		}
	}
}