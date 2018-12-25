import unittest
from core.Sendhttp import SendHttp
from core import Common
from core import DataProvider


class scenes(unittest.TestCase):
    def setUp(self):
        self.logurl = "/common/fgadmin/login"
        self.listurl = "/fgadmin/address/list?tm=1545495924820"
        self.delurl = "/fgadmin/address/delete"
        self.addurl = "/fgadmin/address/new"
        self.suburl = "/fgadmin/orders/submit"
        self.feeurl = "/common/getTransportFee?id=1&addressDetail=%E6%B5%99%E6%B1%9F%E7%9C%81_%E6%9D%AD%E5%B7%9E%E5%B8%82_%E6%BB%A8%E6%B1%9F%E5%B8%82&tm=1545553794046"
        self.skuurl = "/common/skuList"
        self.user = {"phoneArea": "86", "phoneNumber": "200000000077", "password": "netease123"}
        self.cookie = Common.getcookies(self.user)

    # 场景一：登录——获取收货地址表——获取邮费——提交订单
    def test_scene01(self):
        log_result = SendHttp().send_post(self.logurl, self.user)
        self.assertEqual(log_result['code'], 200)

        list_result = SendHttp().sent_get_bycookies(self.listurl, self.cookie)
        self.assertEqual(list_result['code'], 200)

        para = {"id": "1", "addressDetail": "浙江省_杭州市_滨江市", "tm": "1545553765731"}
        fee_result = SendHttp().sent_get_bycookies(self.feeurl, self.cookie, para)
        self.assertEqual(fee_result['code'], 200)

        submit_data = {"skuIds": "2,3", "skuNumbers": "1,1", "stockIds": "74966312,74966313", "receiverName": "测试用户",
                       "cellPhone": "12345678900", "addressDetail": "浙江大学", "province": "浙江省", "city": "杭州市",
                       "area": "滨江市", "voiceStatus": 0, "needInvoice": 0, "invoiceHead": "", "transportFee": 0,
                       "logisticsCompanyId": 1, "accessSource": "noSource", "accessDevice": 0}
        sub_result = SendHttp().send_post_bycookies(self.suburl, submit_data, self.cookie)
        self.assertEqual(sub_result['code'], 200)

    # 场景二：获取收获地址的id列表————删除最后一个收货地址————添加新的收货地址
    def test_scene02(self):
        cookie = Common.getcookies(self.user)
        list_result = SendHttp().sent_get_bycookies(self.listurl, cookie)
        id_num = int(list_result['result']['total'])
        id_list = list(range(id_num))
        for index in range(len(id_list)):
            id_list[index] = list_result['result']['list'][index]['id']
        self.assertEqual(list_result['code'], 200)

        para = {"id": id_list[id_num - 1]}
        del_result = SendHttp().send_post_bycookies(self.delurl, para, cookie)
        self.assertEqual(del_result['code'], 200)

        add_para = {"id": "", "receiverName": "测试用户", "cellPhone": "12345678900", "province": "浙江省", "city": "杭州市",
                    "area": "滨江市", "addressDetail": "浙江大学"}
        add_result = SendHttp().send_post_bycookies(self.addurl, add_para, cookie)
        self.assertEqual(add_result['code'], 200)

    # 场景三：查看产品信息————查看id为1的产品信息————查看id为2的产品信息————查看id为3的产品信息
    def test_scene03(self):
        result = SendHttp().sent_get(self.skuurl)
        self.assertEqual(result['code'], 200)

        result = SendHttp().sent_get(self.skuurl + "?goodsId=1")
        self.assertEqual(result['code'], 200)

        result = SendHttp().sent_get(self.skuurl + "?goodsId=2")
        self.assertEqual(result['code'], 200)

        result = SendHttp().sent_get(self.skuurl + "?goodsId=3")
        self.assertEqual(result['code'], 200)

    # 场景四：登录————获取收货地址————删除第一个地址————添加新地址————提交订单
    def test_scene04(self):
        log_result = SendHttp().send_post(self.logurl, self.user)
        self.assertEqual(log_result['code'], 200)

        cookie = Common.getcookies(self.user)
        list_result = SendHttp().sent_get_bycookies(self.listurl, cookie)
        id_num = int(list_result['result']['total'])
        id_list = list(range(id_num))
        for index in range(len(id_list)):
            id_list[index] = list_result['result']['list'][index]['id']
        self.assertEqual(list_result['code'], 200)

        para = {"id": id_list[id_num - 1]}
        del_result = SendHttp().send_post_bycookies(self.delurl, para, cookie)
        self.assertEqual(del_result['code'], 200)

        add_para = {"id": "", "receiverName": "测试用户", "cellPhone": "12345678900", "province": "浙江省", "city": "杭州市",
                    "area": "滨江市", "addressDetail": "浙江大学"}
        add_result = SendHttp().send_post_bycookies(self.addurl, add_para, cookie)
        self.assertEqual(add_result['code'], 200)

        submit_data = {"skuIds": "2,3", "skuNumbers": "1,1", "stockIds": "74966312,74966313", "receiverName": "测试用户",
                       "cellPhone": "12345678900", "addressDetail": "浙江大学", "province": "浙江省", "city": "杭州市",
                       "area": "滨江市", "voiceStatus": 0, "needInvoice": 0, "invoiceHead": "", "transportFee": 0,
                       "logisticsCompanyId": 1, "accessSource": "noSource", "accessDevice": 0}
        sub_result = SendHttp().send_post_bycookies(self.suburl, submit_data, self.cookie)
        self.assertEqual(sub_result['code'], 200)
