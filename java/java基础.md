
###1.java 的 equals() 和 ==
***
equals():字符串的比较
==      :地址的比较
```
String str1 = new String("poi");
String str2 = "poi";
str1 == str2 //false
str1.equlas(str2) //ture
```
原理:str1和str2放入栈,new String("poi")放入堆
***



<meta http-equiv="refresh" content="0.5">