# 线程的6种状态
1. 初始(NEW)：新创建了一个线程对象，但还没有调用start()方法。
2. 运行(RUNNABLE)：Java线程中将就绪（ready）和运行中（running）两种状态笼统的成为“运行”。
线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，等待被线程调度选中，获取cpu 的使用权，此时处于就绪状态（ready）。就绪状态的线程在获得cpu 时间片后变为运行中状态（running）。
3.阻塞(BLOCKED)：表线程阻塞于锁。
4.等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
5.超时等待(TIME_WAITING)：该状态不同于WAITING，它可以在指定的时间内自行返回。
6. 终止(TERMINATED)：表示该线程已经执行完毕。

# wait()和sleep()区别

1. wait()是object的方法,sleep()是Thread的方法
2. wait()释放锁,sleep()不释放锁
3. wait()需要采用notify()和notifyAll()唤醒
4. wait()通常被用于线程间交互，sleep()通常被用于暂停执行。

# volatile底层实现原理

### 1.定义
java编程语言允许线程访问共享变量，为了确保共享变量能够被准确和一致的更新，线程应该通过排他锁获得这个变量。java提供了volatile，在某些情况下比锁更加方便。如果一个字段被声明成volatile，java线程内存模型确保所有线程看到的这个变量的值是一致的。

### 2.实现原理

标有volatile的变量在进行写操作时，会在前面加上lock质量前缀。

而lock指令前缀会做如下两件事

1. 将当前处理器缓存行的数据写回到内存。lock指令前缀在执行指令的期间，会产生一个lock信号，lock信号会保证在该信号期间会独占任何共享内存。lock信号一般不锁总线，而是锁缓存。因为锁总线的开销会很大。

2. 将缓存行的数据写回到内存的操作会使得其他CPU缓存了该地址的数据无效。

### 3.volatile总结
**volatile经典总结:** volatile仅仅用来保证该变量对线程的可见性,但不保证原子性
- 保证该变量对线程的可见性:在多线程环境下,当这个变量修改时,所有的线程都会知道该变量被修改了。
>使用案例:ConcurrentHashMap中对Node类重写,通过volatile修饰next来实现每次获取都是最新设置的值
- 不保证原子性:修改变量(赋值)实质上是在JVM中分了好几步,而在这几步内(从装载变量到修改),它是不安全的。

使用了**volatile修饰的变量保证了三点**：

- 一旦你完成写入，任何访问这个字段的线程将会得到最新的值

- 在你写入前，会保证所有之前发生的事已经发生，并且任何更新过的数据值也是可见的，因为内存屏障会把之前的写入值都刷新到缓存。

- volatile可以防止重排序(重排序指的就是：程序执行的时候，CPU、编译器可能会对执行顺序做一些调整，导致执行的顺序并不是从上往下的。从而出现了一些意想不到的效果)。而如果声明了volatile，那么CPU、编译器就会知道这个变量是共享的，不会被缓存在寄存器或者其他不可见的地方。

# ThreadLocal（线程局部变量） 是什么底层如何实现

 ThreadLocal不是为了解决多线程访问共享变量,而是为每个线程创建一个单独的变量副本,提供了保持对象的方法和避免参数传递的复杂性。
 > https://blog.csdn.net/lovesomnus/article/details/64441426

假设有这样一个数据库链接管理类，这段代码在单线程中使用是没有任何问题的，但是如果在多线程中使用呢？很显然，在多线程中使用会存在线程安全问题：第一，这里面的2个方法都没有进行同步，很可能在openConnection方法中会多次创建connect；第二，由于connect是共享变量，那么必然在调用connect的地方需要使用到同步来保障线程安全，因为很可能一个线程在使用connect进行数据库操作，而另外一个线程调用closeConnection关闭链接。

> ThreadLocal设计的目的就是为了能够在当前线程中有属于自己的变量，并不是为了解决并发或者共享变量的问题


