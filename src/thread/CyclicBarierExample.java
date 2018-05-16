package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarierExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service = null;
		CyclicBarrier c = new CyclicBarrier(4, () -> System.out.println("All of my 4 workers are ready to do it now"));
		try {
			service = Executors.newFixedThreadPool(4);
			for (int i = 0; i < 4; i++) {
				Worker1 w = new Worker1();
				service.submit(() -> w.start(c));
			}
		} finally {
			if (service != null)
				service.shutdown();
		}
	}

}

class Worker1 {
	public void start(CyclicBarrier c) {
		try {
			System.out.println("I am ready");
			c.await();
			System.out.println("Go!");
		} catch (BrokenBarrierException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}