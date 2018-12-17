from commom.BrowserStartUp import startBrowser
from utils.ReadProperties import Read
import time
import os
from commom.Log import Log
logger=Log(logger='ElementFinder').getLog()
u_path=Read().getValue('url')
su_path=Read().getValue("scyurl")
p_path=Read().getValue('png_path')
class ElementFinder(object):
    driver = startBrowser()

    def findElement(self, locator):
        element = ElementFinder().findElementByPrefix(locator)
        return element

    def findElementByPrefix(self, locator):
        target = locator.strip()
        if target.startswith('name='):
            target = target[len('name='):]
            return self.driver.find_element_by_name(target)
        elif target.startswith('class='):
            locator = locator[len('class='):]
            return self.driver.find_element_by_class_name(locator)
        elif target.startswith('id='):
            locator = locator[len('id='):]
            return self.driver.find_element_by_id(locator)
        elif target.startswith('link='):
            locator = locator[len('link='):]
            return self.driver.find_element_by_link_text(locator)
        elif target.startswith('css='):
            locator = locator[len('css='):]
            return self.driver.find_element_by_css_selector(locator)
        elif target.startswith('xpath='):
            locator = locator[len('xpath='):]
            return self.driver.find_element_by_xpath(locator)
        elif target.startswith('tag='):
            locator = locator[len('tag='):]
            return self.driver.find_element_by_tag_name(locator)
        else:
            print(locator + 'can not find element by prefix')
            return None
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


