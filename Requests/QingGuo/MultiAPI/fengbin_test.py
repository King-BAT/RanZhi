import unittest

from core import Common
from core.Sendhttp import SendHttp


class scene(unittest.TestCase):
    def setUp(self):
        self.login_url="/common/fgadmin/login"
        self.addAddress_url="/fgadmin/address/new"
        self.submit_url="/fgadmin/orders/submit"
        self.skulist_url="/common/skuList"
        self.fee_url="/common/getTransportFee"
        self.delete_url="/fgadmin/address/delete"

    #NO1.登录成功-获取商品列表-添加收货地址-提交订单
    def test_Scene1(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000033", "password": "netease123"}
        newaddress = {"id": "",
                      "receiverName": "小飞龙",
                      "cellPhone": "15226541128",
                      "province": "河北省",
                      "city": "石家庄市",
                      "area": "裕华区",
                      "addressDetail": "新校区"}
        submit = {"skuIds": "3",
                  "skuNumbers": "1",
                  "stockIds": "74966313",
                  "receiverName": "小飞龙",
                  "cellPhone": "15226541128",
                  "addressDetail": "新校区",
                  "province": "河北省",
                  "city": "石家庄市",
                  "area": "裕华区",
                  "voiceStatus": 0,
                  "needInvoice": 0,
                  "invoiceHead": "",
                  "transportFee": 6,
                  "logisticsCompanyId": 1,
                  "accessSource": "noSource",
                  "accessDevice": 0}
        login_result=SendHttp().run_http(self.login_url,"POST",user)
        self.assertEqual(login_result['code'],200)
        self.assertEqual(login_result['message'],"success")
        skulist_result=SendHttp().sent_get(self.skulist_url)
        self.assertEqual(skulist_result['message'],"success")
        newaddress_result=SendHttp().sent_post_bycookies(self.addAddress_url,Common.getcookies(user),newaddress)
        self.assertEqual(newaddress_result['code'],200)
        submit_result=SendHttp().sent_post_bycookies(self.submit_url,Common.getcookies(user),submit)
        self.assertEqual(submit_result['message'],"success")

    #NO2.获取商品列表-计算运费-提交订单
    def test_scene2(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000033", "password": "netease123"}
        fee = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "1",
            "tm": "1545038153217"
        }
        submit = {"skuIds": "3",
                  "skuNumbers": "1",
                  "stockIds": "74966313",
                  "receiverName": "小飞龙",
                  "cellPhone": "15226541128",
                  "addressDetail": "新校区",
                  "province": "河北省",
                  "city": "石家庄市",
                  "area": "裕华区",
                  "voiceStatus": 0,
                  "needInvoice": 0,
                  "invoiceHead": "",
                  "transportFee": 6,
                  "logisticsCompanyId": 1,
                  "accessSource": "noSource",
                  "accessDevice": 0}
        skulist_result = SendHttp().sent_get(self.skulist_url)
        self.assertEqual(skulist_result['message'], "success")
        fee_result=SendHttp().sent_get(self.fee_url,fee)
        self.assertEqual(fee_result['code'],200)
        submit_result = SendHttp().sent_post_bycookies(self.submit_url, Common.getcookies(user), submit)
        self.assertEqual(submit_result['message'], "success")

    #NO3.登录成功-获取商品列表-删除地址-添加地址
    def test_scene3(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000033", "password": "netease123"}
        delete_id = {
            "id": "82317363"
        }
        newaddress = {"id": "",
                      "receiverName": "杨",
                      "cellPhone": "15226541128",
                      "province": "河北省",
                      "city": "石家庄市",
                      "area": "裕华区",
                      "addressDetail": "新校区"}
        login_result=SendHttp().run_http(self.login_url,'POST',user)
        self.assertEqual(login_result['message'],"success")
        skulist_result = SendHttp().sent_get(self.skulist_url)
        self.assertEqual(skulist_result['message'], "success")
        delete_result=SendHttp().sent_post_bycookies(self.delete_url,Common.getcookies(user),delete_id)
        self.assertEqual(delete_result['code'],400)
        newaddress_result = SendHttp().sent_post_bycookies(self.addAddress_url, Common.getcookies(user), newaddress)
        self.assertEqual(newaddress_result['code'], 200)

    #NO4.登录成功-查看指定的商品信息-删除指定的地址-
    def test_scene4(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000033", "password": "netease123"}
        skulist={
            "goodsId":1
        }
        delete_id = {
            "id": "82317363"
        }
        login_result = SendHttp().run_http(self.login_url, 'POST', user)
        self.assertEqual(login_result['message'], "success")
        skulist_result=SendHttp().run_http(self.skulist_url,'GET',skulist)
        self.assertEqual(skulist_result['message'], "success")
        delete_result = SendHttp().sent_post_bycookies(self.delete_url, Common.getcookies(user), delete_id)
        self.assertEqual(delete_result['code'], 400)

    #NO5.登录成功-获取指定的商品信息-计算运费-提交订单
    def test_scene5(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000033", "password": "netease123"}
        skulist = {
            "goodsId": 1
        }
        fee = {
            "addressDetail": "河北省_石家庄市_裕华区",
            "id": "1",
            "tm": "1545038153217"
        }
        submit = {"skuIds": "3",
                  "skuNumbers": "1",
                  "stockIds": "74966313",
                  "receiverName": "小飞龙",
                  "cellPhone": "15226541128",
                  "addressDetail": "新校区",
                  "province": "河北省",
                  "city": "石家庄市",
                  "area": "裕华区",
                  "voiceStatus": 0,
                  "needInvoice": 0,
                  "invoiceHead": "",
                  "transportFee": 6,
                  "logisticsCompanyId": 1,
                  "accessSource": "noSource",
                  "accessDevice": 0}
        login_result = SendHttp().run_http(self.login_url, 'POST', user)
        self.assertEqual(login_result['message'], "success")
        skulist_result = SendHttp().run_http(self.skulist_url, 'GET', skulist)
        self.assertEqual(skulist_result['message'], "success")
        fee_result = SendHttp().sent_get(self.fee_url, fee)
        self.assertEqual(fee_result['code'], 200)
        submit_result = SendHttp().sent_post_bycookies(self.submit_url, Common.getcookies(user), submit)
        self.assertEqual(submit_result['message'], "success")