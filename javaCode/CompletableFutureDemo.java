import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//java异步,async里执行不影响后续方法的执行，无需同步等待
		async("111");
		System.out.println("CompletableFuture");
	}
	
	public static void async(String str){
		CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(str);
		});
	}

}
