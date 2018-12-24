package com.edu.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.dataprovider.NSDataProvider;
import com.edu.utils.Checker;
import com.edu.common.*;

import net.sf.json.JSONObject;

public class QgDeal {
	Checker check;
	//10个用户提交地址
	@Test(dataProvider="user",dataProviderClass=NSDataProvider.class)
	public void submit(String name,String password) throws IOException, Exception {
		CookieStore cookie=Common.getLoginCookie(name, password);
		String url = "http://study-perf.qa.netease.com/fgadmin/orders/submit";
		JSONObject para = new JSONObject();
		para.element("id","");
		para.element("skuIds", "2,3");
		para.element("skuNumbers", "1,1");
		para.element("stockIds", "74966312,74966313");
		para.element("cellPhone", "12615813537");
		para.element("receiverName", "张三");
		para.element("addressDetail", "河北师大新校区");
		para.element("province", "河北省");
		para.element("city", "石家庄市");
		para.element("area", "裕华区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "") ;
		para.element("transportFee", 0);
		para.element("logisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	    String result = HttpDriver.doPost(url, para, cookie);
	    System.out.println(result);
//		����
		JSONObject json = JSONObject.fromObject(result);
		assertEquals(json.getString("message"),"success");
		assertEquals(json.getInt("code"),200);
	}
}
