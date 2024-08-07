package com.ninza.hrm.api.genericutility;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class is going to store the reusable methods of selenium WebDriver This
 * Class will fetch the WebDriver Reference veriable from BaseClass
 * 
 * @author Sujoy
 */

public class WebDriverUtility {

	public void getURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void switchParentWindowToChildWindow(WebDriver driver) {
		String title = null;
		String mainId = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String oneWindow : allWindows) {
			if (!oneWindow.contains(mainId)) {
				driver.switchTo().window(oneWindow);
				title = driver.getCurrentUrl();
			}
		}
	}

	public void switchChildToParentWindow(WebDriver driver) {
		// switch child to parent window
		String mainId = driver.getWindowHandle();
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String pWindow : allWindowIds) {
			if (pWindow.equals(mainId)) {
				driver.switchTo().window(mainId);
			}
		}
	}

	public void switchNewBrowserTab(WebDriver driver, String partialURL) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains(partialURL)) {
				break;
			}
		}
	}

	public void swithToTabOnTitle(WebDriver driver, String partialTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String windowId = it.next();
			driver.switchTo().window(windowId);

			String actTitle = driver.getTitle();
			if (actTitle.contains(partialTitle)) {
				break;
			}

		}
	}

	public void swithToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void swithToFrame(WebDriver driver, String nameId) {
		driver.switchTo().frame(nameId);
	}

	public void swithToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToAlertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchToAlertCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void select(WebElement element, String text) {
		Select sl = new Select(element);
		sl.selectByVisibleText(text);
	}

	public void select(WebElement element, int index) {
		Select sl = new Select(element);
		sl.selectByIndex(index);
	}

	public void mouseMoveOnElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void doubleClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

	/**
	 * This method is used to open the WebApplication and it will perform next
	 * operation without loading time.
	 * 
	 * @param driver It is a Reference variable of webDriver
	 * @param url    This variable is used to get the Url of webPage
	 */
	public void openAppWithNavigationMethod(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

	/**
	 * This method is used to open the WebPage and it will perform operation after
	 * loading the page this is a get() By using this method we can get the url of
	 * webPage.
	 * 
	 * @param driver It is a reference variable of webDriver
	 * @param url    this variable is used to get the WebPage
	 */

	public void openAppicationWithGetMethod(WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * This Method used to fetch the url of current WebPage
	 * 
	 * @param driver is a reference variable of WebDriver
	 * @return type is String.
	 */
	public String fetchCurrentUrl(WebDriver driver) {
		String urlFetch = driver.getCurrentUrl();
		return urlFetch;
	}

	/**
	 * This method is used to fetch the title of WebPage
	 * 
	 * @param driver It is a reference variable of WebDriver Method want ID of that
	 *               page from where title we want to fetch
	 * @return type of this method is String
	 */

	public String fetchTitle(WebDriver driver) {
		String title = driver.getTitle();
		return title;
	}

	public void minimiseBrowser(WebDriver driver) {
		driver.manage().window().minimize();
	}

	public void resizeBrowser(WebDriver driver, int width, int height) {
		Dimension d = new Dimension(width, height);
		driver.manage().window().setSize(d);
	}

	public void setBrowserPosition(WebDriver driver, int width, int height) {
		Point p = new Point(width, height);
		driver.manage().window().setPosition(p);
	}

	public void forwordPerform(WebDriver driver) {
		driver.navigate().forward();
	}

	public void backwordPerforn(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshPerform(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void dragAndDropPerform(WebDriver driver, WebElement we1, WebElement we2) {
		Actions act = new Actions(driver);
		act.dragAndDrop(we1, we2).perform();
	}

	public void movetoElementPerform(WebDriver driver, WebElement we) {
		Actions act = new Actions(driver);
		act.moveToElement(we).perform();
	}

	public void doubleClickPerform(WebDriver driver, WebElement we) {
		Actions act = new Actions(driver);
		act.doubleClick(we).perform();
	}

	public void rightClick(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.contextClick(ele).perform();
	}

	public void implicityWait(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public void explictWaitByPresence(WebDriver driver, int time, String xpathAtribute) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathAtribute)));
	}

	public boolean isSelect(WebElement we) {
		return we.isSelected();

	}

	public boolean isEnable(WebElement we) {
		return we.isEnabled();
	}

	public boolean isDisplay(WebElement we) {
		return we.isDisplayed();
	}

	public void takeScreenShot(WebDriver driver, String screenShotPath) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File desc = new File(screenShotPath);
		try {
			Files.copy(src, desc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void scrollBy(WebDriver driver, WebElement we) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("Arguments[0].scrollIntoView", we);
	}

	public void frameIndex(WebDriver driver, int index) {
		driver.switchTo().frame(index);

	}

	public void frameName(WebDriver driver, String name) {
		driver.switchTo().frame(name);

	}

	public void frameWebelement(WebDriver driver, WebElement we) {
		driver.switchTo().frame(we);
	}

	public void alertOk(WebDriver driver) {
		Alert al = driver.switchTo().alert();
		al.accept();
	}

	public void alertCancel(WebDriver driver) {
		Alert al = driver.switchTo().alert();
		al.dismiss();
	}

	public String alertGetText(WebDriver driver) {
		Alert al = driver.switchTo().alert();
		return al.getText();
	}

	public void alertWrite(WebDriver driver, String write) {
		Alert al = driver.switchTo().alert();
		al.sendKeys(write);
	}

	public void handelingTabs(WebDriver driver, String title) {

		Set<String> allId = driver.getWindowHandles();
		for (String s : allId) {
			String currentTitle = driver.switchTo().window(s).getTitle();
			if (currentTitle.contains(title))
				;
			{
				break;
			}
		}
	}

	public void dropDownByIndex(WebElement we, int i) {
		Select s = new Select(we);
		s.selectByIndex(i);
	}

	public void dropDownByValue(WebElement we, String value) {
		Select s = new Select(we);
		s.selectByValue(value);
	}

	public void dropDownByVisibleText(WebElement we, String str) {
		Select s = new Select(we);
		s.selectByVisibleText(str);
	}

}
