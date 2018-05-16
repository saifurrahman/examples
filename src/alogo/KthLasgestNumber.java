package alogo;

import java.util.Arrays;

public class KthLasgestNumber {

	public static void main(String[] args) {
		int[] p = { 6, 3, 25, 59, 20, 5, 2, 9 };

		System.out.println("K th largest number is -->" + findKthLargestNumber(p, 2));

		Arrays.sort(p);
		Arrays.stream(p).forEach(i -> System.out.println(i));
	}

	private static int findKthLargestNumber(int[] p, int k) {

		int length = p.length;
		int n = 0;
		for (int i = 0; i < length; i++) {

			for (int j = 0; j < length - k - 1; j++) {

				if (p[i] > p[j]) {
					n = p[i];
				}
			}

		}

		return n;
	}
}
