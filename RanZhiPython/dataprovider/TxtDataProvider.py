from utils.ReadProperties import Read

t_path = Read().getValue('txt_path')
class txtRead(object):
    def get_data(self):
        source = open(t_path, 'r')
        # 读取文件
        content = source.readlines()
        # 关闭文件
        source.close()
        return content