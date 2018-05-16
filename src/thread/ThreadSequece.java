package thread;

public class ThreadSequece {

	public static void main(String[] args) throws InterruptedException {

//		Thread t1 = new Thread(() -> new ObjOne().m1());
//		Thread t2 = new Thread(() -> new ObjTwo().m1());
//		Thread t3 = new Thread(() -> new ObjThree().m1());
//		t1.start();
//		// t1.join();
//		t2.start();
//		// t2.join();
//		t3.start();
		new A().start();
	}

}
class A extends Thread{
	
}
class ObjOne {

	public void m1() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1");

	}
}

class ObjTwo {
	public void m1() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2");
	}
}

class ObjThree {
	public void m1() {

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("3");
	}
}