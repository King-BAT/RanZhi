import unittest
from core.Sendhttp import SendHttp
from core import Common


class List(unittest.TestCase):
    def setUp(self):
        self.url = "/fgadmin/address/list"

    # 成功获取地址信息
    def test_address_by_Login(self):
        user = {"phoneArea": "86", "phoneNumber": "20000000000", "password": "netease123"}
        SendHttp().sent_get_bycookies(self.url, Common.getcookies(user))

    # 未登录获取地址信息失败
    def test_listFail_by_NoLogin(self):
        SendHttp().run_http(self.url, "GET")

    # 登录时使用错误的用户名获取地址信息失败
    def test_listFail_by_WrongUser(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000000", "password": "netease123"}
        SendHttp().sent_get_bycookies(self.url, Common.getcookies(user))

    # 登录时使用错误的密码获取地址信息失败
    def test_listFail_by_WrongPwd(self):
        user = {"phoneArea": "86", "phoneNumber": "20000000000", "password": "netease1234"}
        SendHttp().sent_get_bycookies(self.url, Common.getcookies(user))
