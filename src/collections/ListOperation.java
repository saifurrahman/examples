package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListOperation {

	public static void main(String[] args) {
		List<Person> list = new ArrayList<Person>();

		list.add(new Person(2, "g"));
		list.add(new Person(3, "d"));
		list.add(new Person(5, "p"));
		list.add(new Person(4, "x"));
		list.add(new Person(1, "a"));
		list.add(new Person(1, "C"));
		System.out.println(list);

		Collections.sort(list, (o1, o2) -> o1.getName().compareTo(o2.getName()));

		System.out.println(list);
		Collections.sort(list, (o1, o2) -> o1.getId() - o2.getId());

		System.out.println(list);

	}

}
