import unittest
from core.Sendhttp import SendHttp
from core import Common


class qgSenceTest(unittest.TestCase):
    def setUp(self):
        self.LoginUrl = "/common/fgadmin/login"
        self.AddressListUrl = "/fgadmin/address/list"
        self.SkuListUrl = "/common/skuList"
        self.TransportFeeUrl = "/common/getTransportFee"
        self.NewAddressUrl = "/fgadmin/address/new"
        self.SubmitUrl = "/fgadmin/orders/submit"
        self.DeleteAddress = "/fgadmin/address/delete"

    # 登录成功 - -获取skuList - -获取list - -计算运费 - -新建地址 - -提交订单 - -删除地址
    def test_scene1(self):
        # 登录
        user = {"phoneArea": "86", "phoneNumber": "200000000044", "password": "netease123"}
        resultLogin = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(resultLogin['code'], 200)
        self.assertEqual(resultLogin['message'], 'success')

        # 获取skuList,goodsId=1
        goodsId = 1
        resultSkuList = SendHttp().run_http(self.SkuListUrl + "?" + str(goodsId), "GET")
        self.assertEqual(resultSkuList['code'], 200)
        self.assertEqual(resultSkuList['message'], 'success')

        # 实现获取list
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        pro = address_result['result']['list'][0]['province']
        city = address_result['result']['list'][0]['city']
        area = address_result['result']['list'][0]['area']
        self.assertEqual(address_result['code'], 200)
        self.assertEqual(address_result['message'], 'success')

        # 获取运费
        para = {"id": 1, "addressDetail": "河北省_石家庄市_裕华区"}
        resultFee = SendHttp().run_http(self.TransportFeeUrl, "GET", para)
        self.assertEqual(resultFee['code'], 200)
        self.assertEqual(resultFee['message'], 'success')

        # 新建地址
        para = {"receiverName": "张三",
                "cellPhone": "12345678901",
                'addressDetail': "浙江大学",
                "province": "浙江省",
                "city": "杭州市",
                "area": "滨江区"}
        resultNewAddress = SendHttp().sent_post_bycookies(self.NewAddressUrl, Common.getcookies(user), para)
        self.assertEqual(resultNewAddress['code'], 200)
        self.assertEqual(resultNewAddress['message'], 'success')

        # 提交订单
        submit_data = {"skuIds": "2,3",
                       "skuNumbers": "1,1",
                       "stockIds": "74966312,74966313",
                       "receiverName": "张三",
                       "cellPhone": "12615813537",
                       "addressDetail": "1 栋 3 单元",
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
        resultSubmit = SendHttp().sent_post_bycookies(self.SubmitUrl, Common.getcookies(user), submit_data)
        self.assertEqual(resultSubmit['code'], 200)
        self.assertEqual(resultSubmit['message'], 'success')

    # 登录成功 - -新建订单 - -删除订单
    def test_scene2(self):
        # 登录成功
        user = {"phoneArea": "86", "phoneNumber": "200000000044", "password": "netease123"}
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        # 新建订单
        para = {"receiverName": "张三",
                "cellPhone": "12345678901",
                'addressDetail': "浙江大学",
                "province": "浙江省",
                "city": "杭州市",
                "area": "滨江区"}
        SendHttp().sent_post_bycookies(self.NewAddressUrl, Common.getcookies(user), para)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        # 删除订单
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        aid = address_result['result']['list'][0]['id']
        para = {"id": aid}
        SendHttp().sent_post_bycookies(self.DeleteAddress, Common.getcookies(user), para)
        self.assertEqual(address_result['code'], 200)
        self.assertEqual(address_result['message'], 'success')

    # 登录成功 - -新建订单 - -提交订单
    def test_scene3(self):
        # 登录成功
        user = {"phoneArea": "86", "phoneNumber": "200000000044", "password": "netease123"}
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        # 新建订单
        para = {"receiverName": "张三",
                "cellPhone": "12345678901",
                'addressDetail': "浙江大学",
                "province": "浙江省",
                "city": "杭州市",
                "area": "滨江区"}
        SendHttp().sent_post_bycookies(self.NewAddressUrl, Common.getcookies(user), para)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        # 提交订单
        submit_data = {"skuIds": "2,3",
                       "skuNumbers": "1,1",
                       "stockIds": "74966312,74966313",
                       "receiverName": "张三",
                       "cellPhone": "12615813537",
                       "addressDetail": "1 栋 3 单元",
                       "province": "1",
                       "city": "1",
                       "area": "1",
                       "voiceStatus": 0,
                       "needInvoice": 0,
                       "invoiceHead": "",
                       "transportFee": 0,
                       "logisticsCompanyId": 1,
                       "accessSource": "noSource",
                       "accessDevice": 0}
        resultSubmit = SendHttp().sent_post_bycookies(self.SubmitUrl, Common.getcookies(user), submit_data)
        self.assertEqual(resultSubmit['code'], 200)
        self.assertEqual(resultSubmit['message'], 'success')

    # 登录失败 - -查询收货地址失败
    def test_scene4(self):
        # 登录失败
        user = {"phoneArea": "86", "phoneNumber": "200000000044", "password": "netease1234"}
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 400)
        self.assertEqual(result['message'], '用户名或者密码错误')
        # 查询收货地址list失败
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        self.assertEqual(address_result['code'], 403)
        self.assertEqual(address_result['message'], '请重新登录')

    # 登录成功 - -查询地址 - -计算运费 - -新建订单 - -提交订单 - -删除订单
    def test_scene5(self):
        # 登录成功
        user = {"phoneArea": "86", "phoneNumber": "200000000044", "password": "netease123"}
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        # 查询地址
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        pro = address_result['result']['list'][0]['province']
        city = address_result['result']['list'][0]['city']
        area = address_result['result']['list'][0]['area']
        self.assertEqual(address_result['code'], 200)
        self.assertEqual(address_result['message'], 'success')
        # 计算运费
        para = {"id": 1, "addressDetail": pro + "_" + city + "_" + area}
        resultFee = SendHttp().run_http(self.TransportFeeUrl, "GET", para)
        self.assertEqual(resultFee['code'], 200)
        self.assertEqual(resultFee['message'], 'success')
        # 新建订单
        para = {"receiverName": "张三",
                "cellPhone": "12345678901",
                'addressDetail': "浙江大学",
                "province": "浙江省",
                "city": "杭州市",
                "area": "滨江区"}
        SendHttp().sent_post_bycookies(self.NewAddressUrl, Common.getcookies(user), para)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        # 提交订单
        submit_data = {"skuIds": "2,3",
                       "skuNumbers": "1,1",
                       "stockIds": "74966312,74966313",
                       "receiverName": "张三",
                       "cellPhone": "12615813537",
                       "addressDetail": "1 栋 3 单元",
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
        resultSubmit = SendHttp().sent_post_bycookies(self.SubmitUrl, Common.getcookies(user), submit_data)
        self.assertEqual(resultSubmit['code'], 200)
        self.assertEqual(resultSubmit['message'], 'success')
        # 删除订单
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        aid = address_result['result']['list'][0]['id']
        para = {"id": aid}
        SendHttp().sent_post_bycookies(self.DeleteAddress, Common.getcookies(user), para)
        self.assertEqual(address_result['code'], 200)
        self.assertEqual(address_result['message'], 'success')


if __name__ == '__main__':
    unittest.main()
