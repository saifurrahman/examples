package oops;

public class EqualObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Obj o1 = new Obj(1, "abc", "x");
		Obj o2 = new Obj(2, "abc", "x");

		System.out.println(o1.equals(o2));
		System.out.println(o1.hashCode());
		System.out.println(o2.hashCode());
	}

}

class Obj {

	int i;
	String firstName;
	String lastName;

	public Obj(int i, String name, String lname) {
		super();
		this.i = i;
		this.firstName = name;
		this.lastName = lname;
	}

	@Override
	public boolean equals(Object obj) {
		Obj o = (Obj) obj;
		return (this.firstName.equals(o.firstName)) && (this.lastName.equals(o.lastName));
	}

	@Override
	public int hashCode() {
		int i = this.firstName.hashCode()*1000 + this.lastName.hashCode();
		return i;
	}

}