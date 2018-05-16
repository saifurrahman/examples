package oops;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SerializationTest {

	public static void main(String[] args) {
		Serz s = new Serz(1, "abc", new ThirdPart("xyz"));
		String filename = "file.ser";
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(s);
			out.close();
			file.close();
			System.out.println("Object has been serialized");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Serz z = null;
		try {

			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			z = (Serz) in.readObject();

			in.close();
			file.close();
			System.out.println("Object has been deserialized ");
			System.out.println("a = " + z.id);
			System.out.println("b = " + z.name);
			if (z.value != null) {
				System.out.println("b = " + z.value.value);
			} else {
				System.out.println("ThirdPart=" + z.value);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}

	}

}

class ThirdPart {
	String value;

	public ThirdPart(String value) {
		this.value = value;
	}

}

class Serz implements Externalizable {

	int id;
	String name;
	ThirdPart value;

	public Serz(int id, String name, ThirdPart value) {
		super();
		this.id = id;
		this.name = name;
		ThirdPart part = new ThirdPart(value.value);
		this.value = part;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(id);
		out.writeObject(name);
		out.writeObject(this.value.value);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

		this.id = in.readInt();
		this.name = (String) in.readObject();
//		String str = (String) in.readObject();
//		this.value = new ThirdPart(str);

	}

}
