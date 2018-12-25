import unittest

from core.Sendhttp import SendHttp


class skulist(unittest.TestCase):
    def setUp(self):
        self.url="/common/skuList"
    def test_skulist(self):
        result=SendHttp().sent_get(self.url)
        print(result)

    # goodsId为Int类型
    def test_skulistById(self):

        skulist = {
            "goodsId": 1
        }
        result=SendHttp().run_http(self.url,'GET',skulist)
        print(result)
        self.assertEqual(result['message'],"success")

    # goodsId为Int类型
    def test_skulistById4(self):
        skulist = {
            "goodsId": 1000000000000000000000000
        }
        result = SendHttp().run_http(self.url, 'GET', skulist)
        print(result)
        self.assertEqual(result['code'], 400)
    #goodsId为String类型
    def test_skulistById1(self):
        skulist = {
            "goodsId":"\"1\""
        }
        result = SendHttp().run_http(self.url, 'GET', skulist)
        print(result)
        self.assertEqual(result['code'],400)

    # goodsId为double类型
    def test_skulistById2(self):
        skulist = {
            "goodsId": "\"1.0\""
        }
        result = SendHttp().run_http(self.url, 'GET', skulist)
        print(result)
        self.assertEqual(result['code'], 400)
     # goodsId为空值
    def test_skulistById3(self):
        skulist = {
            "goodsId":"\"null\""
        }
        result = SendHttp().run_http(self.url, 'GET', skulist)
        print(result)
        self.assertEqual(result['code'], 400)

