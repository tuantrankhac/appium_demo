package common;


import static common.TestLogger.info;
import static org.testng.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import login.pageObject.loginPO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;


public class commonBase {
	public AppiumDriver driver;
	public WebDriver driverWeb;
	protected int DEFAULT_TIMEOUT = 40000;
	protected int WAIT_INTERVAL = 500;
	public int loopCount = 0;
	public final int ACTION_REPEAT = 5;
	public Actions action;
	public SoftAssert softAssert;



	public void getMeasuresCreenTransitionTime(String locator, String value1, String value2) {
		long startTime = System.currentTimeMillis();
		getElementPresent(locator);
		long endtTime = System.currentTimeMillis();
		long transitionTime = endtTime - startTime;

		info("Thoi gian chuyen tu " + value1 + " đến " + value2 + " là : " + transitionTime + " milliseconds");
	}

	public AppiumDriver initDriverTestAndroid(String... params) {

		String device = System.getProperty("Device");
		String platForm = System.getProperty("Platform");
		String version = System.getProperty("PlatformVersion");
		String pack = System.getProperty("Package");
		String activity = System.getProperty("Activity");
		String port = System.getProperty("Port");

		AppiumDriver dr = null;

		String dev = params.length > 0 ? params[0] : device;
		String pla = params.length > 1 ? params[1] : platForm;
		String ver = params.length > 2 ? params[2] : version;
		String PACKAGE = params.length > 3 ? params[3] : pack;
		String ACTIVITY = params.length > 4 ? params[4] : activity;
		String PORT = params.length > 5 ? params[5] : port;

		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("resetKeyboard", true);
		caps.setCapability("settings[enforceXPath1]", true);
		caps.setCapability("newCommandTimeout", 90);
		caps.setCapability("ignoreUnimportantViews", false);
		caps.setCapability("skipDeviceInitialization", true);
		caps.setCapability("settings[waitForldleTimeout]", 100);
		caps.setCapability("appium:deviceName", dev);
		caps.setCapability("appium:port", PORT);
		caps.setCapability("appium:automationName", "uiautomator2");
		caps.setCapability("appium:platformVersion", ver);
		if (pla.equalsIgnoreCase("Android")) {
			caps.setCapability("appium:appPackage", PACKAGE);
			caps.setCapability("appium:appActivity", ACTIVITY);
		}
		try {
			dr = new AndroidDriver(new URL("http://localhost:4723/"), caps);
		} catch (MalformedURLException e) {
			assertFalse(true, "Loi khoi tao driver");
		}
		return dr;
	}

	/*
	 * tao driver param[0]: app can test param[1]: name device/simulator chay test
	 * param[2]: platform chay test param[3]: version platform chay test param[4]:
	 * package khoi tao param[5]: activity khoi tao
	 * 
	 */
	public AppiumDriver initDriverTest(String... params) {
		String device = System.getProperty("Device");
		String platForm = System.getProperty("PlatForm");
		String app = System.getProperty("App");
		String version = System.getProperty("PlatformVersion");
		String pack = System.getProperty("Package");
		String activity = System.getProperty("Activity");
		String uuid = System.getProperty("UUID");
		String port = System.getProperty("Port");

		AppiumDriver dr = null;
		String ap = params.length > 0 ? getAbsoluteFilePath(File.separator + "app" + File.separator + params[0])
				: getAbsoluteFilePath(File.separator + "app" + File.separator + app);
		String dev = params.length > 1 ? params[1] : device;
		String pla = params.length > 2 ? params[2] : platForm;
		String ver = params.length > 3 ? params[3] : version;
		String PACKAGE = params.length > 4 ? params[4] : pack;
		String ACTIVITY = params.length > 5 ? params[5] : activity;

		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("appium:app", ap);
		if (dev != null && dev != "") {
			caps.setCapability("appium:deviceName", dev);
		}
		caps.setCapability("platformName", pla);
		caps.setCapability("resetKeyboard", true);
		caps.setCapability("settings[enforceXPath1]", true);
		caps.setCapability("newCommandTimeout", 60);
		caps.setCapability("ignoreUnimportantViews", false);
		caps.setCapability("skipDeviceInitialization", true);
		caps.setCapability("settings[waitForldleTimeout]", 100);
		caps.setCapability("settings[setWebContentsDebuggingEnabled]", true);
		caps.setCapability("preLaunchApp", true);
		caps.setCapability("nativeWebScreenshot", true);
		caps.setCapability("implicitWaitTimeout", 5000);
		caps.setCapability("disableWindowAnimation", true);
		caps.setCapability("appium:enableMultiWindows", true);
		if (pla.equalsIgnoreCase("Android")) {
			caps.setCapability("automationName", "uiautomator2");
			caps.setCapability("appium:udid", dev);
			if (ver != null && ver != "") {
				int v;
				if (ver.contains(".")) {
					v = Integer.parseInt(ver.substring(0, ver.indexOf(".")));
				} else {
					v = Integer.parseInt(ver);
				}
				if (v >= 10) {
					caps.setCapability("autoGrantPermissions", true);
				}
			}
			if (PACKAGE != null && ACTIVITY != null) {
				caps.setCapability("appPackage", PACKAGE);
				caps.setCapability("appActivity", ACTIVITY);
			}
			try {
				dr = new AndroidDriver(new URL("http://localhost:4723/"), caps);
			} catch (MalformedURLException e) {
				assertFalse(true, "Loi khoi tao driver");
			}
		} else {
			if (uuid != null) {
				caps.setCapability("appium:udid", uuid);
			}
			caps.setCapability("useNewWDA", false);
			if (port != null && port != "") {
				caps.setCapability("wdaLocalPort", port);
			}
			caps.setCapability("automationName", "XCUITest");
			try {
				dr = new IOSDriver(new URL("http://localhost:4723/"), caps);
			} catch (Exception e) {
				assertFalse(true, "Loi khoi tao driver");
			}
		}
		softAssert = new SoftAssert();
		return dr;
	}

