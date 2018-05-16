package thread;

public class PrintOddEvenThread {

	public static void main(String[] args) {
		// Printer printer = new Printer();
		// ExecutorService e = Executors.newCachedThreadPool();
		// e.execute(new MyRunnable1(true, printer));
		// e.execute(new MyRunnable1(false, printer));
		// e.shutdown();

		String str = "abc";
		String str1 = "abc";
		String str3 = new String("abc");
		String intern = str.intern();
		System.out.println(str.equals(str3));
		System.out.println(str1==str3);
		System.out.println(str1==intern);
	}

}

class Printer {
	private Object lock = new Object();
	private volatile boolean isOdd = false;

	public void printEven(int number) throws InterruptedException {
		synchronized (lock) {
			while (isOdd == false) {
				lock.wait();
			}
			System.out.println("even : " + number);
			isOdd = false;
			lock.notifyAll();
		}
	}

	public void printOdd(int number) throws InterruptedException {
		synchronized (lock) {
			while (isOdd == true) {
				lock.wait();
			}
			System.out.println("odd : " + number);
			isOdd = true;
			lock.notifyAll();
		}
	}
}

class MyRunnable1 implements Runnable {

	private boolean isOdd;
	Printer printer;

	MyRunnable1(boolean isOdd, Printer printer) {
		this.isOdd = isOdd;
		this.printer = printer;
	}

	public void run() {
		int number = isOdd == true ? 1 : 2;
		while (number <= 10) {
			if (isOdd) {
				try {
					printer.printOdd(number);
				} catch (InterruptedException e) {
				}
			} else {
				try {
					printer.printEven(number);
				} catch (InterruptedException e) {
				}
			}
			number += 2;
		}
	}
}