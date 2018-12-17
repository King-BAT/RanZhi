#coding:utf-8
import  logging
import os
import time
# 写入日志信息的类
class Log(object):
    def __init__(self,logger):
        # 先创建log对象，在创建句柄对象
        self.logger=logging.getLogger('log_Name')
        # 设置级别，有很多不同的级别，比如debug。error..
        self.logger.setLevel(logging.INFO)
        # 设置日志的名称。
        logger_time=time.strftime('%Y%m%d%H%M',(time.localtime()))
        logger_name=os.path.dirname(os.path.abspath('.'))+'\\loginformation\\'+logger_time+'.log'

        # 以文件的形式生成日志文件
        file=logging.FileHandler(logger_name)
        file.setLevel(logging.INFO)
        # 设置格式，格式化内容,时间，名字，等级名称；
        fornatter1=logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        file.setFormatter(fornatter1)
        # 写入日志文件
        self.logger.addHandler(file)

        # # 将日志信息打印在控制台上
        # console=logging.StreamHandler()
        # console.setLevel(logging.INFO)
        # # 格式化
        # fornatter2=logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        # console.setFormatter(fornatter2)
        # # 向日志文件中国写入信息
        # self.logger.addHandler(console)
    def getLog(self):
        return self.logger



