import unittest
from commom.WebDriverEngine  import WebDriverEngine
from time import sleep
import time
class MoneyBrowseTest(unittest.TestCase):
    # 现今记账界面的相关填写信息的操作
    # 先登录
    @classmethod
    def setUpClass(self):
        self.driver = WebDriverEngine()
        self.driver.typeAndClear('xpath', "//input[@name='account']", "xushiwei")
        sleep(1)
        self.driver.typeAndClear('xpath', "//input[@name='password']", "975260")
        sleep(1)
        self.driver.click('xpath', "//button[@type='submit']")
        sleep(1)
        # 判断登录是否成功
        element = self.driver.element_is_exists('xpath', "//button[contains(.,'确认')]")
        if element:
            self.driver.do_screenshot()
            self.driver.click('xpath', "//button[contains(.,'确认')]")
    # 验证账号板块    True
    def test_browse(self):
        self.driver.leaveFrame()
        # 进入到现金记账
        self.driver.click('id',"s-menu-5")
        sleep(1)
        self.driver.enterFrame("iframe-5")
        sleep(2)
        # 点击账户
        self.driver.click('xpath',"//a[contains(.,'账户')]")
        sleep(1)
        # 点击特定区块的编辑
        self.driver.click('xpath',"//a[contains(@href,'/ranzhi/www/cash/index.php?m=depositor&f=edit&depositorID=5')]")
        sleep(1)
        #修改账号的名称
        self.driver.typeAndClear('xpath',"//input[@id='abbr']","xswdee")
        sleep(1)
        self.driver.click('xpath',"//button[@type='submit']")
        sleep(1)
    # 现金记账的对账  True
    def test_check(self):
        self.driver.leaveFrame()
        # 进入到现金记账
        self.driver.click('id', "s-menu-5")
        sleep(1)
        self.driver.enterFrame("iframe-5")
        sleep(2)
        self.driver.click('xpath',"//a[contains(.,'对账')]")
        sleep(1)
        # self.driver.typeAndClear(By.ID,"depositor_chosen","xswdee")
        self.driver.click('xpath',"/html/body/div/div/div/form/table/tbody/tr/td[1]/div/a")
        sleep(1)
        # 在下拉菜单的搜索框内输入要对账的账号，并点击回车
        self.driver.typeAndClear('xpath',"//input[@type='text']","xswdee")
        sleep(1)
        self.driver.enterType('xpath',"//input[@type='text']")
        sleep(1)
        self.driver.type('id',"start","2018--11-29")
        sleep(1)
        self.driver.type('id', "end", "今天")
        sleep(1)
        self.driver.click('xpath',"//button[@id='submit']")
        sleep(1)
        # 定位修改后的元素
        element1=self.driver.element_is_exists('xpath',"//h4[contains(.,'xswdee')]")
        # 如果有这个元素，就关闭浏览器，说明运行成功，如果没有，就执行截屏操作说明执行失败。
        if element1 :
           MoneyBrowseTest(). test_zindex()
        else:
            self.driver.do_screenshot()
    # 现金记账页面的首页
    def test_zindex(self):
        self.driver.leaveFrame()
        # 进入到现金记账
        self.driver.click('id', "s-menu-5")
        sleep(1)
        self.driver.enterFrame("iframe-5")
        # 点击付款账户区块的更多
        sleep(3)
        self.driver.click('xpath',"//a[contains(.,'首页')]")
        sleep(1)
        self.driver.click('xpath',"//a[contains(@href,'/ranzhi/www/cash/index.php?m=depositor&f=index')]")
        sleep(1)
        element=self.driver.element_is_exists('xpath',"//a[contains(.,'正常账号')]")
        if element:
            self.driver.click('xpath',"//a[contains(.,'首页')]")
            sleep(2)
            # 点击账户区块的更多
            self.driver.click('xpath',"//a[@href='/ranzhi/www/cash/index.php?m=trade&f=browse']")
            sleep(1)
            element1=self.driver.element_is_exists('xpath',"//button[contains(.,' 记账 ')]")
            sleep(1)
            if element1:
                self.driver.click('xpath',"//a[contains(.,'首页')]")
                sleep(1)
                # 点击供应商区块的更多
                self.driver.click('xpath',"/html/body/div/div/div[1]/div[3]/div[1]/div[1]/div/a")
                sleep(1)
                # 添加维护维护类目
                self.driver.click('xpath',"//a[contains(.,'维护类目')]")
                sleep(1)
                self.driver.typeAndClear('xpath',"//input[contains(@name,'children[43]')]","ljc")
                sleep(1)
                self.driver.typeAndClear('xpath',"//input[contains(@name,'children[44]')]","xsw")
                sleep(1)
                # 保存
                self.driver.click('xpath',"//button[@id='submit']")
                sleep(1)
    def test_out(self):
        self.driver.leaveFrame()
        # 进入到现金记账
        self.driver.click('id', "s-menu-5")
        sleep(1)
        self.driver.enterFrame("iframe-5")
        # 点击支出
        sleep(1)
        self.driver.click('xpath',"//a[contains(.,'支出')]")
        sleep(1)
        # 点击记支出
        self.driver.click('xpath',"//a[contains(.,' 记支出')]")
        sleep(1)
        # 账号输入
        self.driver.type('xpath', "//*[@id='depositor']", "xswdee")
        sleep(1)
        # 产品
        self.driver.click('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[2]/td/div/a")
        sleep(1)
        self.driver.type('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[2]/td/div/div/div/input", "dddddddd")
        sleep(1)
        self.driver.enterType('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[2]/td/div/div/div/input")
        sleep(1)
        # 科目
        self.driver.click('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[3]/td/div/span[1]/div/a")
        sleep(1)
        self.driver.type('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[3]/td/div/span[1]/div/div/div/input","非主营业")
        sleep(1)
        self.driver.enterType('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[3]/td/div/span[1]/div/div/div/input")
        sleep(1)
        # 不同的选项会有不同的页面跳转，根据具体的尽进行跳转
        # 客户支出
        self.driver.click('xpath', "//label[contains(.,' 客户支出')]")
        sleep(1)
        self.driver.click('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[7]/td/div/a")
        sleep(1)
        self.driver.type('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[7]/td/div/div/div/input","xsw")
        sleep(1)
        self.driver.enterType('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[7]/td/div/div/div/input")
        sleep(1)
        # 金额
        self.driver.type('xpath', "//input[@name='money']", "80000")
        sleep(1)
        self.driver.type('id', "currencyLabel", "欧元")
        sleep(1)
        # 部门
        self.driver.click('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[12]/td/div/a")
        sleep(1)
        self.driver.type('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[12]/td/div/div/div/input", "显示为顶顶顶顶")
        sleep(1)
        self.driver.enterType('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[12]/td/div/div/div/input")
        sleep(1)
        self.driver.click('xpath', "//ul[@class='chosen-choices']")
        sleep(1)
        self.driver.type('xpath', "//ul[@class='chosen-choices']", "xushiwei")
        sleep(1)
        self.driver.enterType('xpath', "//ul[@class='chosen-choices']")
        sleep(1)
        self.driver.typeAndClear('xpath', "//input[@name='date']", "2018-12-02")
        self.driver.enterType('xpath', "//input[@name='date']")
        sleep(1)
        self.driver.type('xpath', "//textarea[@id='desc']", "哇塞哇塞哇塞支出")
        sleep(1)
        # 上传附件的地方
        self.driver.type('xpath',
                    "/html/body/div[1]/div/div[2]/form/table/tbody/tr[16]/td/div/table[1]/tbody/tr/td[1]/div/input",
                    "F:\\PycharmProjects\\RanZhiPython\\data\\RanZhi.csv")
        sleep(1)
        self.driver.type('xpath',
                    "/html/body/div[1]/div/div[2]/form/table/tbody/tr[16]/td/div/table[1]/tbody/tr/td[2]/input", "然之")
        sleep(1)
        self.driver.click('xpath', "//button[@id='submit']")
        sleep(1)
        element = self.driver.isTextPresent("然之")
        if element:
           MoneyBrowseTest().test_report()
        else:
            self.driver.do_screenshot()
    # 首页的报表   True
    def test_report(self):
        self.driver.leaveFrame()
        # 进入到现金记账
        self.driver.click('id', "s-menu-5")
        sleep(1)
        self.driver.enterFrame("iframe-5")
        # 点击菜单栏的报表
        sleep(1)
        self.driver.click('xpath',"//a[contains(.,'报表')]")
        sleep(1)
        # 判断页面是否有这句话，来判断是否进入页面
        element=self.driver.isTextPresent("2018年收入按产品线统计")
        if element:
            self.driver.click('xpath',"//a[contains(.,'年度对比表')]")
            sleep(1)
            element1=self.driver.isTextPresent("年度对比表")
            if element1:
                self.driver.click('xpath',"//a[contains(.,'账号盈亏表')]")
                sleep(1)
                element2=self.driver.element_is_exists('xpath',"//button[contains(.,'导出')]")
                sleep(1)
                self.driver.click('xpath',"//button[contains(.,'×')]")
                sleep(1)
                element3=self.driver.element_is_exists('xpath',"//h5[contains(.,'2018年度收支表(人民币:千元)')]")
                if element3:
                    MoneyBrowseTest().test_settings()
                else:
                    self.driver.do_screenshot()
    # 现今记账页面的设置
    def test_settings(self):
        self.driver.leaveFrame()
        # 进入到现金记账
        self.driver.click('id', "s-menu-5")
        sleep(1)
        self.driver.enterFrame("iframe-5")
        # 进入到设置界面
        sleep(1)
        self.driver.click('xpath', "//a[contains(.,'设置')]")
        sleep(1)
        # 进入到收入科目
        self.driver.click('xpath', "//a[contains(.,'收入科目')]")
        sleep(1)
        self.driver.typeAndClear('xpath', "//input[@name='children[17]']", "chinese")
        sleep(1)
        self.driver.click('xpath', "//button[@type='submit']")
        sleep(1)
        element = self.driver.isTextPresent("chinese")
        if element:
            # 进入到支出科目
            self.driver.click('xpath', "//a[contains(.,'支出科目')]")
            sleep(1)
            self.driver.typeAndClear('xpath', "//input[@name='children[40]']", "english")
            sleep(1)
            self.driver.click('xpath', "//button[@type='submit']")
            sleep(1)
            element1 = self.driver.isTextPresent("english")
            if element1:
                # 进入到货币类型界面
                self.driver.click('xpath', "//a[contains(.,'货币类型')]")
                sleep(1)
                self.driver.type('id', "mainCurrency", "人民币")
                sleep(1)
                self.driver.click('xpath', "//button[@type='submit']")
                sleep(1)
                # 进入到导入模板设置界面
                self.driver.click('xpath', "//a[contains(.,'导入模板设置')]")
                sleep(1)
                element2 = self.driver.isTextPresent("导入记账模板")
                if element2:
                    # 支出浏览权限设置
                    self.driver.click('xpath', "//a[contains(.,'支出浏览权限设置')]")
                    sleep(1)
                    self.driver.click('xpath', "//label[contains(.,' 管理员')]")
                    sleep(1)
                    self.driver.click('xpath', "//label[contains(.,' 销售人员')]")
                    sleep(1)
                    self.driver.click('xpath', "//button[@type='submit']")
                    sleep(1)
                    element3 = self.driver.isTextPresent("权限")
                    if element3:
                        self.driver.click('xpath', "//a[contains(.,'记账设置')]")
                        sleep(1)
                        self.driver.click('xpath', "//label[contains(.,'必须选择部门')]")
                        sleep(1)
                        self.driver.click('xpath', "//button[@type='submit']")
                        sleep(1)
                        element4 = self.driver.isTextPresent("记账设置")
                        if element4:
                            MoneyBrowseTest().test_trade()
                        else:
                            self.driver.do_screenshot()
                    else:
                        self.driver.do_screenshot()
                else:
                    self.driver.do_screenshot()
            else:
                self.driver.do_screenshot()
        else:
            self.driver.do_screenshot()
    # 现金记账页面的借贷
    def test_trade(self):
        self.driver.leaveFrame()
        # 进入到现金记账
        self.driver.click('id', "s-menu-5")
        sleep(1)
        self.driver.enterFrame("iframe-5")
        # 点击菜单栏的借贷
        sleep(1)
        self.driver.click('xpath',"//a[contains(.,'借贷')]")
        sleep(1)
        # 点击导入
        self.driver.click('xpath',"//i[@class='icon-download-alt']")
        sleep(1)
        self.driver.type('xpath', "//*[@id='depositor']", "xswdee")
        sleep(1)
        self.driver.selectByValue('id', "schema", "中信银行")
        sleep(3)
        self.driver.type('id',"files","F:\\PycharmProjects\\RanZhiPython\\data\\RanZhi.csv")
        sleep(1)
        self.driver.selectByValue('id', "encode", "UTF-8")
        sleep(1)
        self.driver.click('xpath',"//button[@id='submit']")
        sleep(1)
        self.driver.click('xpath',"//a[contains(.,'返回')]")
        element=self.driver.element_is_exists('xpath',"//button[contains(.,' 导出 ')]")
        if element:
            # 点击借贷
            self.driver.click('xpath',"//a[contains(.,' 借贷')]")
            sleep(1)
            self.driver.type('xpath',"//*[@id='depositor']","xswdee")
            sleep(1)
            self.driver.click('xpath',"//input[@value='1']")
            sleep(2)
            self.driver.type('xpath',"//input[@name='traderName']","xushiwei")
            sleep(2)
            self.driver.type('xpath',"//input[@name='money']","1234567")
            sleep(1)
            self.driver.type('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[6]/td/div/a","显示为顶顶顶顶")
            sleep(1)
            self.driver.enterType('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[6]/td/div/a")
            sleep(1)
            self.driver.click('xpath',"//ul[@class='chosen-choices']")
            sleep(1)
            self.driver.type('xpath',"//ul[@class='chosen-choices']","xushiwei")
            sleep(1)
            self.driver.enterType('xpath',"//ul[@class='chosen-choices']")
            sleep(1)
            self.driver.typeAndClear('xpath',"//input[@name='date']","2018-11-02")
            self.driver.enterType('xpath',"//input[@name='date']")
            sleep(2)
            self.driver.typeAndClear('xpath',"//input[@name='deadline']","2018-11-30")
            self.driver.enterType('xpath',"//input[@name='deadline']")
            sleep(2)
            self.driver.click('xpath',"//textarea[@id='desc']")
            sleep(1)
            self.driver.type('xpath',"//textarea[@id='desc']","哇塞哇塞哇塞！")
            sleep(2)
            self.driver.click('xpath',"//button[@id='submit']")
            sleep(2)

            self.driver.click('xpath',"//a[contains(.,' 还贷')]")
            sleep(1)
            element=self.driver.element_is_exists('xpath',"//li[contains(.,'详情')]")
            if element:
                self.driver.click('xpath',"//a[contains(.,'返回')]")
                sleep(1)
                # 点击编辑
                element1=self.driver.element_is_exists('xpath',"//strong[contains(.,' 编辑账目')]")
                if element1:
                    self.driver.click('xpath',"//button[@id='submit']")
                    sleep(1)
                else:
                    self.driver.do_screenshot()
            else:
                self.driver.do_screenshot()
        else:
            self.driver.do_screenshot()
    # 现今记账页面的转账
    def test_transfer(self):
        self.driver.leaveFrame()
        # 进入到现金记账
        self.driver.click('id', "s-menu-5")
        sleep(1)
        self.driver.enterFrame("iframe-5")
        # 点击转账
        sleep(1)
        self.driver.click('xpath', "//a[@href='/ranzhi/www/cash/index.php?m=trade&f=browse&mode=transfer']")
        sleep(1)
        self.driver.click('xpath', "//i[@class='icon-plus']")
        sleep(1)
        # 付款账户
        self.driver.type('id', "payment", "xswdee")
        sleep(1)
        # 收款账户
        self.driver.type('id', "receipt", "顶顶顶顶顶顶顶")
        sleep(1)
        self.driver.type('xpath', "//input[@name='money']", "9999999999.99")
        sleep(1)
        self.driver.type('xpath', "//input[@id='fee']", "300")
        sleep(1)
        self.driver.type('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[7]/td/div/a", "显示为顶顶顶顶")
        sleep(1)
        self.driver.enterType('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[7]/td/div/a")
        sleep(1)
        self.driver.click('xpath', "//ul[@class='chosen-choices']")
        sleep(1)
        self.driver.type('xpath', "//ul[@class='chosen-choices']", "xushiwei")
        sleep(1)
        self.driver.enterType('xpath', "//ul[@class='chosen-choices']")
        sleep(1)
        self.driver.typeAndClear('xpath', "//input[@name='date']", "2018-12-02")
        self.driver.enterType('xpath', "//input[@name='date']")
        sleep(2)
        self.driver.click('xpath', "//textarea[@id='desc']")
        sleep(1)
        self.driver.type('xpath', "//textarea[@id='desc']", "哇塞哇塞哇塞转账！")
        sleep(1)
        self.driver.click('xpath', "//button[@id='submit']")
        sleep(1)
        element = self.driver.isTextPresent("哇塞哇塞哇塞转账!")
        if element:
            MoneyBrowseTest().test_trade()
        else:
            self.driver.do_screenshot()
    # 现今记账的投资界面
    def test_invest(self):
        self.driver.leaveFrame()
        # 进入到现金记账
        self.driver.click('id', "s-menu-5")
        sleep(1)
        self.driver.enterFrame("iframe-5")
        # 点击菜单栏的投资
        self.driver.click('xpath',"//a[@href='/ranzhi/www/cash/index.php?m=trade&f=browse&mode=invest']")
        sleep(1)
        # 点击任务栏的投资区块
        self.driver.click('xpath',"//a[contains(.,' 投资')]")
        sleep(2)
        # 部门
        self.driver.type('id',"depositor","xswdee")
        sleep(2)
        # 商户
        self.driver.click('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[2]/td/div/div[1]/a")
        sleep(2)
        self.driver.type('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[2]/td/div/div[1]/a", "xushiwei")
        sleep(2)
        self.driver.enterType('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[2]/td/div/div[1]/a")
        sleep(2)
        # 金额
        self.driver.type('xpath',"//input[@name='money']","521526521")
        sleep(1)
        # 部门
        # self.driver.type('id', "dept_chosen","显示为顶顶顶顶")
        # sleep(10)
        # self.driver.type('xpath', "//input[@autocomplete='off']", "显示为顶顶顶顶")
        # sleep(2)
        # self.driver.enterType('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[6]/td/div/div/div/input")
        # sleep(2)
        # 经手人
        self.driver.click('xpath', "//ul[contains(@class,'chosen-choices')]")
        sleep(1)
        self.driver.type('xpath', "//ul[contains(@class,'chosen-choices')]", "xushiwei")
        sleep(1)
        self.driver.enterType('xpath', "//ul[contains(@class,'chosen-choices')]")
        sleep(1)
        self.driver.typeAndClear('xpath', "//input[@name='date']", "2018-11-02")
        self.driver.enterType('xpath', "//input[@name='date']")
        sleep(2)
        self.driver.typeAndClear('xpath', "//input[@name='deadline']", "2018-11-30")
        self.driver.enterType('xpath', "//input[@name='deadline']")
        sleep(2)
        self.driver.type('xpath', "//textarea[@id='desc']", "哇塞哇塞哇塞投资！")
        sleep(2)
        self.driver.click('xpath', "//button[@id='submit']")
        sleep(2)
        # 判断是否有投资回报率这个字段,如果有就点击页面上的赎回区块
        element=self.driver.element_is_exists('xpath',"//th[contains(.,'投资回报率')]")
        if element:
            self.driver.click('xpath',"/html/body/div[2]/div[1]/a[3]")
            sleep(1)
            # 部门
            self.driver.type('id', "depositor", "xswdee")
            sleep(1)
            # 投资
            self.driver.click('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[3]/td/div[2]/a")
            sleep(1)
            self.driver.type('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[3]/td/div[2]/a","2018")
            sleep(1)
            self.driver.enterType('xpath',"/html/body/div[1]/div/div[2]/form/table/tbody/tr[3]/td/div[2]/a")
            sleep(1)
            # 金额
            self.driver.type('xpath',"//input[@name='money']","999999999999.99")
            sleep(1)
            # 收入
            self.driver.type('id',"investCategory","理财盈利")
            sleep(1)
            # 部门
            self.driver.type('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[6]/td/div/a", "显示为顶顶顶顶")
            sleep(1)
            self.driver.enterType('xpath', "/html/body/div[1]/div/div[2]/form/table/tbody/tr[6]/td/div/a")
            sleep(1)
            # 经手人
            self.driver.click('xpath', "//ul[contains(@class,'chosen-choices')]")
            sleep(1)
            self.driver.type('xpath', "//ul[contains(@class,'chosen-choices')]", "xushiwei")
            sleep(1)
            self.driver.enterType('xpath', "//ul[contains(@class,'chosen-choices')]")
            sleep(1)
            # 日期
            self.driver.typeAndClear('xpath', "//input[@name='date']", "2018-11-02")
            self.driver.enterType('xpath', "//input[@name='date']")
            sleep(2)
            # 文本框
            self.driver.type('xpath', "//textarea[@id='desc']", "哇塞哇塞哇塞赎回！")
            sleep(2)
            self.driver.click('xpath', "//button[@id='submit']")
            sleep(2)
            element1=self.driver.element_is_exists('xpath',"//td[contains(@class,'w-60px')]")
            if element1:
                pass
            else:
                self.driver.do_screenshot()
        else:
            self.driver.do_screenshot()

    # 9843联系人编辑分组√
    def test_2_editCall(self):
        WebDriverEngine().click_scy("xpath=//a[contains(.,'联系人')]")
        time.sleep(3)
        WebDriverEngine().enterFrame("iframe-dashboard")
        WebDriverEngine().click_scy("xpath=/html/body/div/div/table/tbody/tr/td[4]/a[1]")
        time.sleep(3)
        WebDriverEngine().typeAndClear_scy("xpath=//input[@autocomplete='on']", "kkk")
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
    @classmethod
    def tearDownClass(self):
        self.driver.close()
