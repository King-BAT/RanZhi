package com.edu.test;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;


public class SkuListTest extends BaseTest{
	Checker check;
	String subUrl = "/common/skuList/";
	String result=null;

	public String skuList() throws Exception {
		result = HttpDriver.doGet(baseUrl+subUrl);
		check = new Checker(result);
		return result;
	}
	
	public String skuListById(String goodsId) throws Exception {
	
		Map map = new HashMap();
		map.put("goodsId", goodsId);
		map.put("addressDetail", "");
		String result = HttpDriver.doGet(subUrl,map);
		check = new Checker(result);
		return result;
	}

	@Test
	public void TestSkuList1() throws Exception {
		result = skuList();
		check.verifyTextPresent("mesage");
	}

	@Test
	public void TestSkuList2() throws Exception {
		result = skuList();
		check.verifyXpath("message", "success");
	}

	@Test
	public void TestSkuListById() throws Exception {
		result=this.skuListById("1");
		check.verifyXpath("message", "success");
	}
	
	@Test
	public void TestSkuListByWrongIdType() throws Exception {
		String goodsId=URLEncoder.encode("\"1\"","UTF-8");
		 result=this.skuListById(goodsId);
		check.verifyXpath("message", "Failed");
	}

}
