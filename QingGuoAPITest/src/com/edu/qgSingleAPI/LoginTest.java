package com.edu.qgSingleAPI;

import java.io.IOException;

import org.testng.annotations.Test;

import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.dataprovider.NSDataProvider;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;

/*
 * @author = 刁立翔
 */
public class LoginTest extends BaseTest {
	String result = null;
	Checker check = null;
	String loginUrl = "/common/fgadmin/login";

	public void testLogin(String username, String pwd, String retmsg) throws Exception {
		JSONObject para = new JSONObject();
		para.element("phoneArea", "86");
		para.element("phoneNumber", username);
		para.element("password", pwd);
		result = HttpDriver.doPost(baseUrl + loginUrl, para);
		System.out.println(result);
		check = new Checker(result);
		check.verifyTextPresent("message");
		check.verifyXpath("message", retmsg);
	}

	@Test 
	public void testLoginSuccess() throws Exception {
		String name = "20000000000";
		String password = "netease123";
		String message = "success";
		this.testLogin(name, password, message);
	}

	@Test // 登录失败，phoneArea参数类型不正确
	public void testLoginFault1() throws Exception {
		JSONObject para = new JSONObject();
		String uesrname1 = "20000000000";
		String pwd1 = "netease123";
		String retms = "用户名或者密码错误";
		para.element("phoneArea", 86);
		para.element("phoneNumber", uesrname1);
		para.element("password", pwd1);
		result = HttpDriver.doPost(baseUrl + loginUrl, para);
		System.out.println(result);
		check = new Checker(result);
		check.verifyTextPresent("message");
		check.verifyXpath("message", retms);
	}

	@Test // 登录失败，password参数类型不正确
	public void testLoginFault2() throws Exception {
		JSONObject para = new JSONObject();
		String uesrname2 = "20000000000";
		int pwd1 = 123456;
		String retmsg = "用户名或者密码错误";
		para.element("phoneArea", 86);
		para.element("phoneNumber", uesrname2);
		para.element("password", pwd1);
		result = HttpDriver.doPost(baseUrl + loginUrl, para);
		System.out.println(result);
		check = new Checker(result);
		check.verifyTextPresent("message");
		check.verifyXpath("message", retmsg);
	}

	@Test // 登录失败，缺少phoneArea参数
	public void testLoginFault3() throws Exception {
		JSONObject para = new JSONObject();
		String username = "20000000000";
		String pwd = "neteas123";
		String retmsg = "用户名或者密码错误";
		// para.element("phoneArea", "86");
		para.element("phoneNumber", username);
		para.element("password", pwd);
		result = HttpDriver.doPost(baseUrl + loginUrl, para);
		System.out.println(result);
		check = new Checker(result);
		check.verifyTextPresent("message");
		check.verifyXpath("message", retmsg);
	}

	@Test // 登录失败，缺少phoneNumber参数
	public void testLoginFault4() throws Exception {
		String username = null;
		String pwd = "neteas123";
		String retmsg = "用户名或者密码错误";
		this.testLogin(username, pwd, retmsg);
	}

	@Test // 登录失败，缺少password参数
	public void testLoginFault5() throws Exception {
		String username = null;
		String pwd = "neteas123";
		String retmsg = "用户名或者密码错误";
		this.testLogin(username, pwd, retmsg);
	}

	@Test // 登录失败，电话号码超过11位
	public void testLoginFault6() throws Exception {
		String name = "123456789012";
		String password = "netease123";
		String message = "用户名或者密码错误";
		this.testLogin(name, password, message);
	}

	@Test // 登录失败，密码错误
	public void testLoginFault7() throws Exception {
		String name = "20000000000";
		String password = "wrong";
		String message = "用户名或者密码错误";
		this.testLogin(name, password, message);
	}

	@Test // 登录失败，用户不存在
	public void testLoginFault8() throws Exception {
		String name = "10086";
		String password = "netease123";
		String message = "用户名或密码错误";
		this.testLogin(name, password, message);
	}

	// 登陆成功，通过execl或者text或者数据库传入十个用户名&密码
	// @Test后面的括号里相当于数据驱动的标识
//	@Test(dataProvider = "user", dataProviderClass = NSDataProvider.class)
//	public void testFault9(String name, String password) throws IOException, Exception {
//		String url = "http://study-perf.qa.netease.com/common/fgadmin/login";
//		JSONObject json = new JSONObject();
//		json.element("phoneArea", "86");
//		json.element("phoneNumber", name);
//		json.element("phoneAre", password);
//		result = HttpDriver.doPost(url, json);
//	}
}
