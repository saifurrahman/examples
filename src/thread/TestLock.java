package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyLock {
	private long c = 0;

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	public void inc1() {
		synchronized (lock1) {
			c++;
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inc2() {
		synchronized (lock1) {
			c++;
		}
	}

	public long getC() {
		return c;
	}

}

public class TestLock {
	public static void main(String[] args) {
		MyLock l = new MyLock();
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(() -> l.inc1());

		service.execute(() -> l.inc2());

		System.out.println(l.getC());
		
		System.out.println(l.getC());
		service.shutdown();
	}
}