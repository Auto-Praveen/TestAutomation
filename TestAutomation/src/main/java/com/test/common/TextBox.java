package com.test.common;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

public class TextBox extends KeyWords {

	public TextBox() throws Exception {
		super();
		// TODO Auto-generated constructor stub
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
}
