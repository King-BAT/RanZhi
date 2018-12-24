package com.edu.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.edu.dataprovider.*;

public class loginNiMeng {
	public static WebDriver driver =null;
	@Test(dataProvider="submitTestCase",dataProviderClass=NSDataProvider.class)
	public static void submit(String principalsName) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "F:\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin","E:\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.lemonpm.com/logon/index");
		Thread.sleep(2000);
		driver.findElement(By.name("companyCode")).sendKeys("HebeiNormalUniversitySoftwareCollege");
		driver.findElement(By.name("logonName")).sendKeys("孙立莹");
		driver.findElement(By.name("password")).sendKeys("sly19970418");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1500);
		//点击测试用例
		driver.findElement(By.xpath("//a[@href='/testcase/index']")).click();
		Thread.sleep(1500);
		driver.findElement(By.linkText("新增测试用例")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//input[@id='testcase_title']")).sendKeys("测试一");
		Thread.sleep(1500);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB);
		action.sendKeys("这是测试一的前置条件");
		action.sendKeys(Keys.TAB);
		action.sendKeys("这是测试一的测试步骤");
		Thread.sleep(1500);
		action.sendKeys(Keys.TAB);
		action.sendKeys("这是测试一的预期结果");
		action.sendKeys(Keys.TAB);
		action.sendKeys("这是测试一的实际结果");
		action.perform();
		Select module = new Select(driver.findElement(By.name("testcase.module.id")));
		module.selectByVisibleText("登录模块");
		Thread.sleep(1500);
		Select priority = new Select(driver.findElement(By.name("testcase.priority")));
		priority.selectByValue("7");
		Thread.sleep(1500);
		System.out.println("即将进入if-else判定");
		if(principalsName=="UI") {
			System.out.println("if-else判定principalsName:"+principalsName);
			driver.findElement(By.id("testcase.principals-1")).click();
			Thread.sleep(1000);
			System.out.println("UI");
			//jinruoqi
			driver.findElement(By.id("testcase.principals-3")).click();
			Thread.sleep(1000);
		}
		else if(principalsName=="DataBase") {
			System.out.println("if-else判定principalsName:"+principalsName);
			driver.findElement(By.id("testcase.principals-1")).click();
			Thread.sleep(1000);
			System.out.println("DataBase");
			driver.findElement(By.id("testcase.principals-4")).click();
			Thread.sleep(1000);
		}
		else if(principalsName=="Development") {
			System.out.println("if-else判定principalsName:"+principalsName);
			driver.findElement(By.id("testcase.principals-1")).click();			
			Thread.sleep(1000);
			System.out.println("Development");
			driver.findElement(By.id("testcase.principals-5")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("testcase.principals-6")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("testcase.principals-7")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("testcase.principals-2")).click();
			Thread.sleep(1000);
		}
		System.out.println("已经执行完if-else判定");
		Thread.sleep(1500);
		driver.findElement(By.name("testcase.executeTime")).sendKeys("2018-11-04");
		Thread.sleep(1500);
		Select testcase_status = new Select(driver.findElement(By.id("testcase_status")));
		testcase_status.selectByVisibleText("未通过");
//		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
}
