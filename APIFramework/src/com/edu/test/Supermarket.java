package com.edu.test;


import java.net.URLEncoder;

import org.apache.poi.ss.usermodel.PrintCellComments;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;

public class Supermarket extends BaseTest {
	Checker check =null;
	public String price(String pid) throws Exception {
		String url = "http://10.7.90.245:8080/Supermarket/analysis/lookupprice";
		String para = URLEncoder.encode("{\"pId\":\""+pid+"\"}", "UTF-8");

		String result = HttpDriver.doGet(url, "goodsCode=" + para);
		
		return result;
	}
	@Test
	public void priceSuccess() throws Exception {
		

		String result =this.price("123456");
		JSONObject json=JSONObject.fromObject(result);
		String actualPrice=json.getJSONObject("good").getJSONObject("content").getString("pPrice");
		
		String sql = "";
		sql = "SELECT price FROM t_product where pid = 123456  ";
		String expectPrice =db.queryToString(sql);
		Assert.assertEquals(actualPrice, expectPrice);
		
	}

}
