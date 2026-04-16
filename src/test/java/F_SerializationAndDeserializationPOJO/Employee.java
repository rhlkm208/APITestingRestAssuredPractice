package F_SerializationAndDeserializationPOJO;

import java.io.Serializable;

public class Employee implements Serializable {
	
//	public String name;
//	public String address;
	
	
	private String name;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
