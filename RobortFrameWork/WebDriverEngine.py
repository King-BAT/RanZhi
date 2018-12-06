from selenium import webdriver
from core.ElementFinder import ElemFinder
import time
import logging
from selenium.webdriver.common.action_chains import ActionChains
import select

class WebDriverEngine(object):
    # 定位元素传参
    def type(self, locator, value):
        finder = ElemFinder()
        element = finder.findElement(locator)
        if element != None:
            element.send_keys(value)
    # 点击
    def click(self, locator):
        finder = ElemFinder()
        element = finder.findElement(locator)
        if element != None:
            element.click()
            time.sleep(3)
    def pause(self,_time):
        if _time<=0:
            return None
        time.sleep(_time)

    def typeAndClear(self,locator,value):
        finder = ElemFinder()
        element=finder.findElement(locator)
        if element != None:
            element.clear()
            element.send_keys(value)
    #选中复选框
    def ischecked(self,locator):
        finder = ElemFinder()
        element = finder.findElement(locator)
        return element.is_selected()
    def clickLonger(self,locator):
        finder = ElemFinder()
        element = finder.findElement(locator)
        if element!=None:
            ElemFinder().runJs('window.scrollTo(0,'+element.location+')')
            element.click()
            time.sleep(3)
    def doubleClick(self,locator):
        finder = ElemFinder()
        element = finder.findElement(locator)
        # ActionChains().double_click(element).perform()

    def isDisplayed(self,locator):
         finder = ElemFinder()
         element = finder.findElement(locator)
         if element!=None:
             print(element.is_displayed())
    #获取文本
    def getText(self,locator):
        finder = ElemFinder()
        return finder.findElement(locator).text.strip()
    #判断元素是否存在
    def isElementPresent(self,locator):
        finder = ElemFinder()
        try:
            element = finder.findElement(locator)
        except:
            logging.info('e.getMessage')
        if element!=None:
            return True
        else:
            return False
    #获取值
    def getValue(self,locator):
        finder = ElemFinder()
        return finder.findElement(locator).get_attribute('value')
    def getSelect(self,locator):
        finder = ElemFinder()
        inputSelect=select().select(finder.findElement(locator))
        # inputSelect=select(finder.findElement(locator))
        return inputSelect
    #通过要选择的值选择选项
    def selectByValue(self,locator,value):
        finder=ElemFinder()
        select(finder.findElement(locator)).select_by_value(value)
        time.sleep(5)
    # 通过可见文本的值选择选项
    def selectByVisibleText(self,locator,value):
        finder = ElemFinder()
        select(finder.findElement(locator)).select_by_visible_text(value)
        time.sleep(5)
    # 通过可选的位置，选择选项
    def selectByIndex(self,locator,index):
        finder = ElemFinder()
        # select(finder.findElement(locator)).select_by_index(index)
        finder.findElement(locator).select_by_index(index)
        time.sleep(5)
    def getAlertTest(self):
        return WebDriverEngine().getAlert().text
    #接收警告框
    def alertAccept(self):
        return WebDriverEngine().getAlert().accept()
    #右键
    def rightClickMouse(self,locator):
        finder = ElemFinder()
        element = finder.findElement(locator)
        ActionChains(self.driver).context_click(element).perform()



#**********************************************************************************
    # def selectByValue(self,locator,value):
    #     WebDriverEngine().getSelect(locator)
    #     time.sleep(5)
    # def selectByVisibleText(self,locator,value):
    #     WebDriverEngine().getSelect(locator)
    # def selectByIndex(self,locator,index):
    #     WebDriverEngine().getSelect(locator)