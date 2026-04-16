package E_SerializationAndDeserialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import F_SerializationAndDeserializationPOJO.Employee;

public class DeserializationClass1 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Employee emp = null;
	   try {
			
			FileInputStream fileIn = new FileInputStream("C:\\Users\\rahulkumar02\\eclipse-workspace\\APITestingRestAssuredPractice\\src\\test\\resources\\char.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			emp = (Employee) in.readObject();
			in.close();
			fileIn.close();
		}
		finally {
			System.out.println("Deserialization Employee.....");
			System.out.println("Name of Employee: " + emp.getName());
			System.out.println("Name of Employee: " + emp.getAddress());
			
		}

	}

}
