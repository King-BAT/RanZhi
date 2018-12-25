import unittest
from core.Sendhttp import SendHttp
from core import Common


class scene(unittest.TestCase):
    def setUp(self):
        self.SkulistUrl = "/common/skuList"
        self.LoginUrl = "/common/fgadmin/login"
        self.AddressListUrl = "/fgadmin/address/list"
        self.TransportFeeUrl = "/common/getTransportFee"
        self.SubmitUrl = "/fgadmin/orders/submit"

    def test_scene1(self):
        # 获取所有商品的列表
        skulist_result = SendHttp().run_http(self.SkulistUrl, "GET")
        # result=SendHttp().sent_get(self.SkulistUrl)
        self.assertEqual(skulist_result['code'], 200)
        self.assertEqual(skulist_result['message'], 'success')
        # 获取goodsId=1的商品信息
        para1 = {"goodsId": 1}
        skulist_result1 = SendHttp().run_http(self.SkulistUrl, "GET", para1)
        self.assertEqual(skulist_result1['code'], 200)
        self.assertEqual(skulist_result1['message'], 'success')
        # 获取goodsId=2的商品信息
        para2 = {"goodsId": 2}
        skulist_result2 = SendHttp().run_http(self.SkulistUrl, "GET", para2)
        self.assertEqual(skulist_result2['code'], 200)
        self.assertEqual(skulist_result2['message'], 'success')
        # 获取goodsId=3的商品信息
        para3 = {"goodsId": 1}
        skulist_result3 = SendHttp().run_http(self.SkulistUrl, "GET", para3)
        self.assertEqual(skulist_result3['code'], 200)
        self.assertEqual(skulist_result3['message'], 'success')

    def test_scene2(self):
        # 登录
        user = {"phoneArea": "86", "phoneNumber": "200000000066", "password": "netease123"}
        login_result = SendHttp().run_http(self.LoginUrl, "POST", user)
        self.assertEqual(login_result['code'], 200)
        self.assertEqual(login_result['message'], 'success')
        # 查询收货地址
        cookie = Common.getcookies(user)
        address_result = SendHttp().sent_get_bycookies(self.AddressListUrl, cookie)
        self.assertEqual(address_result['code'], 200)
        self.assertEqual(address_result['message'], 'success')
        # 计算运费
        address_list1 = address_result['result']['list'][0]
        province = address_list1['province']
        city = address_list1['city']
        area = address_list1['area']
        address_id = address_list1["id"]
        addresstoDetial = province + "_" + city + "_" + area
        para = {"id": address_id, "addressDetail": addresstoDetial}
        fee_result = SendHttp().run_http(self.TransportFeeUrl, "GET", para)
        fee = fee_result['result']
        print("运费为:", fee)
        self.assertEqual(fee_result['code'], 200)
        self.assertEqual(fee_result['message'], 'success')
        # 提交订单
        receiverName = address_list1['receiverName']
        cellPhone = address_list1['cellPhone']
        addressDetail = address_list1['addressDetail']
        submit_para = {
            "skuIds": "2,3",
            "skuNumbers": "1,1",
            "stockIds": "74966312,74966313",
            "receiverName": receiverName,
            "cellPhone": cellPhone,
            "addressDetail": addressDetail,
            "province": province,
            "city": city,
            "area": area,
            "voiceStatus": 0,
            "needInvoice": 0,
            "invoiceHead": "",
            "transportFee": fee,
            "logisticsCompanyId": 1,
            "accessSource": "noSource",
            "accessDevice": 0
        }
        submit_result = SendHttp().sent_post_bycookies(self.SubmitUrl, cookie, submit_para)
        self.assertEqual(submit_result['code'], 200)
        self.assertEqual(submit_result['message'], 'success')


if __name__ == '__main__':
    unittest.main()
