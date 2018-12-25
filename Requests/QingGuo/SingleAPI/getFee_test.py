import unittest

from core import Common
from core.Sendhttp import SendHttp


class getFee(unittest.TestCase):
    def setUp(self):
        self.url = "/common/getTransportFee"
    # 获取运费成功
    def test_fee(self):
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "1",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 200)
    # 缺少参数id
    def test_fee1(self):
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 400)

    # 缺少参数addressDetail
    def test_fee3(self):
        address = {
            "id": "1",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 200)

    # 参数id得类型为string
    def test_fee4(self):
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "\"1\"",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 400)

    # 参数id得类型为空字符串
    def test_fee5(self):
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 400)

    # 参数id为None
    def test_fee6(self):
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": None,
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 400)

    # addressDetail参数为空字符串
    def test_fee7(self):
        address = {
            "addressDetail": "",
            "id": "1",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 200)

    # addressDetail参数为None
    def test_fee8(self):
        address = {
            "addressDetail": None,
            "id": "1",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 200)

    # 参数id不是默认值
    def test_fee9(self):
        address = {
            "addressDetail": "浙江省_杭州市_滨江区",
            "id": "8",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 200)

    # addressDetail参数类型为int
    def test_fee10(self):
        address = {
            "addressDetail": "1",
            "id": "1",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 200)

    # 缺少参数id和参数addressDetail
    def test_fee11(self):
        address = {
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 400)

    # 参数id为string类型，参数addressDetail为int类型
    def test_fee12(self):
        address = {
            "addressDetail": "1",
            "id": "\"1\"",
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 400)

    # 参数id和addressDetail均为None
    def test_fee13(self):
        address = {
            "addressDetail": None,
            "id": None,
            "tm": "1545038153217"
        }
        result = SendHttp().sent_get(self.url, address)
        self.assertEqual(result['code'], 400)