# 原子性
>线程原子性操作，也就是在修改时我们只需要保证它的那个瞬间是安全的即可，经过相应的包装后可以再处理对象的并发修改
举例：
【原子性】:count++，先读取，后自增，再赋值。如果该操作是原子性的，那么就可以说线程安全了(因为没有中间的三部环节，一步到位。原子性就是执行某一个操作是不可分割的
```java
public class AtomicIntegerDemo {

    /**
     * 常见的方法列表
     * @see AtomicInteger#get()             直接返回值
     * @see AtomicInteger#getAndAdd(int)    增加指定的数据，返回变化前的数据
     * @see AtomicInteger#getAndDecrement() 减少1，返回减少前的数据
     * @see AtomicInteger#getAndIncrement() 增加1，返回增加前的数据
     * @see AtomicInteger#getAndSet(int)    设置指定的数据，返回设置前的数据
     * 
     * @see AtomicInteger#addAndGet(int)    增加指定的数据后返回增加后的数据
     * @see AtomicInteger#decrementAndGet() 减少1，返回减少后的值
     * @see AtomicInteger#incrementAndGet() 增加1，返回增加后的值
     * @see AtomicInteger#lazySet(int)      仅仅当get时才会set
     * 
     * @see AtomicInteger#compareAndSet(int, int) 尝试新增后对比，若增加成功则返回true否则返回false
     */
    public final static AtomicInteger TEST_INTEGER = new AtomicInteger(1);

    public static void main(String []args) {

         for(int i = 0 ; i < 10 ; i++) { //开启10个线程

             new Thread() {
                 public void run() {
                     try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int now = TEST_INTEGER.incrementAndGet(); //自增
                    System.out.println(Thread.currentThread().getName() + " get value : " + now);
                 }
             }.start();
         }
    }
}
```
看一下结果
```
Thread-3 get value : 4 
Thread-7 get value : 5 
Thread-9 get value : 9 
Thread-4 get value : 6 
Thread-0 get value : 3 
Thread-1 get value : 8 
Thread-5 get value : 11 
Thread-8 get value : 7 
Thread-2 get value : 10 
Thread-6 get value : 2
```
可以看出，10个线程之间是线程安全的，并没有冲突。也就是说，我们使用原子性操作类去操作基本类型int就可以解决线程安全问题，一个线程在操作的时候，会对其它线程进行排斥，不用我们手动去使用synchronized实现互斥操作了。

# 编写一个死锁
```java
public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        // 得到left锁
        synchronized (left) {
            // 得到right锁
            synchronized (right) {
                doSomething();
            }
        }
    }

    public void rightLeft() {
        // 得到right锁
        synchronized (right) {
            // 得到left锁
            synchronized (left) {
                doSomethingElse();
            }
        }
    }
}
```
- 线程A调用leftRight()方法，得到left锁
- 同时线程B调用rightLeft()方法，得到right锁
- 线程A和线程B都继续执行，此时线程A需要right锁才能继续往下执行。此时线程B需要left锁才能继续往下执行。
- 但是：线程A的left锁并没有释放，线程B的right锁也没有释放。
- 所以他们都只能等待，而这种等待是无期限的-->永久等待-->死锁

# 避免死锁
- 固定加锁的顺序(针对锁顺序死锁)
- 开放调用(针对对象之间协作造成的死锁)
- 使用定时锁-->tryLock()
- 如果等待获取锁时间超时，则抛出异常而不是一直等待！ 

# synchronized修饰普通方法和static方法有什么不同
### 1、对象锁
当一个对象中有同步方法或者同步块，线程调用此对象进入该同步区域时，必须获得对象锁。如果此对象的对象锁被其他调用者占用，则进入阻塞队列，等待此锁被释放（同步块正常返回或者抛异常终止，由JVM自动释放对象锁）。
注意，方法锁也是一种对象锁。当一个线程访问一个带synchronized方法时，由于对象锁的存在，所有加synchronized的方法都不能被访问（前提是在多个线程调用的是同一个对象实例中的方法）。
synchronized{普通方法}依靠**对象锁**工作，多线程访问synchronized方法，一旦某个进程抢得锁之后，其他的进程只有排队对待。
```java
//写法一修饰的是一个方法，写法二修饰的是一个代码块，但写法一与写法二是等价的，都是锁定了整个方法时的内容。
public class object {
	public synchronized void method1(){
		System.out.println("我是对象锁也是方法锁");
	}

    public void method2(){
		synchronized(this){
			System.out.println("我是对象锁");
		}
	}
}

```

### 2、类锁
一个class其中的静态方法和静态变量在内存中只会加载和初始化一份，所以，一旦一个静态的方法被申明为synchronized，此类的所有的实例化对象在调用该方法时，共用同一把锁，称之为类锁。
synchronized{静态方法},静态方法是属于类的而不属于对象的。同样的，synchronized修饰的静态方法锁定的是这个类的所有对象。
```java
public class object {
	public static synchronized void method1(){
		System.out.println("我是第一种类锁");
	}

    public static void method2(){
		synchronized (object.this) {
			System.out.println("我是第二种类锁");
		}
	}
}
```

# 如果同步块内的线程抛出异常会发生什么？
线程会释放锁,下一个线

# Synchronized优化后的锁机制简单介绍一下，包括自旋锁、偏向锁、轻量级锁、重量级锁？

自旋锁：

线程自旋说白了就是让cup在做无用功，比如：可以执行几次for循环，可以执行几条空的汇编指令，目的是占着CPU不放，等待获取锁的机会。如果旋的时间过长会影响整体性能，时间过短又达不到延迟阻塞的目的。

偏向锁

偏向锁就是一旦线程第一次获得了监视对象，之后让监视对象“偏向”这个线程，之后的多次调用则可以避免CAS操作，

说白了就是置个变量，如果发现为true则无需再走各种加锁/解锁流程。

轻量级锁：

轻量级锁是由偏向所升级来的，偏向锁运行在一个线程进入同步块的情况下，当第二个线程加入锁争用的时候，偏向锁就会升级为轻量级锁；

重量级锁

重量锁在JVM中又叫对象监视器（Monitor），它很像C中的Mutex，除了具备Mutex(0|1)互斥的功能，它还负责实现了Semaphore(信号量)的功能，也就是说它至少包含一个竞争锁的队列，和一个信号阻塞队列（wait队列），前者负责做互斥，后一个用于做线程同步。

