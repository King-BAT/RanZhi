import os
from dataprovider.TxtDataProvider  import txtRead
from dataprovider.ExcelDataProvider  import excelRead
from dataprovider.MySqlDataProvider import MySQl
class JudgeFile(object):
    def  judge(self,filepath):
        if (os.path.splitext(filepath)[-1]==".txt"):
            text=txtRead().get_data()
            return text
        elif(os.path.splitext(filepath)[-1]==".xlsx"):
            excel=excelRead().get_data()
            return excel
    def judgeMysql(self, sql = None, host = None, port = None, dbName = None, username = None, password = None, charset = None):
        result = MySQl(host, port, dbName, username, password, charset).getData(sql)
        return  result



