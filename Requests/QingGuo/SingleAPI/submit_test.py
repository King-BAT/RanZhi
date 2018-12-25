import unittest
from core.Sendhttp import SendHttp
from core import Common


class Submit(unittest.TestCase):
    def setUp(self):
        self.url = "/fgadmin/orders/submit"
        self.user = {
            "phoneArea": "86",
            "phoneNumber": "200000000011",
            "password": "netease123"}

    # 提交成功
    def test_submit_1(self):
        self.lists = {"skuIds": "2",
                      "skuNumbers": "1",
                      "stockIds": "74966313",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 缺少skuIds参数
    def test_submit_2(self):
        self.lists = {"skuNumbers": "1",
                      "stockIds": "74966313",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 缺少skuNumbers参数
    def test_submit_3(self):
        self.lists = {"skuIds": "2",
                      "stockIds": "74966313",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 提交失败，缺少stockIds参数
    def test_submit_4(self):
        self.lists = {"skuIds": "2",
                      "skuNumbers": "1",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 提交失败，缺少addressDetail参数
    def test_submit_5(self):
        self.lists = {"skuIds": "2",
                      "skuNumbers": "1",
                      "stockIds": "74966313",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 提交失败，缺少province参数
    def test_submit_6(self):
        self.lists = {"skuIds": "2",
                      "skuNumbers": "1",
                      "stockIds": "74966313",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 提交失败，province和city不匹配
    def test_submit_7(self):
        self.lists = {"skuIds": "2",
                      "skuNumbers": "1",
                      "stockIds": "74966313",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "石家庄市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 提交失败，缺少skuIds和skunumbers两个参数
    def test_submit_8(self):
        self.lists = {"stockIds": "74966313",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 提交失败，缺少skuIds、skunumbers和stockIds参数
    def test_submit_9(self):
        self.lists = {"receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 提交失败，skuIds为int类型
    def test_submit_a(self):
        self.lists = {"skuIds": 1,
                      "skuNumbers": "1",
                      "stockIds": "74966313",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 提交失败，skunumbers参数类型为int类型
    def test_submit_b(self):
        self.lists = {"skuIds": "2",
                      "skuNumbers": 1,
                      "stockIds": "74966313",
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    # 提交失败，stockIds参数为int类型
    def test_submit_c(self):
        self.lists = {"skuIds": "2",
                      "skuNumbers": "1",
                      "stockIds": 74966313,
                      "receiverName": "晓",
                      "cellPhone": "15226588864",
                      "addressDetail": "组织",
                      "province": "浙江省",
                      "city": "杭州市",
                      "area": "滨江区",
                      "voiceStatus": 0,
                      "needInvoice": 0,
                      "invoiceHead": "",
                      "transportFee": 0,
                      "logisticsCompanyId": 1,
                      "accessSource": "noSource",
                      "accessDevice": 0}

    def tearDown(self):
        SendHttp().sent_get_bycookies(self.url, Common.getcookies(self.user), self.lists)


if __name__ == '__main__':
    unittest.main()
