import requests
import json

url = 'http://study-perf.qa.netease.com/common/fgadmin/login'
# 字典
user = {"phoneArea": "86",
        "phoneNumber": "20000000000",
        "password": "netease1231"}
header = {"Content-Type": "application/json"}
res = requests.post(url, data=json.dumps(user), headers=header)
print(res.json())


# 重构post请求
def send_post(url, paramdata):
    header = {"Content-Type": "application/json;charset=UTF-8"}
    res = requests.post(url, data=json.dumps(paramdata), headers=header).json()
    return json.dumps(res, indent=2, sort_keys=True, ensure_ascii=False)


result = send_post(url, user)
print(result)
