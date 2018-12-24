package com.edu.core;

import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.edu.utils.DbHelper;

public class BaseTest {
	public DbHelper db ;
	public String baseUrl ="http://study-perf.qa.netease.com";
	@BeforeMethod
	public void setUp()	throws Exception {
		db=DbHelper.getInstance();
  }

	@AfterMethod
	public void tearDown() throws Exception {
		
		db.close();
	}
//	@BeforeSuite
//	public void addListener(ITestContext context) throws Exception {
//	TestRunner runner = (TestRunner) context;
//	runner.addListener(new ApiListener());
//	}               

}
