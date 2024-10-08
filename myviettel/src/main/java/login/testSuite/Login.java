package login.testSuite;


import common.commonBase;
import io.appium.java_client.AppiumDriver;
import login.pageObject.loginPO;
import login.pageUi.loginPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static common.TestLogger.info;


public final class Login extends commonBase {

	@BeforeClass
	public void init() {
		driver = initDriverTestAndroid("R5CW134H25Z", "Android", "14.0", "com.vttm.vietteldiscovery",
				"com.vttm.myviettelV2.activity.NFirstUserActivity", "8207");
	}

	@Test
	public void TC_001_Check_Man_Hinh_Welcome() {
		info("Open app");
		getElementPresent(loginPageUI.CONT_BT);
		getElementPresent(loginPageUI.SKIP_BT);
		info("Tap vào nút Tiếp tục");
		click(loginPageUI.CONT_BT);
		info("Tap vào nút Tiếp tục");
		click(loginPageUI.CONT_BT);
		info("Tap vào nút Khám phá ngay");
		getElementPresent(loginPageUI.KHAM_PHA_BT);
		click(loginPageUI.KHAM_PHA_BT);
		info("Kiểm tra các thành phần trên màn");
		getElementPresent(loginPageUI.DI_DONG_TAB);
		getElementPresent(loginPageUI.CO_DINH_TAB);
		getElementPresent(loginPageUI.SDT_FIELD);
		getElementPresent(loginPageUI.LOGIN_BY_OTP_BT);
		getElementPresent(loginPageUI.LOGIN_BY_MK_BT);
		getElementPresent(loginPageUI.LOGIN_BY_3G_4G_BT);
		getElementPresent(loginPageUI.DKY_NGAY_LINK);
		info("Click vào nút đăng nhập bằng mật khẩu");
		click(loginPageUI.LOGIN_BY_MK_BT);
		getElementPresent(loginPageUI.SDT_FIELD);
		getElementPresent(loginPageUI.PW_FIELD);
		getElementPresent(loginPageUI.LOGIN_BT);
		getElementPresent(loginPageUI.FINGER_PRINT_BT);
		getElementPresent(loginPageUI.FORGOT_PW_LINK);
		getElementPresent(loginPageUI.DKY_NGAY_LINK);
	}

	@Test
	public void TC_002_Login_Voi_Du_Lieu_Trong() {
		info("Bấm vào nút 'Đăng nhập'");
		click(loginPageUI.LOGIN_BT);
		info("Verify message hiển thị tại trường SĐT");
		String sdtTrongMessage = getText(loginPageUI.VALIDATE_MESSAGE_SDT);
		verifyCompare(sdtTrongMessage, "Số điện thoại không được để trống.");
		info("Nhập vào số điện thoại hợp lệ");
		loginPage.enterSDTToTextbox("0342992917");
		info("Bấm vào nút 'Đăng nhập'");
		click(loginPageUI.LOGIN_BT);
		info("Verify message hiển thị tại trường Mật khẩu");
		String passwordTrongMessage = getText(loginPageUI.VALIDATE_MESSAGE_PASSWORD);
		verifyCompare(passwordTrongMessage,"Mật khẩu không được để trống.");
	}


	@Test
	public void TC_003_Login_Voi_SDT_Khong_Hop_Le() {
		info("Nhập vào số điện thoại nhỏ hơn 9 ký tự");
		loginPage.enterSDTToTextbox("033333");
		info("Bấm vào nút 'Đăng nhập'");
		click(loginPageUI.LOGIN_BT);
		info("Verify message hiển thị tại trường SĐT");
		String validateSdtMessage = getText(loginPageUI.VALIDATE_MESSAGE_SDT);
		verifyCompare(validateSdtMessage, "Số điện thoại từ 9 đến 12 ký tự.");
		info("Nhập vào số điện thoại lớn hơn 12 ký tự");
		loginPage.enterSDTToTextbox("12324252525252");
		info("Bấm vào nút 'Đăng nhập'");
		click(loginPageUI.LOGIN_BT);
		info("Verify message hiển thị tại trường SĐT");
		verifyCompare(validateSdtMessage, "Số điện thoại từ 9 đến 12 ký tự.");
	}


	@AfterClass (alwaysRun = true)
	public void after() {
		quitDriver(driver);
	}

	loginPO loginPage;
}
