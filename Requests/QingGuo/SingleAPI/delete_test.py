import unittest
from core.Sendhttp import SendHttp
from core import Common


class deleteAddress(unittest.TestCase):
    def setUp(self):
        self.listurl = "/fgadmin/address/list?tm=1545495924820"
        self.delurl = "/fgadmin/address/delete"
        self.addurl = "/fgadmin/address/new"
        self.user = {"phoneArea": "86", "phoneNumber": "200000000077", "password": "netease123"}

    def getIdList(self):
        cookie = Common.getcookies(self.user)
        list_result = SendHttp().sent_get_bycookies(self.listurl, cookie)
        id_num = int(list_result['result']['total'])
        id_list = list(range(id_num))
        for index in range(len(id_list)):
            id_list[index] = list_result['result']['list'][index]['id']
        return id_list

    # 成功删除
    def test_success_del(self):
        ID_LIST = self.getIdList()
        cookie = Common.getcookies(self.user)
        para = {"id": ID_LIST[0]}
        del_result = SendHttp().send_post_bycookies(self.delurl, para, cookie)
        self.assertEqual(del_result['code'], 200)

    # 删除失败，无此id收货地址
    def test_fail_del01(self):
        cookie = Common.getcookies(self.user)
        para = {"id": 1}
        del_result = SendHttp().send_post_bycookies(self.delurl, para, cookie)
        self.assertEqual(del_result['code'], 400)

    # 删除失败，未登录
    def test_fail_del02(self):
        ID_LIST = self.getIdList()
        para = {"id": ID_LIST[0]}
        del_result = SendHttp().send_post(self.delurl, para)
        self.assertEqual(del_result['code'], 403)

    # 删除失败，id为空
    def test_fail_del03(self):
        cookie = Common.getcookies(self.user)
        para = {"id": ""}
        del_result = SendHttp().send_post_bycookies(self.delurl, para, cookie)
        self.assertEqual(del_result['code'], 400)
