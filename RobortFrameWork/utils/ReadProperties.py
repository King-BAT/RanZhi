import configparser
class Read(object):
        def getValue(self, key):
            # 初始化一个实例对象
            cf = configparser.ConfigParser()
            # 读取文件
            cf.read("../config/config.ini")
            # 读取[section]模块
            section = cf.sections()[0]
            # 获取键值对的key得到对应的value，返回value的值
            value = cf.get(section, key)
            return value
