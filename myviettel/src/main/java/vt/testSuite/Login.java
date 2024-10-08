package vt.testSuite;

import common.commonBase;
import vt.pageObject.dashboardPO;
import vt.pageObject.loginPO;
import vt.pageUi.dashboardPageUI;
import vt.pageUi.loginPageUI;
import org.testng.annotations.*;

import static common.TestLogger.info;


public final class Login extends commonBase {

	@BeforeClass
	public void init() {
		driver = initDriverTestAndroid("R5CW134H25Z", "Android", "14.0", "com.vttm.vietteldiscovery",
				"com.vttm.myviettelV2.activity.NFirstUserActivity", "8207");
		loginPage = new loginPO(driver);
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
	public void TC_002_Login_Voi_Du_Lieu_Trong_Tab_Di_Dong() {
		info("Bấm vào nút 'Đăng nhập'");
		loginPage.pressToLoginButton();
		info("Verify message hiển thị tại trường SĐT");
		String sdtTrongMessage = getText(loginPageUI.VALIDATE_MESSAGE_SDT);
		verifyCompare(sdtTrongMessage, "Số điện thoại không được để trống.");
		info("Nhập vào số điện thoại hợp lệ");
		loginPage.enterSDTToTextbox("0342992917");
		info("Bấm vào nút 'Đăng nhập'");
		loginPage.pressToLoginButton();
		info("Verify message hiển thị tại trường Mật khẩu");
		String passwordTrongMessage = getText(loginPageUI.VALIDATE_MESSAGE_PASSWORD);
		verifyCompare(passwordTrongMessage,"Mật khẩu không được để trống!");
	}

	@Test
	public void TC_003_Login_Voi_SDD_Khong_Hop_Le() {
		info("Nhập vào số điện thoại nhỏ hơn 9 ký tự");
		loginPage.enterSDTToTextbox("033333");
		info("Bấm vào nút 'Đăng nhập'");
		loginPage.pressToLoginButton();
		info("Verify message hiển thị tại trường SĐT");
		String validateSdtMessage = getText(loginPageUI.VALIDATE_MESSAGE_SDT);
		verifyCompare(validateSdtMessage, "Số điện thoại từ 9 đến 12 ký tự.");
		info("Nhập vào số điện thoại lớn hơn 12 ký tự");
		loginPage.enterSDTToTextbox("12324252525252");
		info("Bấm vào nút 'Đăng nhập'");
		loginPage.pressToLoginButton();
		info("Verify message hiển thị tại trường SĐT");
		verifyCompare(validateSdtMessage, "Số điện thoại từ 9 đến 12 ký tự.");
	}

	@Test
	public void TC_004_Login_Voi_Mat_Khau_Khong_Chinh_Xac(){
		info("Nhập vào số điện thoại hợp lệ");
		loginPage.enterSDTToTextbox("0342992917");
		info("Nhập vào mật khẩu không chính xác");
		loginPage.enterMKToTextbox("123456");
		info("Bấm vào nút 'Đăng nhập'");
		loginPage.pressToLoginButton();
		info("Verify message hiển thị");
		isElementDisplayed(loginPageUI.INVALID_SDT_PASS_MESSAGE);

	}

	@Test
	public void TC_005_Login_Voi_Du_Lieu_Trong_Tab_Co_Dinh(){
		info("Bấm nút quay lại");
		click(loginPageUI.BACK_BT);
		info("Mở tab đăng nhập với Internet/TV/PSTN");
		click(loginPageUI.CO_DINH_TAB);
		info("Bấm vào nút 'Đăng nhập'");
		loginPage.pressToLoginButton();
		info("Kiểm tra message hiển thị");
		isElementDisplayed(loginPageUI.TK_TRONG_MESSAGE);
		info("Nhập vào trường tài khoản");
		loginPage.enterSDTToTextbox("h004_ftth_tuantk12");
		info("Bấm vào nút 'Đăng nhập'");
		loginPage.pressToLoginButton();
		info("Kiểm tra message hiển thị");
		isElementDisplayed(loginPageUI.MK_TRONG_MESSAAAGE);

	}

	@Test
	public void TC_006_Login_So_Co_Dinh_Khong_Hop_Le(){
		info("Nhập vào mật khẩu không hợp lệ");
		loginPage.enterMKToTextbox("123456a@");
		info("Bấm vào nút 'Đăng nhập'");
		loginPage.pressToLoginButton();
		info("Kiểm tra message hiển thị tại trường mật khẩu");
		String passInvalidMessage = getText(loginPageUI.VALIDATE_MESSAGE_PASSWORD);
		verifyCompare(passInvalidMessage, "Tài khoản đăng nhập không hợp lệ");

	}

	@Test
	public void TC_007_Login_Thanh_Cong_Voi_So_DD(){
		info("Bấm nút quay lại");
		click(loginPageUI.BACK_BT);
		info("Mở tab đăng nhập với số Di động");
		click(loginPageUI.DI_DONG_TAB);
		info("Nhập vào số điện thoại hợp lệ");
		loginPage.enterSDTToTextbox("0342992917");
		info("Nhập vào mật hợp lệ");
		loginPage.enterMKToTextbox("vt123456a@");
		info("Bấm vào nút 'Đăng nhập'");
		dashboardPage = loginPage.pressToLoginButton();
		info("Cho phép nhận thông báo");
		dashboardPage.pressToAllowButton();
		info("Verify số điện thoại đã login được hiển thị");
		isElementDisplayed(dashboardPageUI.THONG_TIN_SDT);
	}

	@AfterClass (alwaysRun = true)
	public void after() {
		quitDriver(driver);
	}

	loginPO loginPage;
	dashboardPO dashboardPage;
}
