#Spring bean 周期

- 1.Spring对Bean进行实例化（相当于程序中的new Xx()）
- 2.Spring将值和Bean的引用注入进Bean对应的属性中
- 3.如果Bean实现了BeanNameAware接口，Spring将Bean的ID传递给setBeanName()方法
**（实现BeanNameAware清主要是为了通过Bean的引用来获得Bean的ID，一般业务中是很少有用到Bean的ID的)**
- 4.如果Bean实现了BeanFactoryAware接口，Spring将调用setBeanDactory(BeanFactory bf)方法并把BeanFactory容器实例作为参数传入。**（实现BeanFactoryAware 主要目的是为了获取Spring容器，如Bean通过Spring容器发布事件等）**
- 5.如果Bean实现了ApplicationContextAwaer接口，Spring容器将调用setApplicationContext(ApplicationContext ctx)方法，把y应用上下文作为参数传入.**(作用与BeanFactory类似都是为了获取Spring容器，不同的是Spring容器在调用setApplicationContext方法时会把它自己作为setApplicationContext 的参数传入，而Spring容器在调用setBeanDactory前需要程序员自己指定（注入）setBeanDactory里的参数BeanFactory )**
6.如果Bean实现了BeanPostProcess接口，Spring将调用它们的postProcessBeforeInitialization（预初始化）方法 **（作用是在Bean实例创建成功后对进行增强处理，如对Bean进行修改，增加某个功能）**
- 7.如果Bean实现了InitializingBean接口，Spring将调用它们的afterPropertiesSet方法，作用与在配置文件中对Bean使用init-method声明初始化的作用一样，都是在Bean的全部属性设置成功后执行的初始化方法。
- 8.如果Bean实现了BeanPostProcess接口，Spring将调用它们的postProcessAfterInitialization（后初始化）方法 **（作用与6的一样，只不过6是在Bean初始化前执行的，而这个是在Bean初始化后执行的，时机不同 )**
- 9.经过以上的工作后，Bean将一直驻留在应用上下文中给应用使用，直到应用上下文被销毁10.如果Bean实现了DispostbleBean接口，Spring将调用它的destory方法，作用与在配置文件中对Bean使用destory-method属性的作用一样，都是在Bean实例销毁前执行的方法。

# BeanFactory和ApplicationContext

BeanFactory : IoC 容器
>Bean 工厂（ com.springframework.beans.factory.BeanFactory ）是 Spring 框架中最核心的接口，它提供了高级 IoC 的配置机制 。BeanFactory 使管理不同类型的 Java 对象成为可能

ApplicationContext : 为应用上下文或 Spring 容器
>应用上下文（ com.springframework.context.ApplicationContext ）建立在 BeanFactory 基础之上，提供了更多面向应用的功能，它提供了国际化支持和框架事件体系，更易于创建实际应用 


# Spring支持3种注入方式

>属性注入 : setter方法注入,写在applicationContext.xml的\<bean id="" class=""> 里

>构造器注入 : \<construtor-arg>

>工厂方法注入(不推荐)

## 现在用注解,而不是用xml来写

@Component : 基本注解

@Controller : 表现层,既Action

@Service : 业务层

@Repository : 持久化层

@Autowired : 注入bean

```
@Autowire
private UserService userService;
```

@Resource :

@Inject : 

## 指定Spring IOC 容器扫描包

`<context:component-scan base-package>`

# Spring IOC容器管理bean的生命周期

## 过程
1.通过构造器或者工厂方法创建Bean

2.为Bean的属性设置值和对其他Bean的引用

3.调用Bean的初始化方法

4.使用Bean

5.当容器关闭时,调用的Bean销毁

6.可以通过init-method=""和destory-method=""初始化和销毁

# Spring AOP 面向切面编成

切面(Aspect) : 申明一个切面,需要把该类放入IOC容器中,再声明为一个切面

```
@Aspect
@Component
public class LogAspect{

    @Before("execution(目标类方法)")
    public void beforeMethod(){
        //前置通知,目标方法开始前执行
    }

    @After
    //...

}
```

通知(Advice) :

目标(Target) : 

代理(Proxy) :

连接点(Joinpoint) :

切点(pointcut) ： 

# Spring事务没有事务,只提供事务管理

```
//事务案例 : 
buyBook(1)
updateBook(-1)
userAccount(-10)
//如果userAccount(-10)抛出金额不够异常,但之前触发了updateBook(-1),数据库的书实际并没有见,但数据库的book数据-1,这是不应该发生的，所以需要加入@Transactional注解,理解为整个买书过程金额不够应发生回退
```

@Transactional : 添加事务注解,一般写在Service层

# Spring AOP实现原理
动态代理模式

# spring boot自动配置原理

(待补充)

# springboot如何加载application.properties的？
有一个监听器

# spring加载xml的原理

# springboot如何配置测试,生产,开发环境?

springMVC可以使用@profile
```java
@Profile(value="prod")
public class EnvProdConfig {

}
@Profile(value="test")
public class EnvTestConfig {

}
@Profile(value="dev")
public class EnvDevConfig {

}
```
然后xml里配置
```xml
<context-param>
    <param-name>spring.profiles.default</param-name>
    <param-value>prod</param-value>
</context-param>
```

springboot
```
配置三个properties
application.properties
application-dev.properties
application-prod.properties
application-test.properties

然后在application.properites写入
//开发/测试/生产环境分别对应dev/test/prod
spring.profiles.active=dev
```






