package com.edu.qgSingleAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;

/*
 * @author = 徐世伟
 */
public class GetTransportFeeTest {
	String fee = "http://study-perf.qa.netease.com/common/getTransportFee";
	String result = null;

	public String fee(String addressDetial, String id) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("addressDetial", addressDetial);
		result = HttpDriver.doGet(fee, map);
		return result;
	}

	public String fee1(String addressDetial, int id) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("addressDetial", addressDetial);
		result = HttpDriver.doGet(fee, map);
		return result;
	}

	public String fee2(int addressDetial, int id) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("addressDetial", addressDetial);
		result = HttpDriver.doGet(fee, map);
		return result;
	}

	public String fee3(String addressDetial) {
		Map map = new HashMap();
		map.put("addressDetial", addressDetial);
		result = HttpDriver.doGet(fee, map);
		return result;
	}

	public String fee4(int id) {
		Map map = new HashMap();
		map.put("id", id);
		result = HttpDriver.doGet(fee, map);
		return result;
	}

	public String fee5(int id, String addressDetial) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("addressDetial", addressDetial);
		result = HttpDriver.doGet(fee, map);
		return result;
	}

	public String fee6(String addressDetial, String id) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("addressDetial", addressDetial);
		result = HttpDriver.doGet(fee, map);
		return result;
	}

	@Test
	public void getFee1() throws Exception {
		result = this.fee1("浙江省_杭州市_滨江区", 1);
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "success");
		Assert.assertEquals(feejson.getInt("code"), 200);
	}

	@Test
	public void getFee2() throws Exception {
		result = this.fee3("浙江省_杭州市_滨江区");
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);
	}

	@Test
	public void getFee14() throws Exception {
		result = this.fee4(1);
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "success");
		Assert.assertEquals(feejson.getInt("code"), 200);
	}

	@Test
	public void getFee3() throws Exception {
		result = this.fee6("浙江省_杭州市_滨江区", "1");
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "success");
		Assert.assertEquals(feejson.getInt("code"), 200);
	}

	@Test
	public void getFee4() throws Exception {
		try {
			result = this.fee("浙江省_杭州市_滨江区", "\"1\"");
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "请求失败");
			Assert.assertEquals(feejson.getInt("code"), 400);
		} catch (IllegalArgumentException e) {
			System.out.println("URL非法");
		}

	}

	@Test
	public void getFee5() throws Exception {
		result = this.fee("浙江省_杭州市_滨江区", "");
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);
	}

	@Test
	public void getFee6() throws Exception {
		result = this.fee("浙江省_杭州市_滨江区", null);
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);
	}

	@Test
	public void getFee7() throws Exception {
		result = this.fee1("", 1);
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "success");
		Assert.assertEquals(feejson.getInt("code"), 200);
	}

	@Test
	public void getFee8() throws Exception {
		result = this.fee1(null, 1);
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "success");
		Assert.assertEquals(feejson.getInt("code"), 200);
	}

	@Test
	public void getFee9() throws Exception {
		result = this.fee1("浙江省_杭州市_滨江区", 3);
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "success");
		Assert.assertEquals(feejson.getInt("code"), 200);
	}

	@Test
	public void getFee10() throws Exception {
		result = this.fee2(2, 1);
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "success");
		Assert.assertEquals(feejson.getInt("code"), 200);
	}

	@Test
	public void getFee11() throws Exception {
		result = HttpDriver.doGet(fee);
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);
	}

	@Test
	public void getFee12() throws Exception {
		result = this.fee5(1, "1");
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "success");
		Assert.assertEquals(feejson.getInt("code"), 200);
	}

	@Test
	public void getFee13() throws Exception {
		result = this.fee6(null, null);
		JSONObject feejson = JSONObject.fromObject(result);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);
	}

}
