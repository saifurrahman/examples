package thread;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		ProducerConsumer pc = new ProducerConsumer();
		
		service.execute(() -> pc.produce());
		service.execute(() -> pc.consume());
		service.shutdown();
	}

}

class ProducerConsumer {
	private LinkedList<Integer> list = new LinkedList<>();
	private int capacity = 2;

	public void produce() {
		int value = 0;
		while (true) {
			synchronized (this) {
				while (list.size() == capacity)
					try {
						wait();
						System.out.println("Producer produced-" + value);
						list.add(value++);
						notifyAll();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void consume() {
		while (true) {
			synchronized (this) {
				while (list.size() == 0)
					try {
						wait();
						int val = list.removeFirst();
						System.out.println("Consumer consumed-" + val);
						
						notifyAll();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
