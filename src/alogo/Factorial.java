package alogo;

import java.util.HashMap;
import java.util.Map;

public class Factorial {

	public static void main(String[] args) {
		Map<Integer, Long> map = new HashMap<>();
		long fact = factorial(20, map);
		System.out.println(fact);

	}

	private static long factorial(int i, Map<Integer, Long> map) {
		if (i == 0)
			return 1;
		if (i == 1)
			return 1;
		else if (map.containsKey(i)) {
			long value = i * factorial(i - 1, map);
			map.put(i, value);
		}

		return map.get(i);

	}

}
