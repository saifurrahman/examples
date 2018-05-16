package thread;

public class SingletonTest {
	public static void main(String[] args) {
		MySinglton instance1 = MySinglton.getIstance();
		System.out.println(instance1.getCount());
		MySinglton instance2 = MySinglton.getIstance();
		System.out.println(instance2.getCount());
	}
}

class MySinglton {

	private static MySinglton instance;
	private static Object mutex = new Object();
	private int count = 0;

	private MySinglton() {
		System.out.println("Singlteon Object Created");
		count++;
	}

	public static MySinglton getIstance() {
		
		synchronized (mutex) {
			if (instance == null)
				instance = new MySinglton();
		}

		return instance;

	}

	public int getCount() {
		return count;
	}

}