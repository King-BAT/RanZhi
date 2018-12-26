package com.edu.qgSingleAPI;

import org.testng.annotations.Test;

import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

/*
 * @author = 冯滨
 */
public class SkuListTest extends BaseTest {
	// 获取所有商品的 sku 信息或指定商品的 sku 信息。
	Checker check;
	String subUrl = "http://study-perf.qa.netease.com/common/skuList/";
	String result = null;

	// 获取所有的商品信息
	public String skuList() throws Exception {
		result = HttpDriver.doGet(subUrl);
		check = new Checker(result);
		return result;
	}

	// 通过goodsID获取
	public String skuListById(int goodsId) throws Exception {
		String newUrl = subUrl + "?goodsId=" + goodsId;
		String result = HttpDriver.doGet(newUrl);
		check = new Checker(result);
		return result;
	}

	// 尝试泛型失败
	public class info<T> {
		public T goodsId;

		public info(T goodsId) {
			this.goodsId = goodsId;
		}

		public T getKey() throws Exception {
			return goodsId;
		}
	}

	@Test(description = "获取商品信息")
	public void TestSkuList1() throws Exception {
		result = skuList();
		check.verifyTextPresent("message");
	}

	@Test(description = "验证方法不同")
	public void TestSkuList2() throws Exception {
		result = skuList();
		check.verifyXpath("message", "success");
	}

	@Test(description = "获取goodsID为1的商品")
	public void TestSkuListById() throws Exception {
		result = this.skuListById(1);
		check.verifyXpath("message", "success");

	}
//	@Test(description="尝试泛型")
//	public void TestSkuListById1() throws Exception {
//		info skB=new info("1");
//		HttpDriver driver=new HttpDriver();
//		String newUrl=subUrl+"goodsId="+skB;
//		String result = HttpDriver.doGet(newUrl);
//		System.out.println(result);
//		//check = new Checker(result);
//	}

}
