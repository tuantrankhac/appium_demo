package vt.testSuite;

import common.commonBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import vt.pageObject.dashboardPO;
import vt.pageObject.loginPO;
import vt.pageObject.thongTinTaiKhoanPO;
import vt.pageUi.dashboardPageUI;
import vt.pageUi.loginPageUI;
import vt.pageUi.thongTinTaiKhoanPageUI;

import static common.TestLogger.info;


public final class Cap_Nhat_Thong_Tin_Tai_Khoan extends commonBase {

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
		info("Tap vào nút Bỏ qua");
		click(loginPageUI.SKIP_BT);
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
	public void TC_002_Login_Thanh_Cong_Voi_So_DD(){
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

	@Test
	public void TC_003_Kiem_Tra_Thong_Tin_Tai_Khoan() {
		info("Bấm vào tên tài khoản");
		thongTinTaiKhoanPage = dashboardPage.pressToTenTaiKhoan();
		info("Mở tab Cá nhân");
		getElementPresent(thongTinTaiKhoanPageUI.THUE_BAO_TAB);
		getElementPresent(thongTinTaiKhoanPageUI.CA_NHAN_TAB);
		getElementPresent(thongTinTaiKhoanPageUI.HO_TEN_TK);
		getElementPresent(thongTinTaiKhoanPageUI.NGAY_SINH);
		getElementPresent(thongTinTaiKhoanPageUI.NGAY_CAP);
		getElementPresent(thongTinTaiKhoanPageUI.CCCD);
		getElementPresent(thongTinTaiKhoanPageUI.NOI_CAP);
		scrollDown(2);
		getElementPresent(thongTinTaiKhoanPageUI.EMAIL_TEXTBOX);
		getElementPresent(thongTinTaiKhoanPageUI.CHON_NGHE_NGHIEP_DROPDOWN);
		getElementPresent(thongTinTaiKhoanPageUI.CHON_SO_THICH_DROPDOWN);
		getElementPresent(thongTinTaiKhoanPageUI.CAP_NHAT_BUTTON);
	}

	@Test
	public void TC_004_Cap_Nhat_Thong_Tin_Nhung_Khong_Luu() {

	}

	@Test
	public void TC_005_Cap_Nhat_Thong_Tin_Tai_Khoan_Thanh_Cong() {}


	@AfterClass (alwaysRun = true)
	public void after() {
		quitDriver(driver);
	}

	loginPO loginPage;
	dashboardPO dashboardPage;
	thongTinTaiKhoanPO thongTinTaiKhoanPage;
}
