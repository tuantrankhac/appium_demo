package login.pageObject;

import common.commonBase;
import login.pageUi.loginPageUI;


import io.appium.java_client.AppiumDriver;

public class loginPO extends commonBase {

	public loginPO(AppiumDriver dr) {
		this.driver = dr;
	}

	public void enterSDTToTextbox (String value) {
		waitForElementVisible(driver, loginPageUI.SDT_FIELD);
		sendKeyToElement(loginPageUI.SDT_FIELD, value);
	}
}
