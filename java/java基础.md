
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

![avatar](/Notes/java/pic/threeHand.png)