	public void pause(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String generateRandomString(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public String getClipboardContent() {
        try {
            Process process = Runtime.getRuntime().exec("adb shell service call clipboard 2 s16 com.android.server.clipboard.IClipboard$Stub$Proxy");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


	public void scrollDown(int numberOfScrolls) {
		// Lấy kích thước của màn hình
		int screenWidth = driver.manage().window().getSize().width;
		int screenHeight = driver.manage().window().getSize().height;

		// Tính toán các điểm bắt đầu và kết thúc cho thao tác cuộn
		int startX = screenWidth / 2; // Điểm giữa màn hình theo chiều ngang
		int startY = (int) (screenHeight * 0.5); // 50% chiều cao màn hình từ trên xuống
		int endY = (int) (screenHeight * 0.2); // 20% chiều cao màn hình từ trên xuống

		// Tạo một PointerInput để mô phỏng thao tác chạm
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		for (int i = 0; i < numberOfScrolls; i++) {
			// Tạo một chuỗi hành động (Sequence) để mô phỏng thao tác cuộn
			Sequence scrollSequence = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger
							.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, endY))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			// Thực thi chuỗi hành động
			driver.perform(Arrays.asList(scrollSequence));
		}
	}

	public WebElement waitForElementNotPresent(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1] : 1;
		int notDisplayE = opParams.length > 2 ? opParams[2] : 0;

		for (int tick = 0; tick < timeout / WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2) {
				elem = getElement(locator);
			} else {
				elem = getDisplayedElement(locator);
			}
			if (elem == null) {
				return null;
			}
			pause(WAIT_INTERVAL);
		}
		if (isAssert == 1) {
			assert false : ("Timeout after " + timeout + "ms waiting for element not present: " + locator);
		}
		return elem;
	}

	public void checkElementNotPresent(Object locator, int... opParams) {
		WebElement e = null;
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;

		for (int tick = 1; tick <= timeout / WAIT_INTERVAL; tick++) {
			try {
				e = driver.findElement(by);
				if (e != null && tick == timeout / WAIT_INTERVAL) {
					Assert.fail("Element " + locator + " is present");
				}
				info("Wait element not present with time: " + tick + 1);
			} catch (NoSuchElementException ex) {
				pause(WAIT_INTERVAL);
				if (tick == timeout / WAIT_INTERVAL) {
					return;
				}
			}
		}
	}

	public WebElement getElement(Object locator, WebDriver... dr) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		WebDriver driver_web = dr.length > 0 ? dr[0] : driver;
		WebElement elem = null;
		try {
			elem = driver_web.findElement(by);
		} catch (NoSuchElementException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			getElement(locator);
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			getElement(locator);
		} catch (WebDriverException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			getElement(locator);
		}

