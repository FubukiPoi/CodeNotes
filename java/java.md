
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

# String、StringBuffer与StringBuilder之间区别
1.如果要操作少量的数据用 = String
2.单线程操作字符串缓冲区 下操作大量数据 = StringBuilder
3.多线程操作字符串缓冲区 下操作大量数据 = StringBuffer

# 接口
1. java类是单继承的。classB Extends classA
2. java接口可以多继承。Interface3 Extends Interface0, Interface1, interface……

# Error与Exception的区别
Error类和Exception类都是继承Throwable类
Error和Exception就像是水池和水池里的水的区别

　　“水池”，就是代码正常运行的外部环境，如果水池崩溃（系统崩溃），或者池水溢出（内存溢出）等，这些都是跟水池外部环境有关。这些就是java中的error

　　“水池里的水”，就是正常运行的代码，水污染了、有杂质了，浑浊了，这些影响水质的因素就是Exception。


# 其他待补充
面向对象编程的基本理念与核心设计思想解释下多态性（polymorphism），封装性（encapsulation），内聚（cohesion）以及耦合（coupling）。

继承（Inheritance）与聚合（Aggregation）的区别在哪里。

你是如何理解干净的代码（Clean Code）与技术负债（Technical Debt）的。

描述下常用的重构技巧。

阐述下 SOLID 原则。

其他的譬如 KISS,DRY,YAGNI 等原则又是什么含义。

什么是设计模式（Design Patterns）？你知道哪些设计模式？你有了解过存在哪些反模式（Anti-Patterns）吗？你会如何设计登陆舰/数学表达式计算程序/一条龙？你知道哪些基本的排序算法，它们的计算复杂度如何？在给定数据的情况下你会倾向于使用哪种算法呢？尝试编写如下代码：计算指定数字的阶乘开发 Fizz Buzz 小游戏倒转句子中的单词回文字符串检测枚举给定字符串的所有排列组合Java 核心概念equals 与 hashCode 的异同点在哪里？Java 的集合中又是如何使用它们的。

描述下 Java 中集合（Collections），接口（Interfaces），实现（Implementations）的概念。

LinkedList 与 ArrayList 的区别是什么？基础类型（Primitives）与封装类型（Wrappers）的区别在哪里？final 与 static 关键字可以用于哪里？它们的作用是什么？阐述下 Java 中的访问描述符（Access Modifiers）。

描述下 String,StringBuilder 以及 StringBuffer 区别。

接口（Interface）与抽象类（Abstract Class）的区别在哪里。

覆盖（Overriding）与重载（OverLoading）的区别在哪里。

异常分为哪几种类型？以及所谓的handle or declare原则应该如何理解？简述垃圾回收器的工作原理。

你是如何处理内存泄露或者栈溢出问题的？如何构建不可变的类结构？关键点在哪里？什么是 JIT 编译？Java 8 / Java 7 为我们提供了什么新功能？即将到来的 Java 9 又带来了怎样的新功能？Hibernate / 数据库请解释下 ORM。

简述下 Hibernate 的优劣特性。

Hibernate 与 JPA 区别在哪？Hibernate 最新版提供了哪些特性？什么是懒加载（Lazy Loading）？什么是 N+1 难题？介绍一些熟悉的 Hibernate 注释。

简介下 Hibernate Session 与 SessionFactory。

Entity Beans 的状态有哪些。

Hibernate 中的缓存分为几层。

Hibernate 中事务的支持分为几级？什么是乐观锁（Optimistic Locking）？简述下 ACID 原则。

简述下数据库正则化（Normalizations）。

请介绍下你日常工作中优化慢查询（Slow Query）的策略。

Spring新版的 Spring 中有哪些新特性？介绍下 Spring 的优势与缺陷。

什么是控制反转（Inversion of Control）与依赖注入（Dependency Injection）？你用过哪些 Spring 的模块？Spring 中是如何使用依赖注入的？Spring 中提供了几种自动注入的机制？介绍下 Spring MVC。

Spring 中 Scopes 有哪些？Spring 中 Bean 的生命周期包含哪些步骤？Spring Bean 与 EJB Bean 的区别在哪里？其他主题介绍下切面编程（Aspect Oriented Programming）。

概述下 GET 与 POST 的区别。

Web Server、Web Container 与 Application Server 的区别是什么？简要介绍下从浏览器输入 URL 开始到获取到请求界面之后 Java Web 应用中发生了什么。

