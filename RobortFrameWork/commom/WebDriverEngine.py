from selenium import webdriver
from commom.ElementFinder import ElementFinder
import time
import logging
from selenium.webdriver.common.action_chains import ActionChains
from utils.ReadProperties import Read
from commom.BrowserStartUp import startBrowser
from commom.Log import Log
logger=Log(logger='WebDriverEngine').getLog()
u_path=Read().getValue("url")
class WebDriverEngine():
    def getAllWindowTitles(self):
        # driver = webdriver.Firefox()
        driver=startBrowser()
        #获得当前窗口的句柄
        driver.current_window_handle
        #获得所有窗口的句柄
        allhandles=driver.window_handles
        list=[]
        for handle in allhandles:
            driver.switch_to.window(handle)
            list.append(handle.title())
        driver.switch_to.window(allhandles)
        return len(list)
    def enterFrame(self,frameID):#python接受任何类型的参数，不必重载
        driver = startBrowser()
        time.sleep(3)
        driver.switch_to.window(frameID)
        logger.info("Ebtered inframe"+frameID)
    def leaveFrame(self):
        driver = startBrowser()
        driver.switch_to.default_content()
        logger.info("left the iframe")
    def open(self):
        driver = startBrowser()
        driver.get(u_path)
        time.sleep(3)
        logger.info("opened url"+u_path)
    def getTitle(self):
        driver = startBrowser()
        return driver.title()
    def pause(self,_time):
        if _time<=0:
            return
        time.sleep(_time)
    def isTextPresent(self,pattern):
        driver = startBrowser()
        text=driver.page_source()
        #去掉字符串两端的空格，Java使用trim方法
        text=text.strip()
        if pattern in text:
            return True
        else:
            return False
    def typeAndClear(self,type, position,value):
        finder = ElementFinder()
        element=finder.find_element(type, position)
        if finder:
            element.clear()
            element.send_keys(value)
    def type(self,type,position,value):
        finder = ElementFinder()
        element = finder.find_element(type, position)
        if element!=None:
            element.send_keys(value)
        else:
            print("No Find")
    def ischecked(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type, position)
        return element.is_selected()
    def click(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type,position)
        if element!=None:
            element.click()
            time.sleep(3)
    def runJs(self,js):
        driver = startBrowser()
        driver.execute_script(js)
    def clickLonger(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type,position)
        if element!=None:
            WebDriverEngine().runJs('window.scrollTo(0,'+element.location+')')
            element.click()
            time.sleep(3)
    def doubleClick(self,type,position):
        finder = ElementFinder
        element = finder.find_element(type,position)
    def isDisplayed(self,type,position):
         finder = ElementFinder()
         element = finder.find_element(type,position)
         if element!=None:
             print(element.is_displayed())
    def getText(self,type,position):
        finder = ElementFinder()
        return finder.find_element(type,position).text().strip()
    def isElementPresent(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type,position)
        logger.info('e.getMessage')
        if element!=None:
            return True
        else:
            return False
    def getValue(self,type,position):
        finder = ElementFinder()
        return finder.find_element(type,position).get_attribute('value')
    def getUrl(self,locator):
        driver = startBrowser()
        return driver.current_url
    def goBack(self):
        driver = startBrowser()
        driver.back()
    def goForward(self):
        driver = startBrowser()
        driver.forward()
    def getAlert(self):
        driver = startBrowser()
        alert=driver.switch_to.alert
        return alert
    def getSelect(self,type,position):
        finder = ElementFinder()
        inputSelect=finder.find_element(type,position)
        return inputSelect
    def selectByValue(self,locator,value):
        WebDriverEngine.getSelect(locator)
        time.sleep(5)
    def selectByVisibleText(self,locator,value):
        WebDriverEngine.getSelect(locator)
    def selectByIndex(self,locator,index):
        WebDriverEngine.getSelect(locator)
        #以上有问题
    def getAlertTest(self):
        return WebDriverEngine.getAlert().text
    def alertAccept(self):
        WebDriverEngine.getAlert().accept()
    def getHtmlSource(self):
        driver = startBrowser()
        return driver.page_source
    #将鼠标移到element元素上
    def mouseoverElement(self,type,position):
        driver = startBrowser()
        finder = ElementFinder()
        element = finder.find_element(type,position)
        ActionChains(driver).move_to_element(element).perform()
    #切换窗口
    def switchWindow(self,i):
        driver = startBrowser()
        windows=[]
        for handle in driver.window_handles:
            windows.append(handle)
        driver.switch_to.window(windows[i])
    #右键
    def rightClickMouse(self,type,position):
        driver = startBrowser()
        finder = ElementFinder()
        element = finder.find_element(type,position)
        ActionChains(driver).context_click(element).perform()
    #tab键
    def tabType(self,content):
        driver = startBrowser()
        ActionChains(driver).send_keys(content).perform()
    def getWindow(self,i):
        driver = startBrowser()
        windows=[]
        for handle in driver.window_handles:
            windows.append(handle)
        driver.switch_to.window(windows[i])




