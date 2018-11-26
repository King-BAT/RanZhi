from selenium import webdriver
from utils.ReadProperties import Read
from commom.ScreenShot import screenShot
from commom.Log import Log
import os
log=Log(logger='startBrowser').getLog()
def startBrowser():
    browsername=Read().getValue('browserName')
    url=Read().getValue('url')
    if browsername=='firefox':
        log.info('启动Firefox浏览器')
        driver=webdriver.Firefox()
        log.info('Firefox打开网页')
        driver.get(url)
        return driver
    elif browsername=='IE':
        log.info('启动IE浏览器')
        driver=webdriver.Ie()
        log.info('IE打开网页')
        driver.get(url)
        return driver
    elif browsername=='Chrome':
        log.info('启动Chrome浏览器')
        driver=webdriver.Chrome()
        log.info('Chrome打开网页')
        driver.get(url)
        return driver