什么是 N 层架构？微服务（MicroServices）与巨石型应用（Monolithic Applications）之间的区别在哪里？你知道哪些商业级设计模式？你是如何测试一个应用的？知道哪些测试框架？你是如何测试单个方法的？在你的职业生涯中，算得上最困难的技术挑战是什么？什么是领域驱动开发（Domain Driven Development）？介绍下一些你最爱的 IDE 的常用插件。

除了 IDE 之外，你的日常工作中还会用到哪些工具？你使用什么版本管理工具？分支（Branch）与标签（Tag）之间的区别在哪里？你常用的持续集成（Continuous Integration）、静态代码分析（Static Code Analysis）工具有哪些？

面向对象编程的基本理念与核心设计思想解释下多态性（polymorphism），封装性（encapsulation），内聚（cohesion）以及耦合（coupling）。

继承（Inheritance）与聚合（Aggregation）的区别在哪里。

你是如何理解干净的代码（Clean Code）与技术负债（Technical Debt）的。

描述下常用的重构技巧。

阐述下 SOLID 原则。

其他的譬如 KISS,DRY,YAGNI 等原则又是什么含义。

什么是设计模式（Design Patterns）？你知道哪些设计模式？你有了解过存在哪些反模式（Anti-Patterns）吗？你会如何设计登陆舰/数学表达式计算程序/一条龙？你知道哪些基本的排序算法，它们的计算复杂度如何？在给定数据的情况下你会倾向于使用哪种算法呢？尝试编写如下代码：计算指定数字的阶乘开发 Fizz Buzz 小游戏倒转句子中的单词回文字符串检测枚举给定字符串的所有排列组合Java 核心概念equals 与 hashCode 的异同点在哪里？Java 的集合中又是如何使用它们的。

描述下 Java 中集合（Collections），接口（Interfaces），实现（Implementations）的概念。

LinkedList 与 ArrayList 的区别是什么？基础类型（Primitives）与封装类型（Wrappers）的区别在哪里？final 与 static 关键字可以用于哪里？它们的作用是什么？阐述下 Java 中的访问描述符（Access Modifiers）。

描述下 String,StringBuilder 以及 StringBuffer 区别。

接口（Interface）与抽象类（Abstract Class）的区别在哪里。

覆盖（Overriding）与重载（OverLoading）的区别在哪里。

异常分为哪几种类型？以及所谓的handle or declare原则应该如何理解？简述垃圾回收器的工作原理。

你是如何处理内存泄露或者栈溢出问题的？如何构建不可变的类结构？关键点在哪里？什么是 JIT 编译？Java 8 / Java 7 为我们提供了什么新功能？即将到来的 Java 9 又带来了怎样的新功能？Hibernate / 数据库请解释下 ORM。

Hibernate 与 JPA 区别在哪？Hibernate 最新版提供了哪些特性？什么是懒加载（Lazy Loading）？什么是 N+1 难题？介绍一些熟悉的 Hibernate 注释。

简介下 Hibernate Session 与 SessionFactory。

Entity Beans 的状态有哪些。

简述下数据库正则化（Normalizations）。

请介绍下你日常工作中优化慢查询（Slow Query）的策略。

Spring新版的 Spring 中有哪些新特性？介绍下 Spring 的优势与缺陷。

什么是控制反转（Inversion of Control）与依赖注入（Dependency Injection）？你用过哪些 Spring 的模块？Spring 中是如何使用依赖注入的？Spring 中提供了几种自动注入的机制？介绍下 Spring MVC。

Spring 中 Scopes 有哪些？Spring 中 Bean 的生命周期包含哪些步骤？Spring Bean 与 EJB Bean 的区别在哪里？其他主题介绍下切面编程（Aspect Oriented Programming）。

概述下 GET 与 POST 的区别。

Web Server、Web Container 与 Application Server 的区别是什么？简要介绍下从浏览器输入 URL 开始到获取到请求界面之后 Java Web 应用中发生了什么。

什么是 N 层架构？微服务（MicroServices）与巨石型应用（Monolithic Applications）之间的区别在哪里？你知道哪些商业级设计模式？你是如何测试一个应用的？知道哪些测试框架？你是如何测试单个方法的？在你的职业生涯中，算得上最困难的技术挑战是什么？什么是领域驱动开发（Domain Driven Development）？介绍下一些你最爱的 IDE 的常用插件。

除了 IDE 之外，你的日常工作中还会用到哪些工具？你使用什么版本管理工具？分支（Branch）与标签（Tag）之间的区别在哪里？你常用的持续集成（Continuous Integration）、静态代码分析（Static Code Analysis）工具有哪些？




