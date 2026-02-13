package TestAPIPractice;

public class MethodChaining {
	
	public MethodChaining methodA() {
		System.out.println("Hello MethodA");
		return this;
	}

	public MethodChaining methodB() {
		System.out.println("Hello MethodB");
		return this;
	}
	
	public MethodChaining methodC() {
		System.out.println("Hello MethodC");
		return this;
	}
	
	public static void main(String[] args) {
		MethodChaining obj = new MethodChaining();
		obj.methodA().methodB().methodC();
		

	}

}
