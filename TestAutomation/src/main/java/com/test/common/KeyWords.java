package com.test.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class KeyWords {


	public static WebDriver driver;
	public static Properties config;
	public static Properties OR;
	public static FileInputStream fis;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	//public static FileInputStream fis1;

	public KeyWords() throws Exception {

		try {
			fis = new FileInputStream("E:\\MavenTestAutomation\\src\\main\\resources\\config.properties");
			/* We can remove the hard coding of the file path later */
			/*
			 * Q: Do we need to define two global variables for FileInputStream
			 * 
			 */ 
			config = new Properties();
			config.load(fis);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			fis = new FileInputStream("E:\\MavenTestAutomation\\src\\main\\resources\\ObjectRepository.properties");
			OR = new Properties();
			OR.load(fis);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		reporter = new ExtentHtmlReporter("./Reports/MyReport.html");
		extent =new ExtentReports();
		extent.attachReporter(reporter);

	}

	public static  void launchBrowser() {
		String browser = config.getProperty("browser");

		if (browser.equalsIgnoreCase("Chrome")) {
			logger.log(Status.INFO, "Opening chrome browser");

			System.setProperty("webdriver.chrome.driver","./lib/chromedriver.exe");
			driver = new ChromeDriver();

		} else if(browser.equalsIgnoreCase("ie")){

			System.setProperty("webdriver.chrome.driver","C:\\Users\\Redirection\\pravev2\\Desktop\\TD\\Selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new InternetExplorerDriver();
		}
		logger.log(Status.INFO, "Opening URL");

		driver.get("https://www.facebook.com/");
		logger.log(Status.INFO, "URL Opened successfully.");

	}

	public static void enterText(String locator, String value) {

		try {

			if (locator.endsWith("_ID")) {

				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);

			} else if (locator.endsWith("_name")) {

				driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);

			} else if (locator.endsWith("_class")) {

				driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
			}


			//logger.log(status, markup)
		} catch (Exception e) {

			logger.log(Status.FAIL, "Exception occured");
		}

	}

	public static void click(String locator) {
		System.out.println(OR.getProperty(locator));

		if (locator.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_name")) {

			driver.findElement(By.name(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}



	}


	/*public static void main(String[] args) throws Exception {
			KeyWords obj = new KeyWords();
			obj.launchBrowser("Chrome");
			//obj.enterText("EmailorPhone_ID", "praveeee@gmail.com");
			//obj.enterText("Password_ID", "hanumanji99%");
			obj.click("Login_XPATH");	



		}*/


}




