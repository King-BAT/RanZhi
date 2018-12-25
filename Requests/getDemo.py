import requests
import json
url1 = "http://study-perf.qa.netease.com/common/skuList?goodsId=1"
url2 = "http://study-perf.qa.netease.com/common/skuList"
paraGoods = {"goodsId": 1}


def sent_get(url, param=None):
    r = requests.get(url, params=param)
    if (param == None):
        print("****************************不带参数的get方法访问skuList"
              "******************************************************************************")
    else:
        print("****************************带参数的get方法访问skuList"
              "******************************************************************************")
    print("url：" + r.url)
    print("status_code：" + str(r.status_code))
    print("编码方式：" + r.encoding)
    print(r.json())


# 重构get请求+格式化输出相应数据
def get(url, param=None):
    r = requests.get(url, params=param)
    result = r.json()
    if (param == None):
        print("****************************不带参数的get方法访问skuList"
              "******************************************************************************")
    else:
        print("****************************带参数的get方法访问skuList"
              "******************************************************************************")
    print("url：" + r.url)
    print("status_code：" + str(r.status_code))
    print("编码方式：" + r.encoding)
    print(result)
    # json.dumps()用于将dict类型的数据转成str
    # indent参数对json数据格式化输出，设置json缩进量
    # dict里的数据是无序的,sort_keys按照key的顺序排序
    print(json.dumps(result, indent=2, sort_keys=True))


# 调用不带参数的get方法访问skuList
# sent_get(url1)
# 调用带参数的get方法访问skuList
# sent_get(url2, paraGoods)
# 格式化输出json
get(url1)