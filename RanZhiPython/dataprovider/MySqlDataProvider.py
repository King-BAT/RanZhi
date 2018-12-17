import pymysql
class MySQl(object):
    # 构造方法，初始化数据库，
    # 因为每个人要脸的数据库的信息不同，使用数据库驱动的时候需要手动输入信息
    def __init__(self, host, port, dbName, username, password, charset):
        self.host = host
        self.port = port
        self.dbname = dbName
        self.user = username
        self.password = password
        self.charset = charset
        # 连接数据库
        self.conn=pymysql.connect(
            host=host,
            port=port,
            db=dbName,
            user=username,
            password=password,
            charset=charset
        )
        # 通过设置游标获取数据
        self.cursor=self.conn.cursor()
    # 在数据库中获取数据
    def getData(self,sql):
        self.cursor.execute(sql)
        # 用result获取所有的表中的信息
        result=self.cursor.fetchall()
        return result
