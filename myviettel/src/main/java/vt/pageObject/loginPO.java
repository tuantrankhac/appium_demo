package vt.pageObject;

import common.commonBase;
import vt.pageUi.loginPageUI;
import io.appium.java_client.AppiumDriver;

public class loginPO extends commonBase {



	public loginPO(AppiumDriver driver) {
		this.driver = driver;
	}

	public dashboardPO pressToLoginButton() {
		waitForElementClickable(loginPageUI.LOGIN_BT);
		click(loginPageUI.LOGIN_BT);
		return dashboardPO.getDashboardPage(driver);
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
