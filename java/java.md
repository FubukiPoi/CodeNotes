
#  java 的 equals() 和 ==
  
equals():字符串的比较

==      :地址的比较
```
String str1 = new String("poi");
String str2 = "poi";
str1 == str2 //false
str1.equlas(str2) //ture
```
 原理:str1和str2放入栈,new String("poi")放入堆

# ThreadLocal 是什么底层如何实现

 ThreadLocal不是为了解决多线程访问共享变量,而是为每个线程创建一个单独的变量副本,提供了保持对象的方法和避免参数传递的复杂性。
 >https://blog.csdn.net/lovesomnus/article/details/64441426

# MySQL常见的三种存储引擎（InnoDB、MyISAM、MEMORY）
1. InnoDB：支持事务处理，支持外键，支持崩溃修复能力和并发控制。如果需要对事务的完整性要求比较高（比如银行），要求实现并发控制（比如售票），那选择InnoDB有很大的优势。如果需要频繁的更新、删除操作的数据库，也可以选择InnoDB，因为支持事务的提交（commit）和回滚（rollback）。 

2. MyISAM：插入数据快，空间和内存使用比较低。如果表主要是用于插入新记录和读出记录，那么选择MyISAM能实现处理高效率。如果应用的完整性、并发性要求比 较低，也可以使用。

3. MEMORY：所有的数据都在内存中，数据的处理速度快，但是安全性不高。如果需要很快的读写速度，对数据的安全性要求较低，可以选择MEMOEY。它对表的大小有要求，不能建立太大的表。所以，这类数据库只使用在相对较小的数据库表。

# TCP三次握手过程
1. client向server发送TCP请求数据包

2. server收到请求后,会发回连接确认数据包

3. client收到server的确认报文后,还需作出确认,既发送一个序列号seq(A)=x+1;确认号ack(A)=y+1;的报文

![avatar](https://github.com/FubukiPoi/Notes/blob/master/java/pic/threeHand.png)

# TCP四次挥手过程

 TCP连接的拆除,需要发送4个包
 ![avatar](https://github.com/FubukiPoi/Notes/blob/master/java/pic/fourHand.png)

 ## 为什么建立连接三次握手,关闭连接四次握手

因为TCP连接是全双工的(即数据可在两个方向上同时传递)所以进行关闭时每个方向上都要单独进行关闭。这个单方向的关闭就叫半关闭。当一方完成它的数据发送任务，就发送一个FIN来向另一方通告将要终止这个方向的连接。



