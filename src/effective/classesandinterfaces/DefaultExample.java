package effective.classesandinterfaces;

public class DefaultExample implements A, B{

	public static void main(String[] args) {
		DefaultExample example = new DefaultExample();
		example.defaultMethod();
		example.anotherDefaultMethod();
		A.staticMethod();
	}

	@Override
	public void defaultMethod() {		
		A.super.defaultMethod();
		B.super.defaultMethod();
	}

}

interface A {
	default void defaultMethod() {
		System.out.println("A's defaultMethod called");
	}
	
	default void anotherDefaultMethod() {
		System.out.println("A's anotherDefaultMethod called");
	}
	
	static void staticMethod() {
		System.out.println("A's staticMethod called");
	}
}

interface B {
	default void defaultMethod() {
		System.out.println("B's defaultMethod called");
	}
}
