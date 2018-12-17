from commom.ElementFinder import ElementFinder
import time
from selenium.webdriver.common.action_chains import ActionChains
from utils.ReadProperties import Read
from commom.BrowserStartUp import startBrowser
from commom.Log import Log
from selenium.webdriver.support.select import Select
import os
from selenium.webdriver.common.keys import Keys
logger=Log(logger='WebDriverEngine').getLog()
u_path=Read().getValue("url")
class WebDriverEngine():
    # 定位元素传参
    def type_scy(self, locator, value):
        finder = ElementFinder()
        element = finder.findElement(locator)
        if element != None:
            element.send_keys(value)

    # 点击
    def click_scy(self, locator):
        finder = ElementFinder()
        element = finder.findElement(locator)
        if element != None:
            element.click()
            time.sleep(3)
    # 判断页面元素是否存在的方法
    def element_is_exists(self,type,value):
       flag=True
       driver=ElementFinder().driver
       try:
           driver.find_element(type,value)
           return flag
       except:
           flag=False
           return flag
    # 截屏方法
    def do_screenshot(self):
       driver = ElementFinder().driver
       pic_time = time.strftime('%Y%m%d%H%M', (time.localtime()))
       pic_name = os.path.dirname(os.path.abspath('.')) + '\\picture\\' + pic_time + '.png'
       driver.get_screenshot_as_file(pic_name)
       logger.info('截屏成功')
    # 获得当前窗口的标题
    def getTitle(self):
        driver=ElementFinder().driver
        return (driver.title)
    # 获得所有的句柄
    def getAllWindowTitles(self):
        driver = ElementFinder().driver
        #获得当前窗口的句柄
        driver.current_window_handle
        #获得所有窗口的句柄
        allhandles=driver.window_handles
        list=[]
        for handle in allhandles:
            driver.switch_to.window(handle)
            list.append(handle.title())
            driver.switch_to.window(allhandles)
        return list
    # 进入富文本框
    def enterFrame(self,frameID):#python接受任何类型的参数，不必重载
        driver = ElementFinder().driver
        time.sleep(1.5)
        driver.switch_to.frame(frameID)
        logger.info("Ebtered inframe"+frameID)
    # 跳到最外层的页面
    def leaveFrame(self):
        driver = ElementFinder().driver
        driver.switch_to.default_content()
        logger.info("left the iframe")
    # 判断是否存在文本
    def isTextPresent(self,str):
        driver = ElementFinder().driver
        text=driver.page_source
        #去掉字符串两端的空格，Java使用trim方法
        content=text.strip()
        if str in content:
            return True
        else:
            return False
    # 先清空再输入元素
    def typeAndClear(self,type, position,value):
        finder = ElementFinder()
        element=finder.find_element(type, position)
        if finder:
            element.clear()
            element.send_keys(value)

    def typeAndClear_scy(self, locator, value):
        finder = ElementFinder()
        element = finder.findElement(locator)
        if element != None:
            element.clear()
            element.send_keys(value)
    # 输入元素
    def type(self,type,position,value):
        finder = ElementFinder()
        element = finder.find_element(type, position)
        if element!=None:
            element.send_keys(value)
        else:
            print("No Find")
    # 返回的是布尔类型的，用来判断单选或者多选控件是否被选中或是否选择一个默认的option；
    def ischecked(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type, position)
        return element.is_selected()
    # 鼠标单击事件
    def click(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type,position)
        if element!=None:
            element.click()
            time.sleep(1.5)
    # 执行JS文件
    def runJs(self,js):
        driver = ElementFinder().driver
        driver.execute_script(js)
    # 鼠标长按
    def ClickandHold(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type,position)
        if element!=None:
            # WebDriverEngine().runJs('window.scrollTo(0,'+element.location+')')
            # element.click()
            ActionChains(driver=ElementFinder().driver).click_and_hold(element)
            time.sleep(3)
    # 右键单击
    def doubleClick(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type,position)
        if element!=None:
            ActionChains(ElementFinder().driver).double_click(element).perform()
            time.sleep(1.5)
    # 判断元素是否显示
    def isDisplayed(self,type,position):
         finder = ElementFinder()
         element = finder.find_element(type,position)
         if element!=None:
            return element.is_displayed()
    # 获取文本元素
    def getText(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type, position).text.strip()
        print(element)
        return element
    #获取content-desc属性，如果content-desc属性为空，那么获取的就是text属性，不为空获取的才是content-desc属性
    def getValue(self,type,position):
        finder = ElementFinder()
        return finder.find_element(type,position).get_attribute('value')
    # 获取打开网页的网址
    def getUrl(self,locator):
        driver = ElementFinder().driver
        return driver.current_url
    # 后退一个网页
    def goBack(self):
        driver = ElementFinder().driver
        driver.back()
    # 前进一个网页
    def goForward(self):
        driver = ElementFinder().driver
        driver.forward()
    # 获取提醒框
    def getAlert(self):
        driver = ElementFinder().driver
        alert=driver.switch_to.alert
        return alert
    # 获取提醒框的内容
    def getAlertText(self):
        return self.getAlert().text
    # 点击提醒框的确认按钮
    def alertAccept(self):
        self.getAlert().accept()

    # 获取下拉框的元素
    def getSelect(self,type,position,value=None):
        finder = ElementFinder()
        element=finder.find_element(type,position)
        select=Select(element)
        return select
    # 根据下拉框的值来获取元素
    def selectByValue(self,type,position,value):
        element=self.getSelect(type,position,value)
        return element
    # 根据序列号获取元素
    def selectByIndex(self,type,position,index):
        element=self.getSelect(type,position,index)
        return element
    # 根据可见文本来获取元素
    def selectByVisibleText(self,type, position, content):
       element=self.getSelect( type, position, content)
       return element
    # 获取页面的源代码
    def getHtmlSource(self):
        driver = ElementFinder().driver
        return driver.page_source
    #将鼠标移到element元素上
    def mouseoverElement(self,type,position):
        finder = ElementFinder()
        element = finder.find_element(type,position)
        ActionChains(ElementFinder().driver).move_to_element(element).perform()
    #切换窗口
    def switchWindow(self,i):
        driver = ElementFinder().driver
        windows=[]
        for handle in driver.window_handles:
            windows.append(handle)
        driver.switch_to.window(windows[i])
    #右键
    def rightClickMouse(self,type,position):
        driver = ElementFinder().driver
        finder = ElementFinder()
        element = finder.find_element(type,position)
        ActionChains(driver).context_click(element).perform()
    #tab键
    def tabType(self,type,position):
        element=ElementFinder().find_element(type,position)
        element.send_keys(Keys.TAB)
    # Enter键
    def enterType(self,type, position):
        element = ElementFinder().find_element(type, position)
        element.send_keys(Keys.ENTER)
    #退格键
    def backspace(self,type, position):
        element = ElementFinder().find_element(type, position)
        element.send_keys(Keys.BACK_SPACE)
    def getWindow(self,i):
        driver = ElementFinder().driver
        windows=[]
        for handle in driver.window_handles:
            windows.append(handle)
        driver.switch_to.window(windows[i])
    # 刷新页面
    def refresh(self):
        driver = ElementFinder().driver
        driver.refresh()
    # 关闭浏览器的方法
    def close(self):
        driver = ElementFinder().driver
        driver.close()




