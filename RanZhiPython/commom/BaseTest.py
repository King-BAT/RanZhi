import unittest
from selenium import webdriver
import webbrowser as web#引入webbrowser包起别名为web
import os
import time
import random
#from utils import ReadProperties as rp
from utils.ReadProperties import Read
from threading import Thread

class Base1(object):
    #判断选择浏览器
     def open_url_use_browser(self,browser_path,url):
        if browser_path ==Read().getValue('firefox_path'):
            return webdriver.Firefox()
        elif browser_path == Read().getValue('chrome_path'):
            return webdriver.Chrome()
        elif browser_path==Read().getValue('ie_path'):
            return webdriver.Ie()
    #多线程调用浏览器
     def threads(self):
        data=[Read().getValue('firefox_path'),Read().getValue('chrome_path'),Read().getValue('ie_path')]
        threads=[]
        for i in data:
            t = Thread(target=Base1.open_url_use_browser, args=(i,Read().getValue('base_url')))
            threads.append(t)
        for thr in threads:
            thr.start()

    #最大化浏览器窗口
     def do_max(self):
        self.driver.maximize_window()
    #关闭浏览器
     def do_quit(self):
        driver = webdriver.Firefox()
        self.driver.quit()

# #单例模式创建一个实例,在监听器中使用
class Base2(object):
    _single = None
    def __new__(cls, *args, **kwargs):
        if not cls._single:
            cls._single = object.__new__(cls)
        return cls._single


##########################################################################################################

# #注册浏览器
# web.register('firefox', web.Mozilla('mozilla'), web.BackgroundBrowser(browser_path))
#在一个新窗口打开浏览器
# web.get('firefox').driver.open_new_tab(RP().getValue('base_url'))
# print( 'use_firefox_open_url open url ending ....')