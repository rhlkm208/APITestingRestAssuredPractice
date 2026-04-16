package E_SerializationAndDeserialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import F_SerializationAndDeserializationPOJO.Employee;

public class SerializationClass {

	public static void main(String[] args) {
	
		Employee emp = new Employee();
		emp.setName("Rahul Kumar");
		emp.setAddress("Sitamarhi");
		
//		emp.name ="Rahul Kumar";
//		emp.address ="Sitamarhi";
		
		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\rahulkumar02\\eclipse-workspace\\APITestingRestAssuredPractice\\src\\test\\resources\\char.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(emp);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in char.txt file");
		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}

}
