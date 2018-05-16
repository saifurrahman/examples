package collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapOperation {

	public static void main(String[] args) {
		Map<Person, Integer> map = new LinkedHashMap<>();
		map.put(new Person(1, "a"), 300);
		map.put(new Person(3, "p"), 400);
		map.put(new Person(4, "x"), 500);
		map.put(new Person(2, "d"), 200);

		System.out.println(map);

		map.entrySet().stream().sorted((o1, o2) -> o1.getKey().getName().compareTo(o2.getKey().getName()))
				.forEach(o -> System.out.print(o.getKey()));

	}

}
