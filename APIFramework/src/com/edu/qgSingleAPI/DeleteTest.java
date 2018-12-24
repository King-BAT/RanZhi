package com.edu.qgSingleAPI;

import java.io.IOException;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.common.*;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;

/*
 * @author = 张飞宇
 */
public class DeleteTest {
	String baseurl = "http://study-perf.qa.netease.com";
	String listurl = "/fgadmin/address/list?tm=1545495924820";
	String delurl = "/fgadmin/address/delete";
	String addurl = "/fgadmin/address/new";
	JSONObject para = null;
	JSONObject json = null;
	String result;
	private String u_name = "200000000077";
	private String u_pwd = "netease123";
	private Checker checker = null;

	private String[] getIdList(String username, String password) throws IOException, Exception {
		CookieStore cookie = Common.getLoginCookie(username, password);
		result = HttpDriver.doGet(baseurl + listurl, cookie);
		json = JSONObject.fromObject(result).getJSONObject("result");
		int id_num = json.getInt("total");
		String[] id_list = new String[id_num];
		for (int i = 0; i < id_num; i++) {
			id_list[i] = json.getJSONArray("list").getJSONObject(i).getString("id");
		}
		return id_list;
	}

//	地址不够时解除注释添加地址。
	/*
	 * @Test public void address_front() throws IOException, Exception{ para = new
	 * JSONObject(); para.element("id", ""); para.element("receiverName", "测试用户");
	 * para.element("cellPhone", "12345678900"); para.element("province", "浙江省");
	 * para.element("city", "杭州市"); para.element("area", "滨江市");
	 * para.element("addressDetail", "浙江大学"); HttpDriver.doPost(baseurl + addurl,
	 * para, Common.getLoginCookie(u_name, u_pwd)); }
	 */

	@Test // 删除成功，收货地址列表第一个地址删除
	public void del_success_test() throws IOException, Exception {
		para = new JSONObject();
		String id[] = this.getIdList(u_name, u_pwd);
		para.element("id", id[0]);
		result = HttpDriver.doPost(baseurl + delurl, para, Common.getLoginCookie(u_name, u_pwd));
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test // 删除失败，无此id地址
	public void del_fail01_test() throws IOException, Exception {
		para = new JSONObject();
		para.element("id", "1");
		result = HttpDriver.doPost(baseurl + delurl, para, Common.getLoginCookie(u_name, u_pwd));
		checker = new Checker(result);
		checker.assertQulity("code", "400");
	}

	@Test // 删除失败，未登录
	public void del_fail02_test() throws IOException, Exception {
		para = new JSONObject();
		String id[] = this.getIdList(u_name, u_pwd);
		para.element("id", id[0]);
		result = HttpDriver.doPost(baseurl + delurl, para);
		checker = new Checker(result);
		checker.assertQulity("code", "403");
	}

	@Test // 删除失败，参数类型不正确
	public void del_fail03_test() throws IOException, Exception {
		para = new JSONObject();
		para.element("id", 1);
		result = HttpDriver.doPost(baseurl + delurl, para, Common.getLoginCookie(u_name, u_pwd));
		checker = new Checker(result);
		checker.assertQulity("code", "400");
	}

	@Test // 删除失败，无id/id为空
	public void del_fail04_test() throws IOException, Exception {
		para = new JSONObject();
		result = HttpDriver.doPost(baseurl + delurl, para, Common.getLoginCookie(u_name, u_pwd));
		checker = new Checker(result);
		checker.assertQulity("code", "400");
	}
}
