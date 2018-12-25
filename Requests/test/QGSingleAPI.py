from core.EmailUtils import generateTestReport
from core.EmailUtils import send_email
if __name__ == "__main__":
    # 生成测试报告，有两个参数
    # 第一个参数必须给出，是要测试的.py文件的名字可以是全名，如例子所写，也可以是匹配，*_test.py
    # 第二个参数声明时已经给出默认值，默认生成原生的测试报告，也可以传参换测试报告的样式:
    # en--美化后的英文的测试报告(无饼图)，round--美化后的中文的测试报告(有饼图)
    generateTestReport("*_test.py")
    # 将测试报告以正文形式或者附件形式发送，不传参默认以正文形式发送测试报告，也可以加上形参：attachment,附件形式发送
    # send_email("attachment")