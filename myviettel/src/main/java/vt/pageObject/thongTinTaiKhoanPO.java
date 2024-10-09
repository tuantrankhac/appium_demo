package vt.pageObject;

import common.commonBase;
import io.appium.java_client.AppiumDriver;
import vt.pageUi.dashboardPageUI;

public class thongTinTaiKhoanPO extends commonBase {

	public thongTinTaiKhoanPO(AppiumDriver driver) {
		this.driver = driver;
	}

	public static thongTinTaiKhoanPO getDashboardPage(AppiumDriver driver) {
		return new thongTinTaiKhoanPO(driver);
	}

}
