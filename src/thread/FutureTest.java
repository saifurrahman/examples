package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		Worker w = new Worker();
		Future<String> future = service.submit(() -> w.call());
		try {
			System.out.println(future.isDone());
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("");
		
	}

}

class Worker implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		return "value";
	}

}