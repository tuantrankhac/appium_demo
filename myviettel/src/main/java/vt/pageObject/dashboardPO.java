package vt.pageObject;

import common.commonBase;
import io.appium.java_client.AppiumDriver;
import vt.pageUi.dashboardPageUI;

public class dashboardPO extends commonBase {

	public dashboardPO(AppiumDriver driver) {
		this.driver = driver;
	}

	public static dashboardPO getDashboardPage(AppiumDriver driver) {
		return new dashboardPO(driver);
	}

	public void pressToAllowButton(){
		waitForElementClickable(dashboardPageUI.ALLOW_NOTIF_BT);
		click(dashboardPageUI.ALLOW_NOTIF_BT);
	}

	public thongTinTaiKhoanPO pressToTenTaiKhoan(){
		waitForElementClickable(dashboardPageUI.THONG_TIN_TAI_KHOAN);
		click(dashboardPageUI.THONG_TIN_TAI_KHOAN);
		return thongTinTaiKhoanPO.getDashboardPage(driver);
	}


}

