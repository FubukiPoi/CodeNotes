# Linux下实现MySql主从同步

>192.168.0.1:主库ip
192.168.0.1:从库ip

## master 主库配置
1. 修改 /etc/my.cnf
2. 添加
```
server-id=1
#开启二进制
log-bin=master
log-bin-index=master.bin.index
```
3. 重启服务命令 service mysqld restart
4. 进入mysql输入命令查看master status: SHOW MASTER STATUS(从库master_log_file的需要使用File字段值)
5. create user poi;
6. slave服务器192.168.0.1 以poi的帐号和master连接，并赋予REPLICATION SLAVE权限,针对maser服务器所有表,密码114514
```
GRANT REPLICATION SLAVE ON *.* TO 'poi'@'192.168.0.2' INDENTIFIED BY '114514'
```
7. flush privileges;


## slave 从库配置
1. 修改 /etc/my.cnf
2. [mysqld]下添加
```
server-id=2
relay-log-index=slave-relay-bin.index
relay-log=slave-relay-bin
```
3. 重启服务命令 service mysqld restart
4. 输入命令: 
```
change master to master_host='192.168.0.1',master_port=3306,master_user='poi',master_password='114514',master_log_file='master.bin.000001',master_log_pos=0;
```
5. 开启主从跟踪: start slave;
6. 查看是否成功无报错: show slave status \G;

### 最后就完成了主库写数据,从库读数据。

## 如何在代码里实现主从数据库的DataSource配置？
待补充...
关键字:AbstractRoutingDataSource,写一个sql增删查改的拦截器判断使用主库还是从库并添加到mybatis的配置里