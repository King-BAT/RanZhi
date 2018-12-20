# coding:utf-8

import unittest
import time
import smtplib, os
from email.mime.text import MIMEText
from email.header import Header
from email.mime.multipart import MIMEMultipart
from utils.ReadProperties import ReadIni
from email.utils import parseaddr, formataddr
from HTMLTestRunner import HTMLTestRunner
from HTMLTestRunnerCN import HTMLTestReportCN
from HTMLTestRunnerEN import HTMLTestRunnerEN

# 所要执行的测试用例所在的位置
test_dir = ReadIni().getValue('testDir')
# 测试报告所在的路径
test_report = ReadIni().getValue('testReport')
# 邮箱服务器server配置
server = ReadIni().getValue('server')
# 发件人
send_from = ReadIni().getValue('send_from')
# 收件人
send_to = ReadIni().getValue('send_to')
# 发件人的邮箱授权码
auth_code = ReadIni().getValue('auth_code')


# 生成测试报告
def generateTestReport(testcase, reportType='native'):
    # 查找想要执行的文件
    discover = unittest.defaultTestLoader.discover(test_dir, pattern=testcase)
    now_time = time.strftime("%Y%m%d%H%M%S")
    file_name = test_report + '\\' + now_time + 'result.html'
    fp = open(file_name, 'wb')
    # 使用HTMLTestRunner生成html测试报告
    if reportType == "en":
        runner = HTMLTestRunnerEN(stream=fp, title="测试报告", description="运行环境:firefox")
        runner.run(discover)
    # 使用HTMLTestRunnerCN生成html测试报告
    elif reportType == "native":
        runner = HTMLTestRunner(stream=fp, title="测试报告", description="运行环境:firefox")
        runner.run(discover)
    # 使用HTMLTestRunnerEN生成html测试报告
    elif reportType == "round":
        runner = HTMLTestReportCN(stream=fp, title="测试报告", description="运行环境:firefox")
        runner.run(discover)
    fp.close()


# 查找最新生成的测试报告
def new_report(files):
    lists = os.listdir(files)
    lists.sort(key=lambda fn: os.path.getmtime(files + "\\" + fn))
    file_new = os.path.join(files, lists[-1])
    return file_new


# 附件发送邮箱的相关配置
def send_emailAttachment():
    att_file = new_report(test_report)
    subject = '最新的测试报告'
    sendfile = open(att_file, 'rb').read()
    # 邮件附件的内容
    att = MIMEText(sendfile, 'base64', 'utf-8')
    att["Content-Type"] = 'application/octet-stream'
    # 定义附件的名称
    att["Content-Disposition"] = 'attachment; filename="result.html"'

    # 邮件正文的内容
    msg = MIMEMultipart('related')
    msg['Subject'] = Header(subject, 'utf-8')
    msg.attach(att)
    msg.attach(MIMEText('<html><h1>请查收测试报告！</h1></html>', 'html', 'utf-8'))
    msg['from'] = send_from
    msg['to'] = send_to
    smtp = smtplib.SMTP()
    smtp.connect(server)
    smtp.login(send_from, auth_code)
    smtp.sendmail(send_from, send_to, msg.as_string())
    smtp.quit()


def send_emailContent():
    def _format_addr(s):
        name, addr = parseaddr(s)
        # 如果包含中文，需要通过Header对象进行编码。
        return formataddr((Header(name, 'utf-8').encode(), addr))

    to_addr = ReadIni().getValue('send_to')  # ["1042725067@qq.com","963540051@qq.com"]
    tempTo = to_addr
    from_addr = ReadIni().getValue('send_from')
    password = ReadIni().getValue('auth_code')
    smtp_server = ReadIni().getValue('server')
    # 生成邮件体
    msg = MIMEMultipart()
    # 找到最新的测试报告
    newestReport = new_report(test_report)
    f = open(newestReport, 'rb')
    message = f.read()
    text = MIMEText(message, 'html', 'utf-8')
    msg.attach(text)
    msg['From'] = _format_addr(' <%s>' % from_addr)
    msg['To'] = ''.join(tempTo)
    msg['To'] = _format_addr('小组人员 <%s>')
    msg['Subject'] = Header('测试邮件', 'utf-8').encode()
    # SMTP协议默认端口是25
    server = smtplib.SMTP(smtp_server, 25)
    # 打印出和SMTP服务器交互的所有信息
    server.set_debuglevel(1)
    server.login(from_addr, password)

    server.sendmail(from_addr, tempTo, msg.as_string())
    server.quit()


def send_email(emailType="content"):
    if emailType == "attachment":
        send_emailAttachment()
    else:
        send_emailContent()

