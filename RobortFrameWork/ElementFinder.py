from selenium import webdriver
import logging
from utils.ReadProperties import RP
from core.BaseTest import Base1
import time
from selenium.webdriver.common.action_chains import ActionChains

#定位，查找元素
class ElemFinder(object):
    #调用basetest类中的方法对浏览器进行判断
    driver = Base1().open_url_use_browser(RP().getValue('firefox_path'),RP().getValue('base_url2'))
    driver.get(RP().getValue('base_url2'))
    def findElement(self,locator):
        element=ElemFinder().findElementByPrefix(locator)
        return element
    def findElementByPrefix(self,locator):
        target=locator.strip()
        if target.startswith('name='):
            target=target[len('name='):]
            return self.driver.find_element_by_name(target)
        elif target.startswith('class='):
            locator = locator[len('class='):]
            return self.driver.find_element_by_class_name(locator)
        elif target.startswith('id='):
            locator=locator[len('id='):]
            return self.driver.find_element_by_id(locator)
        elif target.startswith('link='):
            locator=locator[len('link='):]
            return self.driver.find_element_by_link_text(locator)
        elif target.startswith('css='):
            locator=locator[len('css='):]
            return self.driver.find_element_by_css_selector(locator)
        elif target.startswith('xpath='):
            locator=locator[len('xpath='):]
            return self.driver.find_element_by_xpath(locator)
        elif target.startswith('tag='):
            locator=locator[len('tag='):]
            return self.driver.find_element_by_tag_name(locator)
        else:
            logging.info(locator+'can not find element by prefix')
            return None
    #截屏
    def do_screenshot(self,url,filaname):
        self.driver.set_window_size(1200,900)
        self.driver.get(str(url))
        #小窗口下截取全屏，借助JS实现滚动条。
        #execute_script()可以直接执行js的脚本
        self.driver.execute_script("""    
        (function(){
            var y=0
            var step=100
            window.scroll(0,0);
        function f(){
            if(y<document.body.scrollHeight){
                y += step;
                window.scroll(0,y);
                setTimeout(f,50);
            }
            else{
                window.scroll(0,0);
                document.title += "scroll-done";
            }
        }
        setTimeout(f,1000);
        })();
        """)
        self.driver.save_screenshot(filaname)
    #进入frame
    def enterFrame(self,frameID):#python接受任何类型的参数，不必重载
        # time.sleep(3)
        ef=self.driver.find_element_by_id(frameID)
        self.driver.switch_to.frame(ef)
        # logging.info("Ebtered inframe"+ef)
    #跳出frame,返回最外层iframe
    def leaveFrame(self):
        self.driver.switch_to.default_content()
        logging.info("left the iframe")
    #刷新页面
    def refresh(self):
        self.driver.refresh()
    #关闭浏览器
    def q(self):
        self.driver.quit()
    def getAlert(self):
        alert=self.driver.switch_to_alert().text
        print(alert)

    def getAllWindowTitles(self):
        #获得当前窗口的句柄
        nowhandle=self.driver.current_window_handle
        #获得所有窗口的句柄
        allhandles=self.driver.window_handles
        list=[]
        for handle in allhandles:
            self.driver.switch_to.window(handle)
            list.append(handle.title())
        self.driver.switch_to.window(allhandles)
        return len(list)

    #打开url
    def open(self,url):
        self.driver.get(url)

    def getTitle(self):
        return self.driver.title()

    # 判断页面中是否存在特定字符
    def isTextPresent(self, pattern):
        text = self.driver.page_source()
        # 去掉字符串两端的空格，Java使用trim方法
        text = text.strip()
        if pattern in text:
            return True
        else:
            return False

    def getUrl(self, locator):
        return self.driver.current_url
        # 返回上一级页面

    def goBack(self):
        self.driver.back()
    def goForward(self):
        self.driver.forward()
    def getAlert(self):
        alert = self.driver.switch_to.alert
        return alert
    def getHtmlSource(self):
        return self.driver.page_source
    def runJs(self,js):
        self.driver.execute_script(js)
    #将鼠标移到element元素上
    def mouseoverElement(self,locator):
        finder = ElemFinder()
        element = finder.findElement(locator)
        ActionChains(self.driver).move_to_element(element).perform()
    #切换窗口
    def switchWindow(self,i):
        windows=[]
        for handle in self.driver.window_handles:
            windows.append(handle)
            self.driver.switch_to.window(windows[i])
    #tab键
    def tabType(self,content):
        ActionChains(self.driver).send_keys(content).perform()
    def getWindow(self,i):
        windows=[]
        for handle in self.driver.window_handles:
            windows.append(handle)
        self.driver.switch_to.window(windows[i])
    def ifContains(self,content):
        if content in self.driver.page_source:
            return True