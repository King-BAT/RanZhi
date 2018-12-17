from commom.BrowserStartUp import *
import requests
import json
class RunMain():
    # 设置GET请求的带参数的cookie信息
    def sent_get_by_cookies(self, url, cookies, paramdata=None):
        headers = {"Content-Type": "application/json"}
        response = requests.get(BaseUrl()+url,cookies=getCookies(cookies),data=json.dumps(paramdata),headers=headers)
        return response.json()

    # 设置POST请求的cookie信息
    def sent_post_by_cookies(self, url, paramData, cookies=None):
        headers = {"Content-Type": "application/json"}
        response = requests.post(BaseUrl()+url,data=json.dumps(paramData),cookies=cookies,headers=headers)
        return response.json()

    # 判断请求信息，调用对应的方法
    def run_http(self,url,method,cookies=None,paraData=None):
        if (method == "POST"):
            result = self.sent_post_by_cookies(url,paraData)
        elif (method == "GET"):
            result = self.sent_get_by_cookies(url,paraData,cookies)
        return result

if __name__=="__main__":
    url2 = "common/fgadmin/login"
    user = {"phoneArea": "86",
            "phoneNumber": "20000000000",
            "password": "netease123"}
    run = RunMain()
    result1 = run.run_http(url2, "POST", user)
    print(result1)