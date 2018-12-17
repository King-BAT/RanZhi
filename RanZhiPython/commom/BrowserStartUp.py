from selenium import webdriver
from utils.ReadProperties import Read
from commom.Log import Log
import requests
import json,time
from datetime import datetime
log=Log(logger='startBrowser').getLog()
def startBrowser():
    browsername=Read().getValue('browserName')
    url=Read().getValue('url')
    if browsername=='firefox':
        log.info('启动Firefox浏览器')
        driver=webdriver.Firefox()
        driver.implicitly_wait(7)
        log.info('Firefox打开网页')
        driver.get(url)
        # return driver
    elif browsername=='IE':
        log.info('启动IE浏览器')
        driver=webdriver.Ie()
        driver.implicitly_wait(7)
        log.info('IE打开网页')
        driver.get(url)
        # return driver
    elif browsername=='Chrome':
        log.info('启动Chrome浏览器')
        driver=webdriver.Chrome()
        driver.implicitly_wait(7)
        log.info('Chrome打开网页')
        driver.get(url)
    return driver
def BaseUrl():
    base_path = Read().getValue('BaseUrl')
    return base_path
# 获得缓存信息
def getCookies(cookie):
    url = BaseUrl() + "common/fgadmin/login"
    header = {"Content-Type": "application/json"}
    response = requests.post(url, data=json.dumps(cookie), headers=header)
    return response.cookies
def getCurrentTime():
    format = "%Y%m%d%H%M%S"
    return time.strftime(format)
def timeDiff(starttime, endtime):
    format = "%Y%m%d%H%M%S"
    return datetime.strptime(endtime, format) - datetime.strptime(starttime, format)
