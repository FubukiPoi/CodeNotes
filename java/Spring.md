# ApplicationContext 

`ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");`

ApplicationContext : IOC容器

ClassPathXmlApplication :   是ApplicationContext接口的实现类.

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

# Spring事务










