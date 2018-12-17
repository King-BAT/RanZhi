from selenium import webdriver
from commom.BaseTest import Base1
from utils.ReadProperties import Read
from commom.WebDriverEngine import WebDriverEngine
from commom.ElementFinder import ElementFinder
import time
from selenium.webdriver.common.keys import Keys
import unittest
from utils.HTMLTestRunnerEN import HTMLTestRunner

#测试类，调用方法传参
class Test(unittest.TestCase):
    # 登录成功√
    def test_1_loginSuccess(self):
        WebDriverEngine().type_scy("id=account","admin")
        WebDriverEngine().type_scy("id=password","123456")
        WebDriverEngine().click_scy("id=submit")
        time.sleep(5)
    #登录失败
    # def loginFail(self):
    #     WebDriverEngine().type_scy("id=account","admin")
    #     WebDriverEngine().type_scy("id=password","1234")
    #     WebDriverEngine().click_scy("id=submit")
    #     ElementFinder().q()

    #9843联系人编辑分组√
    def test_2_editCall(self):
        WebDriverEngine().click_scy("xpath=//a[contains(.,'联系人')]")
        time.sleep(3)
        # WebDriverEngine().enterFrame("iframe-dashboard")
        WebDriverEngine().click_scy("xpath=/html/body/div/div/table/tbody/tr/td[4]/a[1]")
        time.sleep(3)
        WebDriverEngine().typeAndClear_scy("xpath=//input[@autocomplete='on']","kkk")
        WebDriverEngine().click_scy("xpath=//button[contains(.,'保存')]")
        # ElementFinder().refresh()
        time.sleep(5)
        WebDriverEngine().enterFrame("iframe-dashboard")
        time.sleep(3)

    # 项目挂起√
    def test_3_protect(self):
        WebDriverEngine().click_scy("xpath=//a[contains(.,'项目')]")
        time.sleep(3)
        WebDriverEngine().click_scy("xpath=/html/body/div/div/table/tbody/tr[1]/td[9]/a[4]")
        WebDriverEngine().click_scy("xpath=/html/body/div[2]/div/div/div[2]/button[1]")
        WebDriverEngine().click_scy("xpath=/html/body/div[2]/div/div/div[2]/button")


    # 9836报销情况√
    def test_4_money(self):
        WebDriverEngine().click_scy("xpath=//a[contains(.,'审批')]")
        time.sleep(3)
        WebDriverEngine().click_scy("xpath=/html/body/nav[2]/ul/li[7]/a")
        time.sleep(3)
        WebDriverEngine().leaveFrame()

    # 9834桌面小图标功能√
    def test_5_smallTable(self):
        WebDriverEngine().click_scy("xpath=//button[@id='showDesk']")


def t_send():
    # 所要执行的测试用例所在的位置
    test_dir = Read().getValue('test_dir')
    # 测试报告所在的路径
    test_report = Read().getValue('test_report')
    # 查找想要执行的文件
    discover = unittest.defaultTestLoader.discover(test_dir, pattern='ranzhi_test.py')
    # 使用HTMLTestRunner来生成testRunner，生成html测试报告
    now_time = time.strftime("%Y%m%d%H%M%S")
    file_name = test_report + '\\' + now_time + 'result.html'
    fp = open(file_name, 'wb')
    runner = HTMLTestRunner(stream=fp, title="测试报告", description="运行环境:firefox")
    runner.run(discover)
    fp.close()



if __name__=='__main__':
    t_send()
