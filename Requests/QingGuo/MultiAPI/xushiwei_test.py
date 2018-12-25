import unittest
from core.Sendhttp import SendHttp
from core import Common


class qgSenceTest(unittest.TestCase):
    def setUp(self):
        self.loginurl = "/common/fgadmin/login"
        self.listurl = "/fgadmin/address/list"
        self.feeurl = "/common/getTransportFee"
        self.submiturl = "/fgadmin/orders/submit"

    # 登录成功-查询收货地址成功-计算运费成功-提交订单成功
    def test_first(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000055", "password": "netease123"}
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "1",
            "tm": "1545038153217"}
        detial = {"skuIds": "3",
                  "skuNumbers": "1",
                  "stockIds": "74966313",
                  "receiverName": "小飞龙",
                  "cellPhone": "12345678909",
                  "addressDetail": "河北师范",
                  "province": "四川省",
                  "city": "成都市",
                  "area": "锦江区",
                  "voiceStatus": 0,
                  "needInvoice": 0,
                  "invoiceHead": "",
                  "transportFee": 6,
                  "logisticsCompanyId": 1,
                  "accessSource": "noSource",
                  "accessDevice": 0}
        login_result = SendHttp().run_http(self.loginurl, "POST", user)
        self.assertEqual(login_result['code'], 200)
        self.assertEqual(login_result['message'], "success")
        fee_result = SendHttp().sent_get(self.feeurl, address)
        self.assertEqual(fee_result['code'], 200)
        self.assertEqual(fee_result['message'], "success")
        list_result = SendHttp().sent_get_bycookies(self.listurl, Common.getcookies(user))
        self.assertEqual(list_result['code'], 200)
        self.assertEqual(list_result['message'], "success")
        submit_result = SendHttp().sent_post_bycookies(self.submiturl, Common.getcookies(user), detial)
        self.assertEqual(submit_result['code'], 200)
        self.assertEqual(submit_result['message'], "success")

    # 登录成功-有收获地址-计算运费失败-提交订单
    def test_second(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000055", "password": "netease123"}
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "\"1\"",
            "tm": "1545038153217"}
        detial = {"skuIds": "3",
                  "skuNumbers": "1",
                  "stockIds": "74966313",
                  "receiverName": "小飞龙",
                  "cellPhone": "12345678909",
                  "addressDetail": "河北师范",
                  "province": "四川省",
                  "city": "成都市",
                  "area": "锦江区",
                  "voiceStatus": 0,
                  "needInvoice": 0,
                  "invoiceHead": "",
                  "transportFee": 6,
                  "logisticsCompanyId": 1,
                  "accessSource": "noSource",
                  "accessDevice": 0}
        login_result = SendHttp().run_http(self.loginurl, "POST", user)
        self.assertEqual(login_result['code'], 200)
        self.assertEqual(login_result['message'], "success")
        fee_result = SendHttp().sent_get(self.feeurl, address)
        self.assertEqual(fee_result['code'], 400)
        self.assertEqual(fee_result['message'], "请求失败")
        list_result = SendHttp().sent_get_bycookies(self.listurl, Common.getcookies(user))
        self.assertEqual(list_result['code'], 200)
        self.assertEqual(list_result['message'], "success")
        submit_result = SendHttp().sent_post_bycookies(self.submiturl, Common.getcookies(user), detial)
        self.assertEqual(submit_result['code'], 200)
        self.assertEqual(submit_result['message'], "success")

    # 登录成功-查询收货地址-计算运费-提交订单失败
    def test_third(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000055", "password": "netease123"}
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "1",
            "tm": "1545038153217"}
        detial = {"skuIds": "\"3\"",
                  "skuNumbers": "1",
                  "stockIds": "74966313",
                  "receiverName": "小飞龙",
                  "cellPhone": "12345678909",
                  "addressDetail": "河北师范",
                  "province": "四川省",
                  "city": "成都市",
                  "area": "锦江区",
                  "voiceStatus": 0,
                  "needInvoice": 0,
                  "invoiceHead": "",
                  "transportFee": 6,
                  "logisticsCompanyId": 1,
                  "accessSource": "noSource",
                  "accessDevice": 0}
        login_result = SendHttp().run_http(self.loginurl, "POST", user)
        self.assertEqual(login_result['code'], 200)
        self.assertEqual(login_result['message'], "success")
        fee_result = SendHttp().sent_get(self.feeurl, address)
        self.assertEqual(fee_result['code'], 200)
        self.assertEqual(fee_result['message'], "success")
        list_result = SendHttp().sent_get_bycookies(self.listurl, Common.getcookies(user))
        self.assertEqual(list_result['code'], 200)
        self.assertEqual(list_result['message'], "success")
        submit_result = SendHttp().sent_post_bycookies(self.submiturl, Common.getcookies(user), detial)
        self.assertEqual(submit_result['code'], 400)
        self.assertEqual(submit_result['message'], "请求失败")

    # 登录失败-无查询收货地址-计算运费-提交订单
    def test_forth(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000055", "password": "netease1234"}
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "1",
            "tm": "1545038153217"}
        detial = {"skuIds": "3",
                  "skuNumbers": "1",
                  "stockIds": "74966313",
                  "receiverName": "小飞龙",
                  "cellPhone": "12345678909",
                  "addressDetail": "河北师范",
                  "province": "四川省",
                  "city": "成都市",
                  "area": "锦江区",
                  "voiceStatus": 0,
                  "needInvoice": 0,
                  "invoiceHead": "",
                  "transportFee": 6,
                  "logisticsCompanyId": 1,
                  "accessSource": "noSource",
                  "accessDevice": 0}
        login_result = SendHttp().run_http(self.loginurl, "POST", user)
        self.assertEqual(login_result['code'], 400)
        self.assertEqual(login_result['message'], "用户名或者密码错误")
        fee_result = SendHttp().sent_get(self.feeurl, address)
        self.assertEqual(fee_result['code'], 200)
        self.assertEqual(fee_result['message'], "success")
        list_result = SendHttp().run_http(self.listurl, "GET", user)
        self.assertEqual(list_result['code'], 403)
        self.assertEqual(list_result['message'], "请重新登录")
        submit_result = SendHttp().sent_post_bycookies(self.submiturl, Common.getcookies(user), detial)
        self.assertEqual(submit_result['code'], 403)
        self.assertEqual(submit_result['message'], "请重新登录")

    # 登录失败-有查询收货地址-计算运费失败-提交订单
    def test_fifth(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000055", "password": "netease1234"}
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "\"1\"",
            "tm": "1545038153217"}
        detial = {"skuIds": "3",
                  "skuNumbers": "1",
                  "stockIds": "74966313",
                  "receiverName": "小飞龙",
                  "cellPhone": "12345678909",
                  "addressDetail": "河北师范",
                  "province": "四川省",
                  "city": "成都市",
                  "area": "锦江区",
                  "voiceStatus": 0,
                  "needInvoice": 0,
                  "invoiceHead": "",
                  "transportFee": 6,
                  "logisticsCompanyId": 1,
                  "accessSource": "noSource",
                  "accessDevice": 0}
        login_result = SendHttp().run_http(self.loginurl, "POST", user)
        self.assertEqual(login_result['code'], 400)
        self.assertEqual(login_result['message'], "用户名或者密码错误")
        fee_result = SendHttp().sent_get(self.feeurl, address)
        self.assertEqual(fee_result['code'], 400)
        self.assertEqual(fee_result['message'], "请求失败")
        list_result = SendHttp().sent_get_bycookies(self.listurl, Common.getcookies(user))
        self.assertEqual(list_result['code'], 403)
        self.assertEqual(list_result['message'], "请重新登录")
        submit_result = SendHttp().sent_post_bycookies(self.submiturl, Common.getcookies(user), detial)
        self.assertEqual(submit_result['code'], 403)
        self.assertEqual(submit_result['message'], "请重新登录")


if __name__ == '__main__':
    unittest.main()
