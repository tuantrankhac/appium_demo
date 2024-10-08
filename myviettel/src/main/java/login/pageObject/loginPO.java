package login.pageObject;

import common.commonBase;
import login.pageUi.loginPageUI;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class loginPO extends commonBase {

	public loginPO(AppiumDriver dr) {
		driver = dr;
	}

	public void enterSDTToTextbox(String value) {
		waitForElementVisible(loginPageUI.SDT_FIELD);
		sendKeyToElement(loginPageUI.SDT_FIELD, value);
	}

	public void enterMKToTextbox(String value) {
		waitForElementVisible(loginPageUI.PW_FIELD);
		sendKeyToElement(loginPageUI.PW_FIELD, value);
	}

	public boolean invalidSDTPasswordMessageIsDisplayed() {
		waitForElementVisible(loginPageUI.INVALID_SDT_PASS_MESSAGE);
		return isDisplay(loginPageUI.INVALID_SDT_PASS_MESSAGE);
	}
}
