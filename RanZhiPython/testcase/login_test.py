from commom.WebDriverEngine import WebDriverEngine
from time import sleep
from dataprovider.NsDataProvider import JudgeFile
from utils.ReadProperties import Read
class Login(object):
    def login(self):
        t_path=Read().getValue('excel_path')
        result=JudgeFile().judge(t_path)
        for i in result:
            driver = WebDriverEngine()
            driver.typeAndClear('xpath', "//input[@name='account']", i[0])
            sleep(1)
            driver.typeAndClear('xpath', "//input[@name='password']", i[1])
            sleep(1)
            driver.click('xpath', "//button[@type='submit']")
            sleep(1)
            element = driver.element_is_exists('xpath', "//button[contains(.,'确认')]")
            if element:
                driver.do_screenshot()
                driver.click('xpath', "//button[contains(.,'确认')]")
if __name__=='__main__':
    Login().login()