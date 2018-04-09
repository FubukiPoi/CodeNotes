
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



