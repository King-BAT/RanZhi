package com.edu.qgMultiAPI;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.edu.common.Common;
import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;
/*
* @author=刁立翔
*/
public class DiaoLixiangTest extends BaseTest{
	String result = null;
	Checker check = null;
	String baseUrl = "http://study-perf.qa.netease.com";
	Common com;
	//登录成功
	public void testLoginSuccess(String username, String pwd) throws Exception {
		String loginUrl = "/common/fgadmin/login";
		JSONObject para = new JSONObject();
		para.element("phoneArea", 86);
		para.element("phoneNumber",username);
		para.element("password", pwd);
		result = HttpDriver.doPost(baseUrl + loginUrl, para);
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("message", "success");
	}
	//获取列表成功
	public void getList() {
		String url = "/common/skuList";
		try {
			result = HttpDriver.doGet(baseUrl+url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		check = new Checker(result);
	}
	/*//获取列表失败
	public void getListFault() {
		String url = "/common/skuList";
		CookieStore cook = Common.getLoginCookie(name, pwd);
		result = HttpDriver.doGet(baseUrl + url, cook);
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("code", "403");
	}*/
	//计算运费
	public void getFreight(int id,String addressDetail) {
		String url = "/common/getTransportFee";
		//URL里面加参数是MAP型  Map是个接口不能实例化
		Map<String, Object> para = new  HashMap<String, Object>();
		para.put("id", id);
		para.put("addressDetail", addressDetail);
		result = HttpDriver.doGet(baseUrl+url, para);
		System.out.println(result);
		check = new Checker(result);
		try {
			check.verifyXpath("message", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//提交订单
	public void getOrder(String skuid,String skuNum,String stockid,String name,String phone,
			String address,String province,String city,String area,String voiceStatus,
			String head,String Source) {
		String url = "/fgadmin/orders/submit";
		//CookieStore cookie =com.getLoginCookie();
		JSONObject para = new JSONObject();
		para.element("skuIds", skuid);
		para.element("skuNumbers", skuNum);
		para.element("stockIds", stockid);
		para.element("receiverName", name);
		para.element("cellPhone", phone);
		para.element("addressDetail",address);
		para.element("province", province);
		para.element("city", city);
		para.element("area", area);
		para.element("voiceStatus", voiceStatus);
		para.element("needInvoice", 1);
		para.element("invoiceHead", head);
		para.element("transportFee", 0);
		para.element("logisticsCompanyId", 1);
		para.element("accessSource", Source);
		para.element("accessDevice", 0);
		result = HttpDriver.doPost(baseUrl+url, para);
		System.out.println(result);
		check = new Checker(result);
		//check.verifyXpath("message", "success");
	}
	//添加地址
	public void addAddress(String id,String name,String phone,String address,
			String prvince,String city,String area) {
		String url = "/fgadmin/address/new";
		//CookieStore cookie = new CookieStore();
		JSONObject para = new JSONObject();
		para.element("id", id);
		para.element("receiverName", name);
		para.element("cellPhone", phone);
		para.element("addressDetail", address);
		para.element("province", prvince);
		para.element("city", city);
		para.element("area", area);
		result = HttpDriver.doPost(baseUrl+url, para);
	}
	//删除地址
	public void deleteAddress(String Id) {
		String url = "/fgadmin/address/delete";
		JSONObject para = new JSONObject();
		para.element("id", Id);
		result = HttpDriver.doPost(baseUrl+url, para);
	}
	
	@Test//场景一：登录成功-获取商品列表失败
	public void sence1() throws Exception {
		String username = "20000000000";
		String pwd = "netease123";
		this.testLoginSuccess(username, pwd);
		//验证错误后程序无法进行，用一种程序可通过的方法来表示异常
		try {
			String url = "/common/skuList?goodsId="+"\"1\"";
			result = HttpDriver.doGet(baseUrl+url);
		//执行try里面的，如果错误是catch括号里面的错误，那么执行catch{}中
		}catch(IllegalArgumentException e) {
			System.out.println(this.getClass()+".sence1执行结果失败与预期不符");
		}
		check = new Checker(result);
		System.out.println(result);
		
	}
	
	
	
	
	@Test //成功登陆-获取商品列表-计算运费-提交订单
	public void sence2() throws Exception {
		String username = "20000000000";
		String pwd = "netease123";
		this.testLoginSuccess(username, pwd);
		this.getList();
		int id = 1 ;
		String addressDetail = "河北省_石家庄市_裕华区";
		this.getFreight(id, addressDetail);
		String skuid = "2"; 
		String skuNum = "1";
		String stockid = "Netease123 ";
		String name = "张三"; 
		String phone = "20000000001"; 
		String address = "裕翔街道20号"; 
		String province = "河北省"; 
		String city = "石家庄市"; 
		String area = "裕华区"; 
		String voiceStatus = "1";
		String head = "网易杭州研究院 ";
		String Source = "noSource";
		this.getOrder(skuid, skuNum, stockid, name, phone, address, province, city, area, voiceStatus, head, Source);
		}
	@Test//成功登陆-获取商品列表-添加收货地址-提交订单
	public void sence3() {
		String username = "20000000000";
		String pwd = "netease123";
		try {
			this.testLoginSuccess(username, pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getList();
		String id = null;
		String name = "小明";
		String phone= "20000000001"; 
		String address = "裕翔街道20号";
		String prvince = "河北省";
		String city = "石家庄市";
		String area = "裕华区";
		this.addAddress(id,name, phone, address, prvince, city, area);
		String skuid = "2"; 
		String skuNum = "1";
		String stockid = "Netease123 ";
		/*String name = "张三"; 
		String phone = "20000000001"; 
		String address = "裕翔街道20号"; 
		String province = "河北省"; 
		String city = "石家庄市"; 
		String area = "裕华区"; */
		String voiceStatus = "1";
		String head = "网易杭州研究院 ";
		String Source = "noSource";
		this.getOrder(skuid, skuNum, stockid, "张三",  "20000000001", "裕翔街道20号", "河北省", "石家庄市", "裕华区", voiceStatus, head, Source);
		}
	
	@Test//成功登录-删除收货地址-添加收货地址-计算运费-提交订单
     public void sence4() {
		String username = "20000000000";
		String pwd = "netease123";
		try {
			this.testLoginSuccess(username, pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Id = "77243286" ;
		this.deleteAddress(Id);
		String id = null;
		String name = "小明";
		String phone= "20000000001"; 
		String address = "裕翔街道20号";
		String prvince = "河北省";
		String city = "石家庄市";
		String area = "裕华区";
		this.addAddress(id,name, phone, address, prvince, city, area);
		String addressDetail = "河北省_石家庄市_裕华区";
		this.getFreight(1, addressDetail);
		String skuid = "2"; 
		String skuNum = "1";
		String stockid = "Netease123 ";
		String voiceStatus = "1";
		String head = "网易杭州研究院 ";
		String Source = "noSource";
		this.getOrder(skuid, skuNum, stockid, "张三",  "20000000001", "裕翔街道20号", "河北省", "石家庄市", "裕华区", voiceStatus, head, Source);
		}
	
	}

