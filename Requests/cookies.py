import requests
import json
url = "http://study-perf.qa.netease.com/common/fgadmin/login"
# 字典
user = {"phoneArea": "86",
        "phoneNumber": "2000000000",
        "password": "netease123"}
header = {"Content-Type": "application/json"}
res = requests.post(url, data=json.dumps(user), headers=header)
cookies = res.cookies
print(cookies)