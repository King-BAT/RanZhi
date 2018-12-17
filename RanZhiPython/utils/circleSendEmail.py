from email.header import Header
from email.mime.text import MIMEText
from email.utils import parseaddr, formataddr
from email.mime.multipart import  MIMEMultipart
import unittest
import smtplib,os
import time
from utils.ReadProperties import Read
from  testcase.money_moneyAll_testa import MoneyBrowseTest
from  testcase.money_check_testing import MoneytCheck
#注：生成的测试报告包含饼图
from utils.HTMLTestRunnerCircle import HTMLTestRunner

def send_email(po):
        def _format_addr(s):
                name, addr = parseaddr(s)
                return formataddr((Header(name, 'utf-8').encode(), addr))#如果包含中文，需要通过Header对象进行编码。
        to_addr=Read().getValue('to_addr')
        tempTo=to_addr
        #smtp_server = "smtp.163.com"
        smtp_server=Read().getValue('smtp_server')
        msg = MIMEMultipart()
        msg.attach(po)

        msg['From'] = _format_addr('ff <%s>' % Read().getValue('from_addr'))
        msg['To'] = ''.join(tempTo)
        msg['To'] = _format_addr('小组人员 <%s>')
        msg['Subject'] = Header('测试邮件', 'utf-8').encode()

        server = smtplib.SMTP(smtp_server, 25)# SMTP协议默认端口是25
        server.set_debuglevel(1)#打印出和SMTP服务器交互的所有信息
        server.login(Read().getValue('from_addr'), Read().getValue('password'))
        #发送指定人邮件
        #server.sendmail(from_addr, [to_addr], msg.as_string())
        #发送多人
        server.sendmail(Read().getValue('from_addr'),tempTo, msg.as_string())
        server.quit()
 #——————————————————————————————————————————————
def t_send():
        #所要执行的测试用例所在的位置
        test_dir = Read().getValue('test_dir')
        #测试报告所在的路径
        test_report = Read().getValue('test_report')
        # 查找想要执行的文件
        discover = unittest.defaultTestLoader.discover(test_dir, pattern='*_testinga.py')
        # 使用HTMLTestRunner来生成testRunner，生成html测试报告
        now_time = time.strftime("%Y%m%d%H%M%S")
        file_name = test_report + '\\' + now_time + 'result.html'
        fp = open(file_name, 'wb')
        runner = HTMLTestRunner(stream=fp, title="测试报告", description="运行环境:firefox")
        runner.run(discover)
        fp.close()

        # 查找修改日期最新的测试方法
        new_report1 = new_report(test_report)
        f = open(new_report1, 'rb')
        message = f.read()
        text = MIMEText(message, 'html', 'utf-8')
        # 发送测试报告
        send_email(text)
#查找最新生成的测试报告
def new_report(files):
    lists = os.listdir(files)
    lists.sort(key=lambda fn: os.path.getmtime(files+"\\"+fn))
    file_new = os.path.join(files,lists[-1])
    return file_new


