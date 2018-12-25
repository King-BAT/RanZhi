import unittest
from core.Sendhttp import SendHttp
from core import Common


class skuList(unittest.TestCase):
    def setUp(self):
        self.LoginUrl = "/common/fgadmin/login"
        self.AddressListUrl = "/fgadmin/address/list"
        self.SkuListUrl = "/common/skuList "
        self.TransportFeeUrl = "/common/getTransportFee"
        self.NewAddressUrl = "/fgadmin/address/new"
        self.SubmitUrl = "/fgadmin/orders/submit"
        self.DeleteAddress = "/fgadmin/address/delete "

    def test_skuList(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000044", "password": "netease123"}
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