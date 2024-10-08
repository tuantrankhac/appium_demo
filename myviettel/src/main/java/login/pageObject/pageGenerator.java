package login.pageObject;

import common.commonBase;
import io.appium.java_client.AppiumDriver;
import login.pageUi.loginPageUI;
import org.openqa.selenium.WebDriver;

public class pageGenerator extends commonBase {

	public pageGenerator(AppiumDriver dr) {
		this.driver = dr;
	}

	public static loginPO getHomePage(AppiumDriver driver) {
		return new loginPO(driver);
	}
}
