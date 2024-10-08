//package login.testSuite;
//
//
//import common.baseTest;
//import common.commonBase;
//import login.pageUi.loginPageUI;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import static common.TestLogger.info;
//
//
//public final class TS_01_Logic_ChatAI_Chat extends commonBase {
//
//	@BeforeMethod
//	public void init() {
//		driver = initDriverTest("ChatAI.apk", "emulator-5554", "Android", "12.0", "com.begamob.chatgpt_openai",
//				"com.begamob.chatgpt_openai.feature.splash.SplashActivity", "windows", "8201");
//		managementAccount = new baseTest(driver);
//	}
//
//	@AfterMethod
//	public void after() {
//		quitDriver(driver);
//	}
//
//	@Test
//	public void TS_001_Logic_ChatAI_Chat_Nhap_Noi_Dung_Hop_Le() {
//		info("Open app");
//		//managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		getElementPresent(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.ICON_LOUDSPEAKER);
//		getElementPresent(loginPageUI.TEXTBOX_ANSWER_USER);
//		getElementPresent(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElementPresent(loginPageUI.BTN_REGENERATE);
//		getElementPresent(loginPageUI.BTN_COPY);
//		getElementPresent(loginPageUI.BUTTON_SHARE);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		getElementPresent(loginPageUI.TXTB_ASK_A_QUESTION);
//		getElementPresent(loginPageUI.BUTTON_SEND);
//		getElementPresent(loginPageUI.BTN_IMAGE);
//		getElementPresent(loginPageUI.BTN_VOID_CHAT);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "2");
//		String USER_ASK2 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "2");
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION, "What is your name");
//		click(loginPageUI.BUTTON_SEND);
//		getElementPresent(USER_ASK2);
//		getElementPresent(chatReply2);
//
//	}
//	@Test
//	public void TS_002_Logic_ChatAI_Chat_Nhap_Khoang_Trang() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "           ");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		getElementPresent(chatReply);
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION, "          ");
//		verifyContains("10", getText(loginPageUI.TEXT_NUMBER_OF_CHARACTERS));
//		click(loginPageUI.BUTTON_SEND);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "2");
//		checkElementNotPresent(chatReply2, 5000);
//	}
//	@Test
//	public void TS_003_Logic_ChatAI_Chat_Nhap_Nhieu_Hon_300_Ky_Tu() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "auto test");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION, generateRandomString(333));
//		verifyCompare("300/300", getValue(loginPageUI.TEXT_NUMBER_OF_CHARACTERS));
//		click(loginPageUI.BUTTON_SEND);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "2");
//		String USER_ASK2 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "2");
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION, "What is your name");
//		click(loginPageUI.BUTTON_SEND);
//		getElementPresent(USER_ASK2);
//		getElementPresent(chatReply2);
//	}
//	@Test
//	public void TS_004_Logic_ChatAI_Chat_Regenerate() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		click(loginPageUI.BTN_REGENERATE);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "2");
//		getElementPresent(chatReply2);
//	}
//	@Test
//	public void TS_005_Logic_ChatAI_Chat_Coppy() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		click(loginPageUI.BTN_COPY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//
//	}
//	@Test
//	public void TS_006_Logic_ChatAI_Chat_Share() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		click(loginPageUI.BUTTON_SHARE);
//		getElementPresent(loginPageUI.OPTION_APPLICATION);
//	}
//	@Test
//	public void TS_007_Logic_ChatAI_Chat_History_1_Lich_Su_Chat() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		click(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK2 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply2);
//		getElementPresent(USER_ASK2);
//
//	}
//	@Test
//
//	public void TS_008_Logic_ChatAI_Chat_History_Nhieu_Hon_1_Lich_Su_Chat() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BUTTON_SEND);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "2");
//		String USER_ASK2 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "2");
//		getElementPresent(USER_ASK2);
//		getElementPresent(chatReply2);
//
//		click(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1_1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1);
//		getElementPresent(USER_ASK1_1);
//
//		String chatReply2_2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "2");
//		String USER_ASK2_2 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "2");
//		getElementPresent(USER_ASK2_2);
//		getElementPresent(chatReply2_2);
//
//	}
//	@Test
//	public void TS_009_Logic_ChatAI_Chat_History_Nhieu_Hon_1_Lich_Su_Chat_Man_Home() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		click(loginPageUI.BTN_LEFT);
//		clickElementIfDisplay(loginPageUI.BTN_LEFT);
//		clickElementIfDisplay(loginPageUI.BTN_DISMISS);
//
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK2 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK2);
//		getElementPresent(chatReply2);
//
//		click(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1_1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1);
//		getElementPresent(USER_ASK1_1);
//
//		String chatReply2_2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "2");
//		String USER_ASK2_2 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "2");
//		getElementPresent(chatReply2_2);
//		getElementPresent(USER_ASK2_2);
//
//	}
//	@Test
//	public void TS_010_Logic_ChatAI_Chat_History_Nhap_KHoang_Trang() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "           ");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		getElementPresent(chatReply);
//		click(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		checkElementNotPresent(chatReply1, 3000);
//		checkElementNotPresent(USER_ASK1, 3000);
//
//	}
//	@Test
//	public void TS_011_Logic_ChatAI_Chat_History_Delete_0_Lich_Su() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "           ");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		getElementPresent(chatReply);
//		click(loginPageUI.ICON_HISTORY);
//
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		checkElementNotPresent(chatReply1, 5000);
//		checkElementNotPresent(USER_ASK1, 5000);
//		click(loginPageUI.BTN_DELETE);
//		getElementPresent(loginPageUI.TITLE_HISTORY);
//		getElementPresent(loginPageUI.BTN_DELETE);
//	}
//	@Test
//	public void TS_012_Logic_ChatAI_Chat_History_Delete_Choose_All_1_Lich_Su() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(chatReply);
//		getElementPresent(USER_ASK);
//		click(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1);
//		getElementPresent(USER_ASK1);
//		click(loginPageUI.BTN_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		String chatReply1_1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1);
//		getElementPresent(USER_ASK1_1);
//		String chosseConversation = parseStringToObject(loginPageUI.OPTION_CHOOSE, "1");
//		getElementPresent(chosseConversation);
//		getElementPresent(loginPageUI.BUTTON_DELETE);
//		click(loginPageUI.BTN_CHOOSE_ALL);
//		pause(3000);
//		verifyContains("1", getText(loginPageUI.BUTTON_DELETE));
//		click(loginPageUI.BUTTON_DELETE);
//		getElementPresent(loginPageUI.TEXT_CONFIRM_DELETE);
//		getElementPresent(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(loginPageUI.BTN_CANCEL_DELETE);
//		click(loginPageUI.BTN_CANCEL_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		click(loginPageUI.BUTTON_DELETE);
//		click(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		getElementPresent(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.ICON_LOUDSPEAKER);
//		click(loginPageUI.ICON_HISTORY);
//		checkElementNotPresent(chatReply1, 3000);
//		checkElementNotPresent(USER_ASK1, 3000);
//	}
//	@Test
//	public void TS_013_Logic_ChatAI_Chat_History_Delete_Choose_One_1_Lich_Su() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(chatReply);
//		getElementPresent(USER_ASK);
//		click(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1);
//		getElementPresent(USER_ASK1);
//		click(loginPageUI.BTN_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		String chatReply1_1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1);
//		getElementPresent(USER_ASK1_1);
//		String chosseConversation = parseStringToObject(loginPageUI.OPTION_CHOOSE, "1");
//		getElementPresent(chosseConversation);
//		getElementPresent(loginPageUI.BUTTON_DELETE);
//		click(chosseConversation);
//		pause(3000);
//		verifyContains("1", getText(loginPageUI.BUTTON_DELETE));
//		click(loginPageUI.BUTTON_DELETE);
//		getElementPresent(loginPageUI.TEXT_CONFIRM_DELETE);
//		getElementPresent(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(loginPageUI.BTN_CANCEL_DELETE);
//		click(loginPageUI.BTN_CANCEL_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		click(loginPageUI.BUTTON_DELETE);
//		click(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		getElementPresent(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.ICON_LOUDSPEAKER);
//		click(loginPageUI.ICON_HISTORY);
//		checkElementNotPresent(chatReply1, 3000);
//		checkElementNotPresent(USER_ASK1, 3000);
//	}
//	@Test
//	public void TS_014_Logic_ChatAI_Chat_History_Delete_Choose_All_Nheu_Hon_1_Lich_Su() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION, "Hi");
//		click(loginPageUI.BUTTON_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "2");
//		String USER_ASK2 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "2");
//		getElementPresent(USER_ASK2);
//		getElementPresent(chatReply2);
//		click(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1_1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1);
//		getElementPresent(USER_ASK1_1);
//		String chatReply2_2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "2");
//		String USER_ASK2_2 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "2");
//		getElementPresent(chatReply2_2);
//		getElementPresent(USER_ASK2_2);
//		click(loginPageUI.BTN_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		String chatReply1_1_1= parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1_1);
//		getElementPresent(USER_ASK1_1_1);
//		String chatReply2_2_2= parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "2");
//		String USER_ASK2_2_2 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "2");
//		getElementPresent(chatReply2_2_2);
//		getElementPresent(USER_ASK2_2_2);
//		String chosseConversation = parseStringToObject(loginPageUI.OPTION_CHOOSE, "1");
//		getElementPresent(chosseConversation);
//		String chosseConversation2 = parseStringToObject(loginPageUI.OPTION_CHOOSE, "2");
//		getElementPresent(chosseConversation2);
//		getElementPresent(loginPageUI.BUTTON_DELETE);
//		click(loginPageUI.BTN_CHOOSE_ALL);
//		pause(3000);
//		verifyContains("2", getText(loginPageUI.BUTTON_DELETE));
//		click(loginPageUI.BUTTON_DELETE);
//		getElementPresent(loginPageUI.TEXT_CONFIRM_DELETE);
//		getElementPresent(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(loginPageUI.BTN_CANCEL_DELETE);
//		click(loginPageUI.BTN_CANCEL_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		click(loginPageUI.BUTTON_DELETE);
//		click(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		getElementPresent(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.ICON_LOUDSPEAKER);
//		click(loginPageUI.ICON_HISTORY);
//		checkElementNotPresent(chatReply1_1,3000);
//		checkElementNotPresent(USER_ASK1_1,3000);
//		checkElementNotPresent(chatReply2_2,3000);
//		checkElementNotPresent(USER_ASK2_2,3000);
//	}
//	@Test
//	public void TS_015_Logic_ChatAI_Chat_History_Delete_Choose_All_Nheu_Hon_1_Lich_Su_ManHome() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		click(loginPageUI.BTN_LEFT);
//		clickElementIfDisplay(loginPageUI.BTN_LEFT);
//		clickElementIfDisplay(loginPageUI.BTN_DISMISS);
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hi");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK2 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK2);
//		getElementPresent(chatReply2);
//		click(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1_1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1);
//		getElementPresent(USER_ASK1_1);
//		String chatReply2_2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "2");
//		String USER_ASK2_2 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "2");
//		getElementPresent(chatReply2_2);
//		getElementPresent(USER_ASK2_2);
//		click(loginPageUI.BTN_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		String chatReply1_1_1= parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1_1);
//		getElementPresent(USER_ASK1_1_1);
//		String chatReply2_2_2= parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "2");
//		String USER_ASK2_2_2 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "2");
//		getElementPresent(chatReply2_2_2);
//		getElementPresent(USER_ASK2_2_2);
//		String chosseConversation = parseStringToObject(loginPageUI.OPTION_CHOOSE, "1");
//		getElementPresent(chosseConversation);
//		String chosseConversation2 = parseStringToObject(loginPageUI.OPTION_CHOOSE, "2");
//		getElementPresent(chosseConversation2);
//		getElementPresent(loginPageUI.BUTTON_DELETE);
//		click(loginPageUI.BTN_CHOOSE_ALL);
//		pause(3000);
//		verifyContains("2", getText(loginPageUI.BUTTON_DELETE));
//		click(loginPageUI.BUTTON_DELETE);
//		getElementPresent(loginPageUI.TEXT_CONFIRM_DELETE);
//		getElementPresent(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(loginPageUI.BTN_CANCEL_DELETE);
//		click(loginPageUI.BTN_CANCEL_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		click(loginPageUI.BUTTON_DELETE);
//		click(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		getElementPresent(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.ICON_LOUDSPEAKER);
//		click(loginPageUI.ICON_HISTORY);
//		checkElementNotPresent(chatReply1_1,3000);
//		checkElementNotPresent(USER_ASK1_1,3000);
//		checkElementNotPresent(chatReply2_2,3000);
//		checkElementNotPresent(USER_ASK2_2,3000);
//	}
//	@Test
//	public void TS_016_Logic_ChatAI_Chat_History_Delete_Choose_One_Nheu_Hon_1_Lich_Su() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hello");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK1 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK1);
//		getElementPresent(chatReply1);
//		click(loginPageUI.BTN_LEFT);
//		clickElementIfDisplay(loginPageUI.BTN_LEFT);
//		clickElementIfDisplay(loginPageUI.BTN_DISMISS);
//		sendKeyToElement(loginPageUI.TEXTBOX_ASK_A_QUESTION, "Hi");
//		click(loginPageUI.BTN_SEND);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY, "1");
//		String USER_ASK2 = parseStringToObject(loginPageUI.TEXT_USER_ASK, "1");
//		getElementPresent(USER_ASK2);
//		getElementPresent(chatReply2);
//		click(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		String chatReply1_1 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1);
//		getElementPresent(USER_ASK1_1);
//		String chatReply2_2 = parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "2");
//		String USER_ASK2_2 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "2");
//		getElementPresent(chatReply2_2);
//		getElementPresent(USER_ASK2_2);
//		click(loginPageUI.BTN_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		String chatReply1_1_1= parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "1");
//		String USER_ASK1_1_1 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "1");
//		getElementPresent(chatReply1_1_1);
//		getElementPresent(USER_ASK1_1_1);
//		String chatReply2_2_2= parseStringToObject(loginPageUI.CHAT_AI_RELLY_HISTORY, "2");
//		String USER_ASK2_2_2 = parseStringToObject(loginPageUI.TEXT_USER_ASK_HISTORY, "2");
//		getElementPresent(chatReply2_2_2);
//		getElementPresent(USER_ASK2_2_2);
//		String chosseConversation = parseStringToObject(loginPageUI.OPTION_CHOOSE, "1");
//		getElementPresent(chosseConversation);
//		String chosseConversation2 = parseStringToObject(loginPageUI.OPTION_CHOOSE, "2");
//		getElementPresent(chosseConversation2);
//		getElementPresent(loginPageUI.BUTTON_DELETE);
//		click(chosseConversation2);
//		pause(3000);
//		verifyContains("1", getText(loginPageUI.BUTTON_DELETE));
//		click(loginPageUI.BUTTON_DELETE);
//		getElementPresent(loginPageUI.TEXT_CONFIRM_DELETE);
//		getElementPresent(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(loginPageUI.BTN_CANCEL_DELETE);
//		click(loginPageUI.BTN_CANCEL_DELETE);
//		getElementPresent(loginPageUI.TEXT_DELETE);
//		getElementPresent(loginPageUI.BTN_LEFT);
//		getElementPresent(loginPageUI.BTN_CHOOSE_ALL);
//		click(loginPageUI.BUTTON_DELETE);
//		click(loginPageUI.TEXT_SUBMIT_DELETE);
//		getElementPresent(chatReply1_1);
//		getElementPresent(USER_ASK1_1);
//		checkElementNotPresent(chatReply2_2,3000);
//		checkElementNotPresent(USER_ASK2_2,3000);
//	}
//	@Test
//	public void TS_017_Logic_ChatAI_IMG() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		getElementPresent(loginPageUI.TEXT_CHOOSE_ACTION);
//		getElementPresent(loginPageUI.BTN_OPEN_CAMERA);
//		getElementPresent(loginPageUI.BTN_GO_TO_PHOTO);
//		getElementPresent(loginPageUI.BTN_CANCEL_ACTION);
//	}
//
//	@Test
//	public void TS_018_Logic_ChatAI_IMG_Open_Camera_Retake() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_RETAKE);
//		getElement(loginPageUI.BTN_TAKE_SCREEN);
//	}
//	@Test
//	public void TS_019_Logic_ChatAI_IMG_Open_Camera_Cancel() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_CANCEL_IMG);
//		getElement(loginPageUI.TEXT_CHAT_AI);
//		getElement(loginPageUI.ICON_CHAT_AI);
//		getElement(loginPageUI.BTN_SETTING);
//	}
//	@Test
//	public void TS_020_Logic_ChatAI_IMG_Open_Camera_Done() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.ICON_FLIP);
//		click(loginPageUI.BUTTON_DONE);
//		getElement(loginPageUI.TITLE_CHAT_AI);
//		getElement(loginPageUI.ICON_HISTORY);
//		getElement(loginPageUI.ICON_LOUDSPEAKER);
//		getElement(loginPageUI.TEXT_ENTER_PROMPTS);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.BTN_CLOSE_IMG);
//		getElement(loginPageUI.TXTB_ASK_A_QUESTION);
//		getElement(loginPageUI.BUTTON_SEND);
//	}
//	@Test
//	public void TS_021_Logic_ChatAI_IMG_Open_Camera_Done_Option_Version_Chat_GPT() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.BTN_ROTATE);
//		click(loginPageUI.BUTTON_DONE);
//		click(loginPageUI.TITLE_CHAT_AI);
//		getElement(loginPageUI.TEXT_SELECT_MODEL_GPT);
//		getElement(loginPageUI.CARD_GPT_3_5);
//		getElement(loginPageUI.CARD_GPT_4_0);
//		getElement(loginPageUI.BTN_CONTINUE_TO_CHAT);
//		click(loginPageUI.CARD_GPT_3_5);
//		click(loginPageUI.BTN_CONTINUE_TO_CHAT);
//		getElement(loginPageUI.TITLE_CHAT_AI);
//		getElement(loginPageUI.ICON_HISTORY);
//		getElement(loginPageUI.ICON_LOUDSPEAKER);
//		getElement(loginPageUI.TEXT_ENTER_PROMPTS);
//		click(loginPageUI.TITLE_CHAT_AI);
//		click(loginPageUI.CARD_GPT_4_0);
//		click(loginPageUI.BTN_CONTINUE_TO_CHAT);
//		getElement(loginPageUI.TEXT_GPT_UPGRADE);
//		getElement(loginPageUI.TAB_YEARLY);
//		getElement(loginPageUI.TAB_WEEKLY);
//		click(loginPageUI.BTN_CLOSE_UPGRADE);
//		getElement(loginPageUI.TITLE_CHAT_AI);
//		getElement(loginPageUI.ICON_HISTORY);
//		getElement(loginPageUI.ICON_LOUDSPEAKER);
//		getElement(loginPageUI.TEXT_ENTER_PROMPTS);
//	}
//	@Test
//	public void TS_022_Logic_ChatAI_IMG_Open_Camera_Done_Close() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.BUTTON_DONE);
//		click(loginPageUI.BTN_CLOSE_IMG);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_IMAGE);
//		getElement(loginPageUI.BTN_VOID_CHAT);
//	}
//	@Test
//	public void TS_023_Logic_ChatAI_IMG_Open_Camera_Done_Promots_Mac_Dinh() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.ICON_FLIP);
//		click(loginPageUI.BUTTON_DONE);
//		verifyNotNull(getText(loginPageUI.TXTB_ASK_A_QUESTION));
//		click(loginPageUI.BUTTON_SEND);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.TEXTBOX_ANSWER_USER);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_REGENERATE);
//		getElement(loginPageUI.BTN_COPY);
//		getElement(loginPageUI.BUTTON_SHARE);
//	}
//	@Test
//	public void TS_024_Logic_ChatAI_IMG_Open_Camera_Done_Promots_Mac_Dinh() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.ICON_FLIP);
//		click(loginPageUI.BUTTON_DONE);
//		verifyNotNull(getText(loginPageUI.TXTB_ASK_A_QUESTION));
//		click(loginPageUI.BUTTON_SEND);
//		pause(7000);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.TEXTBOX_ANSWER_USER);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_REGENERATE);
//		getElement(loginPageUI.BTN_COPY);
//		getElement(loginPageUI.BUTTON_SHARE);
//	}
//	@Test
//	public void TS_025_Logic_ChatAI_IMG_Open_Camera_Done_Promots_Nhap_Khoang_Trang() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.ICON_FLIP);
//		click(loginPageUI.BUTTON_DONE);
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION,"          ");
//		click(loginPageUI.BUTTON_SEND);
//		pause(7000);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.ICON_HISTORY);
//		getElement(loginPageUI.ICON_LOUDSPEAKER);
//		getElement(loginPageUI.TEXT_ENTER_PROMPTS);
//		getElement(loginPageUI.BTN_CLOSE_IMG);
//		getElement(loginPageUI.TXTB_ASK_A_QUESTION);
//		getElement(loginPageUI.BUTTON_SEND);
//	}
//	@Test
//	public void TS_026_Logic_ChatAI_IMG_Open_Camera_Done_Promots_Nhap_Nhieu_Hon_300_Ky_Tu() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.ICON_FLIP);
//		click(loginPageUI.BUTTON_DONE);
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION,generateRandomString(400));
//		verifyCompare("300/300", getText(loginPageUI.TEXT_NUMBER_OF_CHARACTERS));
//		click(loginPageUI.BUTTON_SEND);
//		pause(7000);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.TEXTBOX_ANSWER_USER);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_REGENERATE);
//		getElement(loginPageUI.BTN_COPY);
//		getElement(loginPageUI.BUTTON_SHARE);
//	}
//	@Test
//	public void TS_027_Logic_ChatAI_IMG_Open_Camera_Done_Promots_Nhap_Noi_Dung_Hop_Le() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.ICON_FLIP);
//		click(loginPageUI.BUTTON_DONE);
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION,"Hello World");
//		click(loginPageUI.BUTTON_SEND);
//		pause(7000);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.TEXTBOX_ANSWER_USER);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_REGENERATE);
//		getElement(loginPageUI.BTN_COPY);
//		getElement(loginPageUI.BUTTON_SHARE);
//	}
//	@Test
//	public void TS_028_Logic_ChatAI_IMG_Open_Camera_Done_Back() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.BUTTON_DONE);
//		click(loginPageUI.BTN_LEFT);
//		clickElementIfDisplay(loginPageUI.BTN_LEFT);
//		getElement(loginPageUI.TEXT_CHAT_AI);
//		getElement(loginPageUI.BTN_SETTING);
//		getElement(loginPageUI.TEXT_IMAGE_GENERATOR);
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.ICON_IV_BACK);
//		getElement(loginPageUI.TEXT_CHAT_AI);
//		getElement(loginPageUI.BTN_SETTING);
//		getElement(loginPageUI.TEXT_IMAGE_GENERATOR);
//	}
//	@Test
//	public void TS_029_Logic_ChatAI_IMG_Go_To_Photos_Option_Version_Chat_GPT() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_GO_TO_PHOTO);
//		getElement(loginPageUI.LIST_IMG);
//		getElement(loginPageUI.TITLE_CHOOSE_PHOTOS);
//		click(loginPageUI.IMG_1);
//		click(loginPageUI.BUTTON_DONE);
//		click(loginPageUI.TITLE_CHAT_AI);
//		getElement(loginPageUI.TEXT_SELECT_MODEL_GPT);
//		getElement(loginPageUI.CARD_GPT_3_5);
//		getElement(loginPageUI.CARD_GPT_4_0);
//		getElement(loginPageUI.BTN_CONTINUE_TO_CHAT);
//		click(loginPageUI.CARD_GPT_3_5);
//		click(loginPageUI.BTN_CONTINUE_TO_CHAT);
//		getElement(loginPageUI.TITLE_CHAT_AI);
//		getElement(loginPageUI.ICON_HISTORY);
//		getElement(loginPageUI.ICON_LOUDSPEAKER);
//		getElement(loginPageUI.TEXT_ENTER_PROMPTS);
//		click(loginPageUI.TITLE_CHAT_AI);
//		click(loginPageUI.CARD_GPT_4_0);
//		click(loginPageUI.BTN_CONTINUE_TO_CHAT);
//		getElement(loginPageUI.TEXT_GPT_UPGRADE);
//		getElement(loginPageUI.TAB_YEARLY);
//		getElement(loginPageUI.TAB_WEEKLY);
//		click(loginPageUI.BTN_CLOSE_UPGRADE);
//		getElement(loginPageUI.TITLE_CHAT_AI);
//		getElement(loginPageUI.ICON_HISTORY);
//		getElement(loginPageUI.ICON_LOUDSPEAKER);
//		getElement(loginPageUI.TEXT_ENTER_PROMPTS);
//	}
//	@Test
//	public void TS_030_Logic_ChatAI_IMG_Go_To_Photos_Close() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_GO_TO_PHOTO);
//		getElement(loginPageUI.LIST_IMG);
//		getElement(loginPageUI.TITLE_CHOOSE_PHOTOS);
//		click(loginPageUI.IMG_1);
//		click(loginPageUI.ICON_FLIP);
//		click(loginPageUI.BUTTON_DONE);
//		click(loginPageUI.BTN_CLOSE_IMG);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_IMAGE);
//		getElement(loginPageUI.BTN_VOID_CHAT);
//	}
//	@Test
//	public void TS_031_Logic_ChatAI_IMG_Go_To_Photos_Promots_Mac_Dinh() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_GO_TO_PHOTO);
//		getElement(loginPageUI.LIST_IMG);
//		getElement(loginPageUI.TITLE_CHOOSE_PHOTOS);
//		click(loginPageUI.IMG_1);
//		click(loginPageUI.ICON_FLIP);
//		click(loginPageUI.BUTTON_DONE);
//		verifyNotNull(getText(loginPageUI.TXTB_ASK_A_QUESTION));
//		click(loginPageUI.BUTTON_SEND);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.TEXTBOX_ANSWER_USER);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_REGENERATE);
//		getElement(loginPageUI.BTN_COPY);
//		getElement(loginPageUI.BUTTON_SHARE);
//	}
//	@Test
//	public void TS_032_Logic_ChatAI_IMG_Go_To_Photos_Promots_Mac_Dinh() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_GO_TO_PHOTO);
//		getElement(loginPageUI.LIST_IMG);
//		getElement(loginPageUI.TITLE_CHOOSE_PHOTOS);
//		click(loginPageUI.IMG_1);
//		click(loginPageUI.BUTTON_DONE);
//		verifyNotNull(getText(loginPageUI.TXTB_ASK_A_QUESTION));
//		click(loginPageUI.BUTTON_SEND);
//		pause(7000);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.TEXTBOX_ANSWER_USER);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_REGENERATE);
//		getElement(loginPageUI.BTN_COPY);
//		getElement(loginPageUI.BUTTON_SHARE);
//	}
//	@Test
//	public void TS_033_Logic_ChatAI_IMG_Go_To_Photos_Promots_Nhap_Khoang_Trang() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_GO_TO_PHOTO);
//		getElement(loginPageUI.LIST_IMG);
//		getElement(loginPageUI.TITLE_CHOOSE_PHOTOS);
//		click(loginPageUI.IMG_1);
//		click(loginPageUI.BUTTON_DONE);
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION,"          ");
//		click(loginPageUI.BUTTON_SEND);
//		pause(7000);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.ICON_HISTORY);
//		getElement(loginPageUI.ICON_LOUDSPEAKER);
//		getElement(loginPageUI.TEXT_ENTER_PROMPTS);
//		getElement(loginPageUI.BTN_CLOSE_IMG);
//		getElement(loginPageUI.TXTB_ASK_A_QUESTION);
//		getElement(loginPageUI.BUTTON_SEND);
//	}
//	@Test
//	public void TS_034_Logic_ChatAI_IMG_Go_To_Photos_Promots_Nhap_Nhieu_Hon_300_Ky_Tu() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_GO_TO_PHOTO);
//		getElement(loginPageUI.LIST_IMG);
//		getElement(loginPageUI.TITLE_CHOOSE_PHOTOS);
//		click(loginPageUI.IMG_1);
//		click(loginPageUI.BUTTON_DONE);
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION,generateRandomString(400));
//		verifyCompare("300/300", getText(loginPageUI.TEXT_NUMBER_OF_CHARACTERS));
//		click(loginPageUI.BUTTON_SEND);
//		pause(7000);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.TEXTBOX_ANSWER_USER);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_REGENERATE);
//		getElement(loginPageUI.BTN_COPY);
//		getElement(loginPageUI.BUTTON_SHARE);
//	}
//	@Test
//	public void TS_035_Logic_ChatAI_IMG_Go_To_Photos_Promots_Nhap_Noi_Dung_Hop_Le() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_GO_TO_PHOTO);
//		getElement(loginPageUI.LIST_IMG);
//		getElement(loginPageUI.TITLE_CHOOSE_PHOTOS);
//		click(loginPageUI.IMG_1);
//		click(loginPageUI.BUTTON_DONE);
//		sendKeyToElement(loginPageUI.TXTB_ASK_A_QUESTION,"Hello World");
//		click(loginPageUI.BUTTON_SEND);
//		pause(7000);
//		getElement(loginPageUI.IMG_PHOTO_TAKEN);
//		getElement(loginPageUI.TEXTBOX_ANSWER_USER);
//		getElement(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElement(loginPageUI.BTN_REGENERATE);
//		getElement(loginPageUI.BTN_COPY);
//		getElement(loginPageUI.BUTTON_SHARE);
//	}
//	@Test
//	public void TS_036_Logic_ChatAI_IMG_Go_To_Photos_Back() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_GO_TO_PHOTO);
//		getElement(loginPageUI.LIST_IMG);
//		getElement(loginPageUI.TITLE_CHOOSE_PHOTOS);
//		click(loginPageUI.IMG_1);
//		click(loginPageUI.BUTTON_DONE);
//		click(loginPageUI.BTN_LEFT);
//		clickElementIfDisplay(loginPageUI.BTN_LEFT);
//		getElement(loginPageUI.TEXT_CHAT_AI);
//		getElement(loginPageUI.BTN_SETTING);
//		getElement(loginPageUI.TEXT_IMAGE_GENERATOR);
//		click(loginPageUI.BTN_IMAGE);
//		click(loginPageUI.BTN_OPEN_CAMERA);
//		click(loginPageUI.BTN_TAKE_SCREEN);
//		click(loginPageUI.BTN_DONE_TAKE_SCREEN);
//		click(loginPageUI.ICON_IV_BACK);
//		getElement(loginPageUI.TEXT_CHAT_AI);
//		getElement(loginPageUI.BTN_SETTING);
//		getElement(loginPageUI.TEXT_IMAGE_GENERATOR);
//	}
//	@Test
//	public void TS_037_Logic_ChatAI_IMG_Cancel() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_IMAGE);
//		getElementPresent(loginPageUI.TEXT_CHOOSE_ACTION);
//		getElementPresent(loginPageUI.BTN_OPEN_CAMERA);
//		getElementPresent(loginPageUI.BTN_GO_TO_PHOTO);
//		click(loginPageUI.BTN_CANCEL_ACTION);
//		getElementPresent(loginPageUI.TEXT_CHAT_AI);
//		getElementPresent(loginPageUI.BTN_SETTING);
//		getElementPresent(loginPageUI.TEXT_IMAGE_GENERATOR);
//	}
//	@Test
//	public void TS_038_Logic_ChatAI_Mic() {
//		info("Open app");
//		managementAccount.openHomePageWithLanguage("English");
//		click(loginPageUI.BTN_VOID_CHAT);
//		getElementPresent(loginPageUI.TITLE_VOID_CHAT);
//		getElementPresent(loginPageUI.IMG_MIC);
//		getElementPresent(loginPageUI.TEXT_TRY_TO_SAY_SOMETHING);
//		click(loginPageUI.TEXT_LANGUAGE);
//		getElementPresent(loginPageUI.TITLE_CHANGE_LANGUAGE);
//		getElementPresent(loginPageUI.LIST_LANGUAGE_MIC);
//		getElementPresent(loginPageUI.LIST_FLAG_MIC);
//		click(loginPageUI.BUTTON_DONE);
//		getListElement(loginPageUI.LIST_LANGUAGE_MIC);
//		clickListElement(loginPageUI.LIST_LANGUAGE_MIC, 3);
//		getElementPresent(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElementPresent(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.ICON_LOUDSPEAKER);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		getElementPresent(loginPageUI.TXTB_ASK_A_QUESTION);
//		click(loginPageUI.BTN_VOID_CHAT);
//		click(loginPageUI.TEXT_LANGUAGE);
//		getListElement(loginPageUI.LIST_LANGUAGE_MIC);
//		clickListElement(loginPageUI.LIST_LANGUAGE_MIC, 5);
//		getElementPresent(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElementPresent(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.ICON_LOUDSPEAKER);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		getElementPresent(loginPageUI.TXTB_ASK_A_QUESTION);
//		click(loginPageUI.BTN_VOID_CHAT);
//		click(loginPageUI.TEXT_LANGUAGE);
//		getListElement(loginPageUI.LIST_LANGUAGE_MIC);
//		clickListElement(loginPageUI.LIST_LANGUAGE_MIC, 4);
//		getElementPresent(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElementPresent(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.ICON_LOUDSPEAKER);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		getElementPresent(loginPageUI.TXTB_ASK_A_QUESTION);
//		click(loginPageUI.BTN_VOID_CHAT);
//		click(loginPageUI.TEXT_LANGUAGE);
//		getListElement(loginPageUI.LIST_LANGUAGE_MIC);
//		clickListElement(loginPageUI.LIST_LANGUAGE_MIC, 7);
//		getElementPresent(loginPageUI.TEXT_CONTENT_ANSWER);
//		getElementPresent(loginPageUI.ICON_HISTORY);
//		getElementPresent(loginPageUI.ICON_LOUDSPEAKER);
//		getElementPresent(loginPageUI.TITLE_CHAT_AI);
//		getElementPresent(loginPageUI.TXTB_ASK_A_QUESTION);
//	}
//
//	private baseTest managementAccount;
//}
