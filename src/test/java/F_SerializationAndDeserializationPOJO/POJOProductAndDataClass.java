package F_SerializationAndDeserializationPOJO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class POJOProductAndDataClass {
	
	private String name;
	private POJOData data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public POJOData getData() {
		return data;
	}
	public void setData(POJOData data) {
		this.data = data;
	}
	

}
