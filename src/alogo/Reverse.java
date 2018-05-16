package alogo;

public class Reverse {

	public static void main(String[] args) {
		int[] array = { 2, 4, 1, 5, 6, 7, 10 };
		int length = array.length;
		for (int i = 0; i < length / 2; i++) {

			for (int j = length - 1; j > length / 2; j--) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}

		}
		for (int i = 0; i < length; i++)
			System.out.println(array[i]);
		
		

	}

}
