package chap03;

public class Z_SingtonDefaultClass {
	private static Z_SingtonDefaultClass ourInstance = new Z_SingtonDefaultClass();
	
	public static Z_SingtonDefaultClass getInstance() {
		return ourInstance;
	}
	
	private Z_SingtonDefaultClass() {
	}
}
