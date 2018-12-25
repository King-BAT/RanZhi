import unittest
from core.Sendhttp import SendHttp
from core import Common


class qgSceneTest(unittest.TestCase):
    def setUp(self):
        self.LoginUrl = "/common/fgadmin/login"
        self.AddressListUrl = "/fgadmin/address/list"
        self.TransportFeeUrl = "/common/getTransportFee"
        self.SubmitUrl = "/fgadmin/orders/submit"
        self.Deleteurl = "/fgadmin/address/delete"
        self.Addurl = "/fgadmin/address/new"
        self.Skulisturl = "/common/skuList?goodsId=1&tm=1542093552569"

    # 登录成功--查询收获地址--删除收获地址
    def test_scene1(self):
        # 登录
        user = {"phoneArea": "86", "phoneNumber": "200000000044", "password": "netease123"}
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        # 查询获取地址
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        # 删除收获地址
        aid = address_result['result']['list'][0]['id']
        print(id)
        para = {"id": aid}
        delete_result = SendHttp().sent_post_bycookies(self.Deleteurl, Common.getcookies(user), para)

    # 登录成功--查询收获地址--添加收获地址--删除收获地址
    def test_scene2(self):
        # 登录
        user = {"phoneArea": "86", "phoneNumber": "200000000011", "password": "netease123"}
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        # 查询获取地址
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        pro = address_result['result']['list'][0]['province']
        city = address_result['result']['list'][0]['city']
        area = address_result['result']['list'][0]['area']
        # 添加收获地址
        address = {"id": "",
                   "receiverName": "李四",
                   "cellPhone": "200000000011",
                   "province": "江苏省",
                   "city": "南京市",
                   "area": "鼓楼区",
                   "addressDetail": "南京大学"}
        add_result = SendHttp().sent_post_bycookies(self.Addurl, Common.getcookies(user), address)
        # 删除收获地址
        id = address_result['result']['list'][0]['id']
        delete_result = SendHttp().sent_post_bycookies(self.Deleteurl, Common.getcookies(user), id)

    # 登录成功--获取所有商品的sku列表--查询收货地址--计算运费--提交订单
    def test_scene3(self):
        # 登录
        user = {"phoneArea": "86", "phoneNumber": "200000000011", "password": "netease123"}
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        # 获取所有商品的sku列表
        sku_result = SendHttp().run_http(self.Skulisturl, "GET")
        # 查询获取地址
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        pro = address_result['result']['list'][0]['province']
        city = address_result['result']['list'][0]['city']
        area = address_result['result']['list'][0]['area']
        # 计算运费
        addressDetails = address_result['result']['list'][0]['addressDetail']
        para = {"id": 1, "addressDetail": addressDetails}
        SendHttp().run_http(self.TransportFeeUrl, "GET", para)
        # 提交订单
        submit_data = {"skuIds": "1",
                       "skuNumbers": "1,1",
                       "stockIds": "74966312,74966313",
                       "receiverName": "张三",
                       "cellPhone": "200000000011",
                       "addressDetail": addressDetails,
                       "province": pro,
                       "city": city,
                       "area": area,
                       "voiceStatus": 0,
                       "needInvoice": 0,
                       "invoiceHead": "",
                       "transportFee": 0,
                       "logisticsCompanyId": 1,
                       "accessSource": "noSource",
                       "accessDevice": 0}
        SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user), submit_data)

    # 登录失败--查询收获地址--计算运费
    def test_scene4(self):
        # 登录失败
        user = {"phoneArea": "86", "phoneNumber": "200000000011", "password": "netease12345"}
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 400)
        self.assertEqual(result['message'], '用户名或者密码错误')
        # 查询获取地址
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        # 计算运费
        try:
            addressDetails = address_result['result']['list'][0]['addressDetail']
            para = {"id": 1, "addressDetail": addressDetails}
            SendHttp().run_http(self.TransportFeeUrl, "GET", para)
        except KeyError:
            print("获取运费失败")


if __name__ == '__main__':
    unittest.main()
