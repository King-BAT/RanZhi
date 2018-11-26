import xlrd
from utils.ReadProperties import Read
e_path=Read().getValue('excel_path')
class excelRead(object):
       def get_data(self):
        # 找到excel文件位置
        source = xlrd.open_workbook(e_path, 'r')
        # 打开excel文件
        table = source.sheet_by_index(0)
        # 获取excel一共有几行
        rows=table.nrows
        # 输出表格的正确内容,用list保存获取的结果
        list=[]
        for i in range(rows):
            # 获取每行的值
            # result=table.row_values(i)
            # return (result)
            list.append(table.row_values(i))
        return list
