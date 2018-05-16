package alogo;

public class Fibonacci {

	public static void main(String[] args) {
		long i = fibonacci(10);
		System.out.println(i);

	}

	public static long fibonacci(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}
		
		return fibonacci(number - 1) + fibonacci(number - 2);
	}

}
