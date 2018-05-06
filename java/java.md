
#  java 的 equals() 和 ==
  
equals():字符串的比较

==      :地址的比较
```
String str1 = new String("poi");
String str2 = "poi";
str1 == str2 //false
str1.equlas(str2) //ture
```
 原理:str1和str2放入栈,new String("poi")放入堆,"poi"放入方法区

# volatile底层实现原理

## 定义
java编程语言允许线程访问共享变量，为了确保共享变量能够被准确和一致的更新，线程应该通过排他锁获得这个变量。java提供了volatile，在某些情况下比锁更加方便。如果一个字段被声明成volatile，java线程内存模型确保所有线程看到的这个变量的值是一致的。

## 实现原理

标有volatile的变量在进行写操作时，会在前面加上lock质量前缀。

而lock指令前缀会做如下两件事

1. 将当前处理器缓存行的数据写回到内存。lock指令前缀在执行指令的期间，会产生一个lock信号，lock信号会保证在该信号期间会独占任何共享内存。lock信号一般不锁总线，而是锁缓存。因为锁总线的开销会很大。

2. 将缓存行的数据写回到内存的操作会使得其他CPU缓存了该地址的数据无效。

# ThreadLocal 是什么底层如何实现

 ThreadLocal不是为了解决多线程访问共享变量,而是为每个线程创建一个单独的变量副本,提供了保持对象的方法和避免参数传递的复杂性。
 >https://blog.csdn.net/lovesomnus/article/details/64441426







