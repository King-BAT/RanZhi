package testCase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.ReadProperties;

public class DlxRanZhiTest {
	WebDriver driver;

	public void login() throws InterruptedException {
		WebElement usename = driver.findElement(By.name("account"));
		Thread.sleep(2000);
		usename.click();
		usename.sendKeys("admin");
		WebElement password = driver.findElement(By.name("password"));
		Thread.sleep(2000);
		password.click();
		password.sendKeys("admin");
		Thread.sleep(1000);
		WebElement denglu = driver.findElement(By.id("submit"));
		Thread.sleep(1000);
		denglu.click();
	}

	@BeforeClass
	public void doBeforeClass() throws IOException, InterruptedException {
		String baseUrl = ReadProperties.getPropertyValue("dlxbase_url");
		System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "E:\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		this.login();
	}

	@Test
	public void ceshi1() throws Exception {
		Thread.sleep(2000);
		WebElement desk = driver.findElement(By.id("showDesk"));
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			desk.click();
		}

	}

	@Test
	public void ceshi2() throws InterruptedException {

		Thread.sleep(1000);
		WebElement shuaxin = driver.findElement(By.className("icon-repeat"));
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			shuaxin.click();
		}
	}

	@Test
	public void qianTui() throws InterruptedException {
		Thread.sleep(1000);
		WebElement qiantui = driver.findElement(By.cssSelector("a[class='sign signout'][href='javascript:void(0)']"));
		Thread.sleep(1000);
		qiantui.click();
		Thread.sleep(3000);
		this.login();

	}

	@Test
	public void ceshi4() throws InterruptedException {
		Thread.sleep(1000);
		WebElement dongtai = driver.findElement(By.cssSelector("a[href='/ranzhi/www/sys/index.php?m=my&f=dynamic']"));
		Thread.sleep(1000);
		dongtai.click();
		Thread.sleep(1000);
		WebElement desk = driver.findElement(By.id("showDesk"));
		Thread.sleep(1000);
		desk.click();
	}

	@Test
	public void ceshi5() throws InterruptedException {
		Thread.sleep(1000);
		WebElement lianxi = driver.findElement(By.cssSelector("a[href='/ranzhi/www/sys/index.php?m=my&f=contact']"));
		Thread.sleep(1000);
		lianxi.click();
		Thread.sleep(3000);
		WebElement close = driver.findElement(By.cssSelector("button[class='close-win']"));
		Thread.sleep(1000);
		close.click();

	}

	@Test
	public void ceshi6() throws InterruptedException {
		Thread.sleep(1000);
		WebElement lianxi = driver.findElement(By.cssSelector("a[href='/ranzhi/www/sys/index.php?m=my&f=contact']"));
		Thread.sleep(1000);
		lianxi.click();
		Thread.sleep(3000);
		WebElement min = driver.findElement(By.cssSelector("button[class='min-win']"));
		Thread.sleep(1000);
		min.click();
	}

	@Test
	public void ceshi7() throws InterruptedException {

		Thread.sleep(1000);
		WebElement daiban = driver.findElement(By.cssSelector("a[href='/ranzhi/www/sys/index.php?m=todo&f=calendar']"));
		Thread.sleep(1000);
		daiban.click();

	}

	@Test
	public void ceshi8() throws InterruptedException {
		Thread.sleep(5000);
		WebElement zuzhi = driver.findElement(By.xpath("//a[contains(.,'组织')]"));
		Thread.sleep(2000);
		zuzhi.click();
		Thread.sleep(2000);
		WebElement qiantui = driver.findElement(By.cssSelector("a[class='sign signout'][href='javascript:void(0)']"));
		Thread.sleep(3000);
		qiantui.click();

	}

}
