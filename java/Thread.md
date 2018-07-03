# 并发与并行

1. 并行:表示两个线程同时做事情

2. 并发:表示一会做这个事,一会做另一个事,存在调度。

# 线程池 
```java
public class threadPools {

	public static void main(String[] args) {
		//使用线程池花费 130ms
		threadPool1();
		//未使用线程池花费 7800ms
		threadPool2();
	}
	
	public static void threadPool1(){
		final CountDownLatch countDownLatch = new CountDownLatch(50000);
		long start = System.currentTimeMillis();
		Executor pool = Executors.newFixedThreadPool(10);
		for(int i = 0; i <50000; i++){
			pool.execute(new Runnable() {
				@Override
				public void run() {
					//System.out.println("hello");
					countDownLatch.countDown();
				}
			});
		}
		
		while(countDownLatch.getCount()!=0){}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	public static void threadPool2(){
		final CountDownLatch countDownLatch = new CountDownLatch(50000);
		long start = System.currentTimeMillis();
		for(int i = 0; i <50000; i++){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					//System.out.println("hello");
					countDownLatch.countDown();
				}
			});
			thread.start();
		}
		
		while(countDownLatch.getCount()!=0){}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
}
```