package oops;

public class Test {

	public void main(String[] args) {

	}

}

class A {
	int a = 10;

	public int m(int a) {

		System.out.println("A");
		return 0;
	}

	public int m(int b,int a) {

		System.out.println("A");
		return 1;
	}
}

class B extends A {

	public void m() {

		System.out.println("B");
	}
}

interface I1 {
	public void m();
}

interface I2 {
	public void m();
}