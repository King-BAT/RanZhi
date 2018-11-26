from commom.BrowserStartUp import startBrowser
from utils.ReadProperties import Read
import time
import os
from commom.Log import Log
logger=Log(logger='ElementFinder').getLog()
u_path=Read().getValue('url')
p_path=Read().getValue('png_path')
class ElementFinder(object):
    driver = startBrowser()
    def find_element(self, type, position):
        if type == 'xpath':
            element = self.driver.find_element_by_xpath(position)
            return element
        elif type == 'id':
            element = self.driver.find_element_by_id(position)
            return element
        elif type == 'name':
            element = self.driver.find_element_by_name(position)
            return element
        elif type == 'link_text':
            element = self.driver.find_element_by_link_text(position)
            return element
        else:
            print("不支持的类型")

    # 定位多个元素方法
    def find_elements(self, type, position):
        if type == 'xpath':
            element = self.driver.find_elements_by_xpath(position)
            return element
        elif type == 'id':
            element = self.driver.find_elements_by_id(position)
            return element
        elif type == 'name':
            element = self.driver.find_elements_by_name(position)
            return element
        elif type == 'link_text':
            element = self.driver.find_elements_by_link_text(position)
            return element
        else:
            print("不支持的类型")
    # 截屏方法
    def do_screenshot(self):
        pic_time = time.strftime('%Y%m%d%H%M', (time.localtime()))
        pic_name = os.path.dirname(os.path.abspath('.')) + '\\picture\\' + pic_time + '.png'
        self.driver.get_screenshot_as_file(pic_name)
        logger.info('截屏成功')
    # # 判断页面元素是否存在的方法
    def element_is_exists(self,type,value):
       flag=True
       driver=self.driver
       try:
           driver.find_element(type,value)
           return flag
       except:
           flag=False
           return flag
       # element_str=element.text
       # if len(element_str) ==0:
       #     self.do_screenshot()
    # 关闭浏览器的方法
    def close(self):
        self.driver.quit()

