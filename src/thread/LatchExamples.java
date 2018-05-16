package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LatchExamples {

	public static void main(String[] args) throws InterruptedException {
		final int N = 5;

		CountDownLatch latch = new CountDownLatch(N);
		ExecutorService e = Executors.newFixedThreadPool(N);

		System.out.println("Please wait");

		for (int threadNo = 0; threadNo < N; ++threadNo)
			e.execute(new MyRunnable(latch, threadNo));

		latch.await(); // Wait for all to finish
		System.out.println("All done");
		
		e.shutdown();

	}
	
	public static void main(String[] args,int i) {
		
	}

}

class MyRunnable implements Runnable {
	private final CountDownLatch latch;
	private final int i;

	MyRunnable(CountDownLatch passedLatch, int threadNo) {
		this.latch = passedLatch; // Keep a reference to latch
		this.i = threadNo; // Assigned a thread number
	}

	public void run() {
		try {
			System.out.println(i + " is working");
			Thread.sleep((int) (Math.random() * 2000) + 500);
			System.out.println(i + " done working");
			latch.countDown(); // Send "I'm done" signal to latch
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}