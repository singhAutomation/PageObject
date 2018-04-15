package bn;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class Test {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "/home/tejas/Desktop/WorkspaceSelenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://toolsqa.com/selenium-webdriver/testng-testcase/");
		TakesScreenshot sc = (TakesScreenshot) driver;
		File scr = sc.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File("/home/tejas/freedom/1/Screenshots/test.png"));
		List<WebElement> element = (ArrayList<WebElement>) driver.findElements(By.tagName("a"));
		for (int i = 0; i < element.size(); i++) {

			WebElement ele = element.get(i);
			String url = ele.getAttribute("href");
			verifyLinkActive(url);

		}

	}

	public static void verifyLinkActive(String linkURL) {
		try {
			URL url = new URL(linkURL);
			HttpsURLConnection I = (HttpsURLConnection) url.openConnection();
			I.setConnectTimeout(3000);
			I.connect();
			if (I.getResponseCode() == 200) {
				System.out.println(linkURL + I.getResponseMessage());
			}
			if (I.getResponseCode() == HttpsURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkURL + I.getResponseMessage() + HttpsURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {
		}

	}
}
