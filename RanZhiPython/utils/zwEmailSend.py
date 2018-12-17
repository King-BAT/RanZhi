from email.header import Header
from email.mime.text import MIMEText
from email.utils import parseaddr, formataddr
from email.mime.multipart import  MIMEMultipart
import smtplib,os
from utils.ReadProperties import Read
#注：以正文的形式发送指定的HTML文件
#从本地获取测试报告
f=open("D:\\RobortFrameWork\\report\\20181125171218result.html",'rb')
message=f.read()
text=MIMEText(message,'html','utf-8')
def send_email(text):
        def _format_addr(s):
                name, addr = parseaddr(s)
                return formataddr((Header(name, 'utf-8').encode(), addr))#如果包含中文，需要通过Header对象进行编码。

        from_addr = Read().getValue('from_addr')
        password = Read().getValue('password')
        to_addr = Read().getValue('to_addr')
        smtp_server = Read().getValue('smtp_server')
        msg = MIMEMultipart()
        msg.attach(text)


        msg['From'] = _format_addr('ff <%s>' % from_addr)
        msg['To'] = ','.join(to_addr)
        msg['To'] = _format_addr('管理员 <%s>' % to_addr)

        msg['Subject'] = Header('测试邮件', 'utf-8').encode()
        server = smtplib.SMTP(smtp_server, 25)
        server.set_debuglevel(1)
        server.login(from_addr, password)
        #发送多人
        server.sendmail(from_addr,msg['To'].split(','), msg.as_string())
        server.quit()
