import requests
import json
from . import Common


class SendHttp:
    def sent_get(self, url, paraData=None):
        res = requests.get(Common.baseUrl() + url, params=paraData)
        result = res.json()
        print(json.dumps(result, indent=2, sort_keys=True, ensure_ascii=False))
        return result

    def sent_get_bycookies(self, url, cookies,params=None):
        res = requests.get(Common.baseUrl() + url, params=params,cookies=cookies)
        result = res.json()
        print(json.dumps(result, indent=2, sort_keys=True, ensure_ascii=False))
        return res.json()

    def send_post(self, url, paramdata):
        header = {"Content-Type": "application/json;charset=UTF-8"}
        res = requests.post(Common.baseUrl() + url, data=json.dumps(paramdata), headers=header)
        result = res.json()
        print(json.dumps(result, indent=2, sort_keys=True, ensure_ascii=False))
        return result

    def sent_post_bycookies(self,url,cookies,paramdata):
        header = {"Content-Type": "application/json;charset=UTF-8"}
        res = requests.post(Common.baseUrl() + url, data=json.dumps(paramdata), headers=header,cookies=cookies)
        result = res.json()
        print(json.dumps(result, indent=2, sort_keys=True, ensure_ascii=False))
        return result

    def send_post_bycookies(self, url, paramdata, cookies):
        header = {"Content-Type": "application/json;charset=UTF-8"}
        res = requests.post(Common.baseUrl() + url, data=json.dumps(paramdata), cookies=cookies,headers=header)
        result = res.json()
        print(json.dumps(result, indent=2, sort_keys=True, ensure_ascii=False))
        return result

    def run_http(self, url, method, paramdata=None):

        if (method == 'POST'):
            result = self.send_post(url, paramdata)
        elif (method == 'GET'):
            result = self.sent_get(url, paramdata)
        return result
