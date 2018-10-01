package com.practice;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.annotations.Test;


public class MyReports {

	@Test
	public void sample() {
		
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/MyReport.html");
		ExtentReports extent =new ExtentReports();
		extent.attachReporter(reporter);
	
		ExtentTest logger = extent.createTest("Login test");
		logger.log(Status.INFO, "Testing");
		
		extent.flush();
		
	
	}

}
