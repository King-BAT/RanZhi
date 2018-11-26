from commom.WebDriverEngine import WebDriverEngine
from commom.ElementFinder import ElementFinder
from time import sleep
from selenium.webdriver.common.by import By
from utils.circleSendEmail import *
# from dataprovider.TxtDataProvider import txtRead
from dataprovider.ExcelDataProvider import excelRead
# from dataprovider.MySqlDataProvider import MySQl
class test():
    def test_base(self):
        driver = WebDriverEngine()
        browser = ElementFinder()
        # 使用Txt    i.split(',')[0]
        # result=txtRead().get_data()
        # 使用excel i[0]
        result1=excelRead().get_data()
        # 使用数据库的话，就要先调用初始化方法
        # db=MySQl(host="localhost",port=3306,dbName="mymovie",username="admin",password="123456",charset="utf8")
        # result2=db.getData("select username,password from mm_user")
        for i in result1:
            driver.typeAndClear("xpath", "//input[@name='username']", i[0])
            sleep(1)
            driver.typeAndClear("xpath", "//input[@name='password']", i[1])
            sleep(1)
            driver.click("xpath", "//input[@type='submit']")
            sleep(1)
            element=browser.element_is_exists(By.XPATH, "//a[contains(.,'退出')]")
            if element:
                driver.click("xpath","//a[contains(.,'退出')]")
            else:
                browser.do_screenshot()
        browser.close()
        t_send()
if __name__=='__main__':
    test().test_base()