		return elem;
	}

	public String getSizeOfListElement(Object locator) {
		return String.valueOf(getListElement(locator).size());
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getListElement(Object locator) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		List<WebElement> elementOptions;
		try {
			elementOptions = driver.findElements(by);
			return elementOptions;
		} catch (NoSuchElementException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			getListElement(locator);
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			getListElement(locator);
		} finally {
			loopCount = 0;
		}
		return null;

	}

	/**
	 * lay cac gia tri thuoc tinh cua 1 mang doi tuong element
	 * 
	 * @param locator
	 * @param attribute
	 * @return
	 */
	public String[] getAttOfListElement(Object locator, String attribute) {
		String[] att = new String[20];
		List<WebElement> list;
		list = getListElement(locator);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				att[i] = list.get(i).getAttribute(attribute);
			}
		}
		return att;
	}

	public void checkCycling(Exception e, int loopCountAllowed) {
		info("Co exception xay ra: " + e.getClass().getName());
		if (loopCount > loopCountAllowed) {
			Assert.fail("Qua thoi gian nhung khong thay hoac thay doi tuong " + e.getMessage());
		}
		info("Lap lai lan thu " + loopCount);
		loopCount++;
	}

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}



	public WebElement waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public boolean isElementVisible(String xpathLocator) {
		try {
			driver.findElement(getByXpath(xpathLocator));
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}


	public WebElement getElementPresent(String xpathLocator) {
		try {
			WebElement element = waitForElementVisible(driver, xpathLocator);
			return element;
		} catch (Exception e) {
			System.out.println("Timeout while waiting for element: " + e.getMessage());
			return null;
		}
    }


//	public WebElement getElementPresent(Object locator, int... opParams) {
//		WebElement elem = null;
//		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
//		int isAssert = opParams.length > 1 ? opParams[1] : 1;
//		int notDisplayE = opParams.length > 2 ? opParams[2] : 0;
//		for (int tick = 0; tick < timeout / WAIT_INTERVAL; tick++) {
//			if (notDisplayE == 2) {
//				elem = getElement(locator);
//			} else {
//				elem = getDisplayedElement(locator);
//			}
//			if (null != elem)
//				return elem;
//			pause(WAIT_INTERVAL);
//		}
//		if (isAssert == 1) {
//			String date = getDateTime("yyyyMMddHHmmss");
//			info("date");
//			captureScreen(driver, "Loi_" + date + ".jpg");
//			assert false : ("Qua thoi gian " + timeout + "ma khong tim thay doi tuong " + locator);
//			quitDriver(driver);
//		}
//		return null;
//	}

	public WebElement getElementPresentNoAssert(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		for (int tick = 0; tick < timeout / WAIT_INTERVAL; tick++) {
			try {
				elem = driver.findElement(by);
				if (null != elem)
					return elem;
				pause(WAIT_INTERVAL);
				info("Lap lai lan thu " + tick + 1);
			} catch (NoSuchElementException ex) {
				if (tick == timeout / WAIT_INTERVAL) {
					return null;
				}
			} catch (WebDriverException e) {
				if (tick == timeout / WAIT_INTERVAL) {
					return null;
				}
			} catch (IllegalStateException e) {
				if (tick == timeout / WAIT_INTERVAL) {
					return null;
				}
			}
		}
		return elem;
	}

	/**
	 * get a display element in web page
	 * 
	 * @param locator @ return
	 */
	public WebElement getDisplayedElement(Object locator, WebDriver... dr) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		WebDriver driver_web = dr.length > 0 ? dr[0] : driver;
		WebElement e = null;
		try {
			if (by != null)
				e = driver_web.findElement(by);
			if (e != null) {
				if (isDisplay(by))
					return e;
			}
		} catch (NoSuchElementException ex) {
			checkCycling(ex, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		} catch (WebDriverException ex) {
			checkCycling(ex, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		} finally {
			loopCount = 0;
		}
		return null;
	}

	/**
	 * checking an element is displayed in web page
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isDisplay(Object locator) {
		boolean bool = false;
		WebElement e = getElement(locator);
		try {
			if (e != null)
				bool = e.isDisplayed();
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			isDisplay(locator);
		} finally {
			loopCount = 0;
		}
		return bool;
	}

	/**
	 * @param driver
	 */
	public void captureScreen(AppiumDriver driver, String fileName) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String dir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(dir + "\\capture_screen\\" + fileName + ".jpg"));
			info("Capture man hinh to file " + fileName);
		} catch (Exception e) {
			info("Khong capture duoc man hinh");
		}
	}

	/**
	 * get datetime
	 * 
	 * @param format
	 */
	public String getDateTime(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String dateTime = dateFormat.format(cal.getTime());
		info("time at moment is " + dateTime);
		return dateTime;
	}

	public By convertStringToBy(String locator, String... option) {
		By by = null;
		String loc = locator.toString();
		if (option.length > 0) {
			loc = loc.replaceAll("&option", option[0]);
		}
		if (loc.startsWith("//")) {
			by = By.xpath(loc);
		} else {
			by = AppiumBy.iOSClassChain(loc);
		}
		return by;
	}

	/**
	 * click on an element
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void click(String locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		String opt = (String) (opParams.length > 1 ? opParams[1] : "");
		if (opt != "") {
			locator = String.valueOf(convertStringToBy(locator, opt));
		}
		try {
			WebElement element = getElementPresent(locator);
			if (element != null) {
				element.click();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} catch (NoSuchElementException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} catch (Exception e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} finally {
			loopCount = 0;
		}
	}




	/**
	 * Click on an element of list element
	 * 
	 * @param locator
	 * @param index
	 */
	public void clickListElement(Object locator, int index) {
		List<WebElement> list = getListElement(locator);
		if (list.size() > 0) {
			info("So luong element tim duoc: " + list.size());
			list.get(index).click();
		}
	}


	public void sendKeyToElement(String locator, String keys) {
		try {
			WebElement element = getElementPresent(locator);
			element.clear(); // Clear existing content
			element.sendKeys(keys);
			System.out.println("Successfully sent keys: " + keys + " to element: " + locator.toString());
		} catch (Exception e) {
			System.out.println("Failed to send keys: " + keys + " to element: " + locator.toString());
			e.printStackTrace();
		}
	}


	/**
	 * input data to element
	 *
	 * @param locator
	 * @param value
	 * @param validate
	 */
	public void type(String locator, String value, boolean validate, boolean... clear) {
		boolean clean = clear.length > 0 ? clear[0] : true;
		try {
			for (int loop = 1;; loop++) {
				if (loop >= ACTION_REPEAT) {
					Assert.fail("Qua thoi gian khi input du lieu: " + value + " vao doi tuong " + locator);
				}
				WebElement element = getElementPresent(locator);
				if (element != null) {
					if (clean)
						element.clear();
					element.click();
					element.sendKeys(value);
					if (!validate || value.equals(getValue(locator))) {
						break;
					}
				}
				info("Lap lai tac dong input text lan thu " + loop);
				pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			type(locator, value, validate);
		} catch (NoSuchElementException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			type(locator, value, validate);
		} catch (Exception e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			type(locator, value, validate);
		} finally {
			loopCount = 0;
		}
		driver.navigate().back();
	}

	public void typeHinput(String locator, String text, boolean validate) {
		if (text != null) {
			if (getElementPresent(locator) != null) {
				try {
					WebElement e = getElementPresent(locator);
					e.click();
					e.sendKeys(Keys.CONTROL + "a");
					e.sendKeys(text);
					for (int i = 0; i < 5; i++) {
						String am = getValue(locator, 2);
						if (am != null) {
							if (am.equalsIgnoreCase(text)) {
								break;
							} else {
								e.sendKeys(Keys.CONTROL + "a");
								e.sendKeys(text);
							}
						}
					}
				} catch (StaleElementReferenceException ex) {
					typeHinput(locator, text, validate);
				}
			}
		}
	}

	/**
	 * get value of element in web page
	 *
	 * @param locator
	 * @return
	 */
	public String getValue(String locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		try {
			String platform = driver.getCapabilities().getCapability("platformName").toString();
			String plat = platform != null ? platform : "android";
			if (plat.equalsIgnoreCase("android")) {
				return getElementPresent(locator).getAttribute("text");
			} else {
				return getElementPresent(locator).getAttribute("value");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get text of element
	 * 
	 * @param locator
	 * @return
	 */
	public String getText(String locator) {
		WebElement element = null;
		try {
			element = getElementPresent(locator);
			return element.getText();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			return getText(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get absolute path of file
	 * 
	 * @param relativeFilePath
	 * @return
	 */
	public String getAbsoluteFilePath(String relativeFilePath) {
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + relativeFilePath;
		return absolutePath;
	}

	/**
	 * compare 2 string
	 * 
	 * @param s1
	 * @param s2
	 */
	public void verifyCompare(String s1, String s2) {
		if (s1 != "" && s1 != null && s2 != null && s2 != "") {
			Assert.assertFalse(!s1.equalsIgnoreCase(s2), "So sanh khong bang nhau: " + s1 + " va " + s2);
		} else if ((s1 == "" || s1 == null) && (s2 == "" || s2 == null)) {
			info("2 truong du lieu can so sanh deu null");
		} else {
			Assert.fail("Du lieu so sanh co 1 truong bi null");
		}
	}

	/**
	 * 
	 * @param dateBefore
	 * @param dateAfter
	 */
	public void compareDateBeforeDate(String dateBefore, String dateAfter) {
		Boolean compare = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			compare = sdf.parse(dateBefore).before(sdf.parse(dateAfter));
			info("Ket qua so sanh thoi gian: " + compare);
		} catch (Throwable e) {
			info("Loi:" + e);
		}
		Assert.assertFalse(!compare, "Moc thoi gian " + dateAfter + " khong lon hơn " + dateBefore);
	}

	public void verifyContains(String s1, String s2) {
		if (s1 != null && s2 != null) {
			Assert.assertFalse(!s2.contains(s1), "Chuỗi " + s1 + " không nằm trong chuỗi " + s2);
		}
	}

	protected boolean verifyTrue(boolean condition) {
		boolean status = true;
		try {
			Assert.assertTrue(condition);
			info("---------------------- Passed -----------------------");
		} catch (Throwable e) {
			status = false;
			Reporter.getCurrentTestResult().setThrowable(e);
			info("---------------------- Failed -----------------------");
		}
		return status;
	}

	public void verifyNotContains(String s1, String s2) {
		if (s1 != null && s2 != null) {
			Assert.assertFalse(s2.contains(s1), "Fail do chuoi " + s2 + "  van chua chuoi " + s1);
		}
	}

	/**
	 * scroll to text in screen
	 * 
	 * @param text
	 */
	public void scrollToText(String text) {
		try {
			driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
					+ ".resourceId(\"com.android.settings:id/content\")).scrollIntoView(" + "new UiSelector().text("
					+ text + "));"));
		} catch (NoSuchElementException e) {
			checkCycling(e, 10000 / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			scrollToText(text);
		} finally {
			loopCount = 0;
		}
	}

	public void typeNotClick(String locator, String value, boolean validate, boolean... clear) {
		boolean clean = clear.length > 0 ? clear[0] : true;
		try {
			for (int loop = 1;; loop++) {
				if (loop >= ACTION_REPEAT) {
					Assert.fail("Qua thoi gian khi input du lieu: " + value + " vao doi tuong " + locator);
				}
				WebElement element = getElementPresent(locator);
				if (element != null) {
					if (clean)
						element.clear();
					element.sendKeys(value);
					if (!validate || value.equals(getValue(locator))) {
						break;
					}
				}
				info("Lap lai tac dong input text lan thu " + loop);
				pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			typeNotClick(locator, value, validate);
		} catch (NoSuchElementException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			typeNotClick(locator, value, validate);
		} catch (Exception e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			typeNotClick(locator, value, validate);
		} finally {
			loopCount = 0;
		}

	}

	/**
	 * parse to object from a xpath contains option
	 * 
	 * @param xpathOption
	 * @param option
	 * @return
	 */
	public String parseStringToObject(String xpathOption, String option) {
		return xpathOption.replaceAll("&option", option);
	}

	public String trimCharactor(String input, String trim) {
		info("Xau can xu ly trim: " + input);
		if (input != "" && input != null && trim != "") {
			if (trim == ".") {
				return input.replaceAll("\\.", "");
			} else {
				return input.replaceAll(trim, "");
			}
		} else
			return "";
	}

	/**
	 * get first day of month of next month
	 * 
	 * @param addMonth
	 * @return
	 */
	public String getFirstDayOfMonth(int addMonth) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		info("Current date: " + df.format(cal.getTime()));
		cal.add(Calendar.MONTH, addMonth);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return df.format(cal.getTime());
	}

	public String getExpireDate(int addMonth, int index) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		info("Current date: " + df.format(cal.getTime()));
		cal.add(Calendar.MONTH, addMonth);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		String dateStr = df.format(cal.getTime());

		String[] expireDate = null;
		expireDate = dateStr.split("/");
		return expireDate[index];

	}

	/**
	 * 
	 * @param tbSearch
	 * @param textSearch
	 * @param xpath
	 */
	public void selectOptionFromComBoxSearch(String tbSearch, String textSearch, String xpath) {
		type(tbSearch, textSearch, false, false);
		pause(500);
		click(xpath.replaceAll("&option", textSearch));
		pause(1000);
	}

	public void selectOptionFromCombobox(String xpath, String option) {
		if (option != null) {
			String locator = xpath.replaceAll("&option", option);
			click(locator);
		}
	}

	/**
	 * Open page at not loaded status, as clear cache
	 * 
	 * @param pageUrl
	 * @param driver
	 */
	public void openPageNotLoad(String pageUrl, WebDriver driver) {
		if (pageUrl != null) {
			driver.get(pageUrl);
			pause(2000);
		}
	}

	/**
	 * accept unexpected alert
	 */
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();

		} catch (NoAlertPresentException ex) {
			info("No Alert present");
			;
		}
	}

	/**
	 * 
	 * @param urlText
	 * @param data
	 * @param column
	 * @return
	 */
	public static String[] callWS(String urlText, String data, String... column) {
		String output = "";
		String[] da = new String[100];
		String urlT = "";
		if (urlText != "" && !urlText.contains("http")) {
			urlT = System.getProperty("serviceLink") + urlText;
		} else {
			urlT = urlText;
		}
		try {
			URL url = new URL(urlT);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "text/xml");
			connection.setRequestProperty("Accept", "text/xml");
			info("connection: " + connection);
			OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
			osw.write(data);
			osw.flush();
			osw.close();
			InputStream in = connection.getInputStream();
			output = read(in);
			info("output cua WS: " + output);
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(output));
			Document doc = builder.parse(src);
			if (column.length > 0) {
				for (int i = 0; i < column.length; i++) {
					if (doc.getElementsByTagName(column[i]).item(0).getTextContent() != null) {
						da[i] = doc.getElementsByTagName(column[i]).item(0).getTextContent();
						info("Gia tri trong tag " + column[i] + "la: " + da[i]);
					}
				}
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
			assertFalse(true, "Co loi khi thuc hien WS");

		}
		return da;
	}

	/**
	 * 
	 * @param urlText
	 * @param data
	 * @param column
	 * @return
	 */
	public static String[] callWSNoAssert(String urlText, String data, String... column) {
		String output = "";
		String[] da = new String[100];
		try {
			URL url = new URL(urlText);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "text/xml");
			connection.setRequestProperty("Accept", "text/xml");
			info("connection: " + connection);
			OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
			osw.write(data);
			osw.flush();
			osw.close();
			InputStream in = connection.getInputStream();
			output = read(in);
			info("output cua WS: " + output);
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(output));
			Document doc = builder.parse(src);
			if (column.length > 0) {
				for (int i = 0; i < column.length; i++) {
					if (doc.getElementsByTagName(column[i]).item(0).getTextContent() != null) {
						da[i] = doc.getElementsByTagName(column[i]).item(0).getTextContent();
						info("Gia tri trong tag " + column[i] + "la: " + da[i]);
					}
				}
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
			info("Co loi khi thuc hien WS");
		}
		return da;
	}

	public boolean objectIsNull(String obj) {
		if (getElementPresent(obj) != null)
			return false;
		return true;
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static String read(InputStream input) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
			return buffer.lines().collect(Collectors.joining("\n"));
		}
	}

	/**
	 * get message from Toast object
	 * 
	 * @return
	 */
	public String getMessageToast() {
		WebElement toast = getElementPresent("//android.widget.Toast[1]");
		String message = toast.getAttribute("name");
		info("message Toast is: " + message);
		return message;
	}

	public void scrollToElementUsingTouchAction(Object element, String... direct) {
		String dir = direct.length > 0 ? direct[0] : "DOWN";
		if (dir.equalsIgnoreCase("DOWN")) {
			scrollToElementWithTouchAction(element, "V", (double) 4 / 5, (double) 1 / 8);
		} else {
			scrollToElementWithTouchAction(element, "V", (double) 1 / 8, (double) 4 / 5);
		}
	}

	public void scrollOnUIPickerView(Object element, int... soLanScroll) {
		scrollToElementWithTouchAction(element, "V", 0.9, 0.75, soLanScroll);
	}

	/**
	 * Dung cho scroll tren UI Picker tung dong
	 * 
	 * @param element
	 * @param soLanScroll
	 */
	public void scrollOnUIPickerViewTab(Object element, int... soLanScroll) {
		scrollToElementWithTouchAction(element, "V", 0.9, 0.85, soLanScroll);
	}

	public void selectValueFromUIPickerWheel(String uiPicker, String value) {
		WebElement pick = getElementPresent(uiPicker);
		pick.sendKeys(value);
	}

	/**
	 * Su dung voi nhung popup hien thi ko biet truoc
	 * 
	 * @param obj
	 * @param opParams
	 */
	public void clickElementIfDisplay(String obj, int... opParams) {
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		if (getElementPresentNoAssert(obj, timeout) != null) {
			click(obj);
		}
	}

	public void verifyNotNull(String s) {
		if (s == null || s == "") {
			Assert.fail("Du lieu  null");
		}
	}

	public void verifyCompareNotEqual(String s1, String s2) {
		if (s1 != "" && s1 != null && s2 != null && s2 != "") {
			Assert.assertTrue(!s1.equalsIgnoreCase(s2),
					"Pass neu 2 truong so sanh khong bang nhau: " + s1 + " va " + s2);
		} else if ((s1 == "" || s1 == null) && (s2 == "" || s2 == null)) {
			info("2 truong du lieu can so sanh deu null");
		} else {
			Assert.fail("Du lieu so sanh co 1 truong bi null");
		}
	}

	/**
	 * (from > to)
	 * 
	 * @param element
	 * @param direct
	 * @param from
	 * @param to
	 * @param soLanScroll
	 */
	public void scrollToElementWithTouchAction(Object element, String direct, double from, double to,
			int... soLanScroll) {
		int soLan = (Integer) (soLanScroll.length > 0 ? soLanScroll[0] : 10);
		int denominator = (Integer) (soLanScroll.length > 1 ? soLanScroll[1] : 2);
		int numerator = (Integer) (soLanScroll.length > 2 ? soLanScroll[2] : 1);
		int startX;
		int endX;
		int topY;
		int bottomY;
		double de = denominator;
		double num = numerator;

		WebElement el = getElementPresentNoAssert(element, 5000);
		if (direct.equalsIgnoreCase("V")) {
			startX = endX = (int) ((driver.manage().window().getSize().getWidth()) * (num / de));
			topY = (int) ((driver.manage().window().getSize().getHeight()) * to);
			bottomY = (int) ((driver.manage().window().getSize().getHeight()) * from);
		} else {
			startX = (int) ((driver.manage().window().getSize().getWidth()) * from);
			endX = (int) ((driver.manage().window().getSize().getWidth()) * to);
			topY = bottomY = (int) ((driver.manage().window().getSize().getHeight()) * (num / de));
		}
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		int i = 0;
		while ((el == null) || (!el.isDisplayed())) {
			i++;
			Sequence scroll = new Sequence(finger, i);
			scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, bottomY));
			scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			scroll.addAction(
					finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, topY));
			scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(List.of(scroll));
			if (i == soLan) {
				break;
			}
			el = getElementPresentNoAssert(element, 5000);
		}
	}

	public String subtractDate(String format, String delta) {
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		int del = Integer.parseInt(delta);
		c.add(Calendar.DATE, -del);
		return formatDateToString(c.getTime(), format);
	}

	public String addDate(String format, String delta) {
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		int del = Integer.parseInt(delta);
		c.add(Calendar.DATE, del);
		return formatDateToString(c.getTime(), format);
	}

	public String addDate(String date, String format, String delta) {
		Date currentDate = formatDate(format, date);
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		int del = Integer.parseInt(delta);
		c.add(Calendar.DATE, del);
		return formatDateToString(c.getTime(), format);
	}

	public String formatDateToString(Date date, String format) {
		DateFormat f = new SimpleDateFormat(format);
		String dat = "";
		try {
			dat = f.format(date);
		} catch (Exception e) {
			info("Exception: " + e);
			info("Can not convert date");
		}
		info("date " + dat);
		return dat;
	}

	/**
	 * @param format
	 * @param date
	 * @return
	 */
	public Date formatDate(String format, String date) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date dat = null;
		try {
			dat = f.parse(date);
		} catch (Exception e) {
			info("Exception: " + e);
			info("Can not convert date");
		}
		return dat;
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public String formatDateToString(String date, String format) {
		DateFormat f = new SimpleDateFormat(format);
		String dat = "";
		try {
			dat = f.format(date);
		} catch (Exception e) {
			info("Exception: " + e);
			info("Can not convert date");
		}
		info("dat" + dat);
		return dat;
	}

	/**
	 *
	 * @param date
	 * @param originalFormat
	 * @param intendedFormat
	 * @return
	 */
	public String formatDateToString(String date, String originalFormat, String intendedFormat) {
		DateFormat oriFormat = new SimpleDateFormat(originalFormat);
		DateFormat intendFormat = new SimpleDateFormat(intendedFormat);
		String dat = "";
		try {
			dat = intendFormat.format(oriFormat.parse(date));
		} catch (Exception e) {
			info("Exception: " + e);
			info("Can not convert date");
		}
		info("dat" + dat);
		return dat;
	}

	/**
	 * @param locator
	 * @param att
	 * @return
	 */
	public String getAttribute(String locator, String att, int... opParams) {
		try {
			return getElementPresent(locator).getAttribute(att);
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * 
	 * @return dinh dang Tháng 1
	 */
	public String getAndFormatMonth(String... delta) {
		String del = delta.length > 0 ? delta[0] : "0";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -Integer.parseInt(del));
		int month = cal.get(Calendar.MONTH) + 1;
		return "Tháng " + month;
	}

	public String[] exeServiceManyTimes(String dataInput, String regex, String urlText, String data, String addParam,
			String... method) {
		String med = method.length > 0 ? method[0] : "SOAP";
		String[] output = new String[10];
		if (dataInput != null && dataInput != "" && regex != null && regex != "") {
			String[] input = dataInput.split(regex);
			if (input.length > 0) {
				for (int i = 0; i < input.length; i++) {
					if (!med.equalsIgnoreCase("SOAP")) {
						output[i] = postRestService(urlText, data.replace("&{param}", input[i]), addParam, med);
						pause(2000);
						info("Ket qua thuc thi ws " + urlText + " cua tham so " + input[i] + " is " + output[i]);
					} else {
						info("===================");
						info(data.replaceAll("&param", input[i]));
						output[i] = callWS(urlText, data.replaceAll("&param", input[i]), addParam)[0];

						pause(2000);
						info("Ket qua thuc thi ws " + urlText + " cua tham so " + input[i] + " is " + output[i]);
					}
				}
			}
		}
		return output;
	}

	/**
	 *
	 * @param urlText
	 * @param data
	 * @param addParam
	 * @param method
	 * @return
	 */
	public static String postRestService(String urlText, String data, String addParam, String... method) {
		String med = method.length > 0 ? method[0] : "POST";
		String output = "";
		String urlT = "";
		if (urlText != "" && !urlText.contains("http")) {
			urlT = System.getProperty("serviceLink") + urlText;
		} else {
			urlT = urlText;
		}
		try {
			URL url = new URL(urlT);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod(med);
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));

			if (addParam != "") {
				String[] param = addParam.split(",");
				if (param.length > 0) {
					for (int i = 0; i < param.length; i++) {
						if (param[i] != "") {
							String[] a = param[i].split(":");
							if (a.length > 0) {
								con.setRequestProperty(a[0], a[1]);
							}
						}
					}
				}
			}
			if (data != "") {
				OutputStream osw = con.getOutputStream();
				osw.write(data.getBytes());
				osw.flush();
				osw.close();
			}
			int code = con.getResponseCode();
			info("Result Execute Services is: " + code + " " + con.getResponseMessage());
			if (code == 200) {
				InputStream in = con.getInputStream();
				output = read(in);
			} else {
				InputStream in = con.getErrorStream();
				output = read(in);
			}
			info("Output of service is: " + output);
		} catch (IOException | RuntimeException e) {
			e.printStackTrace();
		}
		return output;
	}

	public String[] shouldMatchRegex(String pattern, String message) {
		String[] result = new String[10];
		if (message == "" || message == null) {
			Assert.fail("message bi null");
		}
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(message);
		if (matcher.find()) {
			/*
			 * Tra ve chuoi match patten result[0] => toan bo chuoi match patten Cac phan tu
			 * tiep theo la cac gia tri muon lay ra trong patten
			 */
			info("Patten match String");
			for (int i = 0; i < matcher.groupCount() + 1; i++) {
				result[i] = matcher.group(i);
			}
		} else {
			Assert.fail("String, patten does not match");
		}

		return result;
	}

	/* Evaluates the given expression and returns the result in String format. */
	public String evaluate(String expression) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String result = null;
		try {
			result = engine.eval(expression).toString();
			if (result.endsWith(".0")) {
				System.out.println("Ket qua cua bieu thuc " + expression + " la: " + result + " Return phan nguyen");
				for (int i = 0; i < result.length(); i++) {
					if (result.charAt(i) == '.') {
						return result.substring(0, i);
					}
				}
			}
			System.out.println("Ket qua cua bieu thuc " + expression + " la: " + result);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void waitForElementDisappear(Object locator) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		int i = 0;
		while (i < 10) {
			try {
				if (driver.findElement(by).isDisplayed()) {
					pause(1000);
					i++;
				}
			} catch (NoSuchElementException ex) {
				break;
			} catch (StaleElementReferenceException ex) {
				break;
			}
		}
		if (i == 10) {
			Assert.fail("Qua thoi gian doi tuong van display");
		}
	}

	/**
	 * drag drop
	 * 
	 * @param elementStart
	 * @param elementEnd
	 */
	public void moveElementToElement(String elementStart, String elementEnd) {
		WebElement eStart = getElementPresent(elementStart);
		WebElement eEnd = getElementPresent(elementEnd);
		// get location of element start
		if (eStart != null & eEnd != null) {
			Point sourceLocationStart = eStart.getLocation();
			Dimension sourceSizeStart = eStart.getSize();
			int centerXStart = sourceLocationStart.getX() + sourceSizeStart.getWidth() / 2;
			int centerYStart = sourceLocationStart.getY() + sourceSizeStart.getHeight() / 2;
			// get location of element end
			Point sourceLocationEnd = eStart.getLocation();
			Dimension sourceSizeEnd = eStart.getSize();
			int centerXEnd = sourceLocationEnd.getX() + sourceSizeEnd.getWidth() / 2;
			int centerYEnd = sourceLocationEnd.getY() + sourceSizeEnd.getHeight() / 2;

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence dragNDrop = new Sequence(finger, 1);
			dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
					centerXStart, centerYStart));
			dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(),
					centerXEnd, centerYEnd));
			dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(List.of(dragNDrop));
		} else {
			Assert.fail("Element is not exit to drag drop");
		}

	}

	public String convertTimestampToDate(String time, String... format) {
		String fm = format.length > 0 ? format[0] : "YYYY/MM/dd HH:mm";
		long l = Long.parseLong(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat(fm);
		String date = dateFormat.format(l);
		return date;
	}

	public void setSliderValue(String element, int... intToSlideValue) {
		WebElement seekBar = getElementPresent(element);
		int value = intToSlideValue.length > 0 ? intToSlideValue[0]
				: (int) ((driver.manage().window().getSize().getWidth()) / 10);

		int startX = seekBar.getLocation().getX();
		int yAxis = seekBar.getLocation().getY();

		// Set slidebar move to position.
		// this number is calculated based on (offset + 3/4width)
		int moveToXDirectionAt = value + startX;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence move = new Sequence(finger, 1);
		move.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, yAxis));
		move.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		move.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(),
				moveToXDirectionAt, yAxis));
		move.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(move));
	}

	public String getValueByJSONPath(String json, String path) {
		String data = null;
		DocumentContext docCtx = JsonPath.parse(json);
		JsonPath jsonPath = JsonPath.compile(path);
		data = docCtx.read(jsonPath).toString();
		if (data == null || data == "") {
			Assert.fail("JSON path does not match");
		}
		return data;
	};

	public String[] getValueFromRestService(String response, String key) {
		String[] result = new String[10];
		String[] path = null;
		if (response != null && response != "") {
			JSONObject json = new JSONObject(response);
			if (key != "") {
				path = key.split("/");
				if (path.length == 1) {
					Object val = json.get(path[0]);
					result[0] = val != null ? String.valueOf(val) : null;
				} else {
					JSONArray array1 = json.getJSONArray(path[0]);
					if (path.length == 2) {
						for (int i = 0; i < array1.length(); i++) {
							Object val = array1.getJSONObject(i).get(path[1]);
							result[i] = val != null ? val.toString() : null;
						}
					} else if (path.length == 3) {
						JSONArray array2 = array1.getJSONObject(0).getJSONArray(path[1]);
						for (int i = 0; i < array2.length(); i++) {
							Object val = array2.getJSONObject(i).get(path[2]);
							result[i] = val != null ? val.toString() : null;
						}
					}

				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param urlText
	 * @param data
	 * @param column
	 * @return
	 */
	public String[] callWSWithCondition(String condition, String urlText, String data, String... column) {
		String output = "";
		String[] da = new String[100];
		String urlT = "";
		if (urlText != "" && !urlText.contains("http")) {
			urlT = System.getProperty("serviceLink") + urlText;
		} else {
			urlT = urlText;
		}
		if (condition.equalsIgnoreCase("true")) {
			try {
				URL url = new URL(urlT);
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "text/xml");
				connection.setRequestProperty("Accept", "text/xml");
				info("connection: " + connection);
				OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
				osw.write(data);
				osw.flush();
				osw.close();
				InputStream in = connection.getInputStream();
				output = read(in);
				info("output cua WS: " + output);
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource src = new InputSource();
				src.setCharacterStream(new StringReader(output));
				Document doc = builder.parse(src);
				if (column.length > 0) {
					for (int i = 0; i < column.length; i++) {
						if (doc.getElementsByTagName(column[i]).item(0).getTextContent() != null) {
							da[i] = doc.getElementsByTagName(column[i]).item(0).getTextContent();
							info("Gia tri trong tag " + column[i] + "la: " + da[i]);
						}
					}
				}
			} catch (IOException | ParserConfigurationException | SAXException e) {
				e.printStackTrace();
				assertFalse(true, "Co loi khi thuc hien WS");

			}
		}
		return da;
	}

	public String replaceCharacter(String input, String char1, String char2) {
		if (input != "" && input != null) {
			return input.replaceAll(char1, char2);
		} else
			return "";
	}

	public String getMinOfArray(String[] array) {
		Long[] a = sortArray(array);
		if (a.length > 0) {
			info("Min value is" + a[0].toString());
			return a[0].toString();
		} else {
			return "";
		}
	}

	public String getMaxOfArray(String[] array) {
		Long[] a = sortArray(array);
		if (a.length > 0) {
			info("Max value is" + a[a.length - 1].toString());
			return a[a.length - 1].toString();
		} else {
			return "";
		}
	}

	public Long[] sortArray(String[] array) {
		Long[] a = new Long[array.length];
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i] != "") {
				a[i] = Long.parseLong(array[i]);
			}
		}
		Arrays.sort(a);
		info("Sorted Array: " + Arrays.toString(a));
		return a;
	}

	/**
	 * get key having min value from output of SOAP service
	 *
	 */

	public void singleTab(int x, int y) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(tap));
	}

	public void doubleTab(int x, int y) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(new Pause(finger, Duration.ofMillis(100)));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(new Pause(finger, Duration.ofMillis(50)));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(new Pause(finger, Duration.ofMillis(100)));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(tap));
	}

	public void tabElement(Object element) {
		WebElement e = getElement(element, driver);
		if (e != null) {
			Point sourceLocation = e.getLocation();
			Dimension sourceSize = e.getSize();
			int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
			int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;
			singleTab(centerX, centerY);
		}
	}

	public String[] convertStringToArray(String string, String split) {
		String[] a = new String[100];
		if (string != null && string != "") {
			a = string.split(split);
		} else {
			info("Xau ky tu can chuyen sang mang ");
		}
		return a;
	}

	public void checkListByScroll(String[] arr, String xpathOption, int... soLanScroll) {
		for (int i = 0; i < arr.length; i++) {
			String obj = xpathOption.replaceAll("&option", arr[i]);
			scrollToElementWithTouchAction(obj, "V", 0.9, 0.55, soLanScroll);
			getElementPresent(obj);

		}
	}

	public String formatCurrency(String number, String... param) {
		String languageCountry = param.length > 0 ? param[0] : "vi-VN"; // en-US
		String[] p = languageCountry.split("-");
		Locale locale = new Locale(p[0], p[1]);
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		info(formatter.format(Integer.parseInt(number)));
		return formatter.format(Integer.parseInt(number)).replace(" ₫", "");
	}

	public String postRestServiceHttps(String urlText, String data, String addParam, String... method) {
		String med = method.length > 0 ? method[0] : "POST";
		String output = "";
		String urlT = "";
		if (urlText != "" && !urlText.contains("http")) {
			urlT = System.getProperty("serviceLink") + urlText;
		} else {
			urlT = urlText;
		}
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}

				public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			try {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (GeneralSecurityException e) {
			}
			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = (hostname, session) -> true;
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			URL url = new URL(urlT);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod(med);
			con.setRequestProperty("Accept", "application/json");

			if (addParam != "") {
				String[] param = addParam.split(",");
				if (param.length > 0) {
					for (int i = 0; i < param.length; i++) {
						if (param[i] != "") {
							String[] a = param[i].split(":");
							if (a.length > 0) {
								con.setRequestProperty(a[0], a[1]);
							}
						}
					}
				}
			}
			if (data != "") {
				OutputStream osw = con.getOutputStream();
				osw.write(data.getBytes());
				osw.flush();
				osw.close();
			}
			info("Result Execute Services is: " + con.getResponseCode() + " " + con.getResponseMessage());
			InputStream in = con.getInputStream();
			output = read(in);
			info("Output of service is: " + output);
		} catch (IOException | RuntimeException e) {
			e.printStackTrace();
		}
		return output;
	}

	public void setProxy(String ip, String port) {
		System.setProperty("https.proxyHost", ip);
		System.setProperty("https.proxyPort", port);
	}

	public String concatString(String s1, String s2) {
		return s1.concat(s2);
	}

	/**
	 * check field is null = ""
	 * 
	 * @param s
	 */
	public void verifyNull(String s) {
		if (!s.equalsIgnoreCase("")) {
			Assert.fail("Du lieu khong null");
		}
	}

	public static void scrollFromBottomToTop(AppiumDriver driver, int startX, int startY, int endX, int endY,
			int duration) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(
				finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(swipe));
	}

	public void pressKeyCode(String keycode) {
		AndroidKey key = AndroidKey.valueOf(keycode.toUpperCase());
		KeyEvent event = new KeyEvent(key);
		((AndroidDriver) driver).pressKey(event);
	}

	public void quitDriver(AppiumDriver dr) {
		if (dr.toString().contains("null")) {
			System.out.print("All Browser windows are closed ");
		} else {
			dr.quit();
		}
	}


	private long shortTimeout = Constant.SHORT_TIMEOUT;
	private long longTimeout = Constant.LONG_TIMEOUT;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
}
