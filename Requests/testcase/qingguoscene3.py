import unittest
from core.Sendhttp import SendHttp
from core import Common
from core import DataProvider


class qgLoginTest(unittest.TestCase):
    def setUp(self):
        self.LoginUrl = "/common/fgadmin/login"
        self.AddressListUrl = "/fgadmin/address/list"
        self.TransportFeeUrl = "/common/getTransportFee"
        self.SubmitUrl = "/fgadmin/orders/submit"

    def test_scene(self):
        # 登录
        user = {"phoneArea": "86", "phoneNumber": "20000000000", "password": "netease123"}
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')
        result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(result['code'], 200)

        # 实现获取地址
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user))
        pro = address_result['result']['list'][0]['province']
        city = address_result['result']['list'][0]['city']
        area = address_result['result']['list'][0]['area']

        # 获取运费
        para = {"id": 1, "addressDetail": "null"}
        SendHttp().run_http(self.TransportFeeUrl, "GET", para)

        # 提交订单
        submit_data = {"skuIds": "2,3", "skuNumbers": "1,1", "stockIds": "74966312,74966313", "receiverName": "张三",
                       "cellPhone": "12615813537", "addressDetail": "1 栋 3 单元", "province": pro, "city": city,
                       "area": area, "voiceStatus": 0, "needInvoice": 0, "invoiceHead": "", "transportFee": 0,
                       "logisticsCompanyId": 1, "accessSource": "noSource", "accessDevice": 0}
        SendHttp().sent_get_bycookies(self.AddressListUrl, Common.getcookies(user), submit_data)


if __name__ == '__main__':
    unittest.main()
