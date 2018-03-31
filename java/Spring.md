# ApplicationContext 
`ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");`

ApplicationContext : IOC容器

ClassPathXmlApplication :   是ApplicationContext接口的实现类.

# Spring支持3种注入方式

>属性注入 : setter方法注入,写在applicationContext.xml的\<bean id="" class=""> 里
>构造器注入 : \<construtor-arg>
>工厂方法注入(不推荐)

## 现在用注解,而不是用xml来写

@Component :




