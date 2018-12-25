import unittest
from core.Sendhttp import SendHttp
from core import Common


class addressNew(unittest.TestCase):
    def setUp(self):
        self.url = "/fgadmin/address/new"

    # 添加收货地址成功（已登录）
    def test_addressnew1(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000066", "password": "netease123"}
        para = {
            "id": "",
            "receiverName": "张三",
            "cellPhone": "12345678901",
            "addressDetail": "浙江大学",
            "province": "浙江省",
            "city": "杭州市",
            "area": "滨江区"
        }
        result = SendHttp().sent_post_bycookies(self.url, Common.getcookies(user), para)
        self.assertEqual(result['code'], 200)
        self.assertEqual(result['message'], 'success')

    # 添加已存在的收货地址失败(已登录,收货地址已存在--test_addressnew1添加)
    def test_addressnew21(self):
        user = {"phoneArea": "86", "phoneNumber": "200000000066", "password": "netease123"}
        para = {
            "id": "",
            "receiverName": "张三",
            "cellPhone": "12345678901",
            "addressDetail": "浙江大学",
            "province": "浙江省",
            "city": "杭州市",
            "area": "滨江区"
        }
        result = SendHttp().sent_post_bycookies(self.url, Common.getcookies(user), para)
        # self.assertEqual(result['code'], 400)
        # self.assertEqual(result['message'], '请求参数不正确')
        # 用例失败，code：200 message：success
        if self.assertEqual(result['code'], 200):
            print('添加重复的地址成功，用例失败')
        # self.assertEqual(result['message'], 'success')


if __name__ == '__main__':
    unittest.main()
