package effective.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Interesting links :
 * https://medium.com/omnius/wildcards-in-java-generics-part-1-3-dd2ce5b0e59a
 * http://tutorials.jenkov.com/java-generics/wildcards.html
 * https://medium.com/@isuru89/java-producer-extends-consumer-super-9fbb0e7dd268
 * http://bayou.io/draft/Capturing_Wildcards.html
 * 
 * In invariance, “list of cars” is not a “list of vehicles”, and “list of
 * vehicles” is not a “list of cars”, though Car is a Vehicle. You cannot assign
 * each other because the parameter type is "invariant" on a specified List<E>
 * type.
 * 
 * In Java generics, there are two types of wildcards: extends wildcard and
 * super wildcard. i.e. Covariance (? extends E) and Contravariance (? super E).
 * And this is where the title comes (Producer Extends, Consumer Super).
 * Covariance exhibits the producer behavior from that type’s perspective.
 * Contravariance exhibits consumer behavior from that type’s perspective
 * 
 * In general, if a structure contains elements with a type of the form ?
 * extends E, we can get elements out of the structure, but we cannot put
 * elements into the structure. To put elements into the structure we need
 * another kind of wildcard
 * 
 * The quizzical phrase ? super T means that the destination list may have
 * elements of any type that is a supertype of T, just as the source list may
 * have elements of any type that is a subtype of T.
 * 
 * The Get and Put Principle: use an extends wildcard when you only get values
 * out of a structure, use a super wildcard when you only put values into a
 * structure, and don’t use a wildcard when you both get and put.
 * 
 * Wild types are quite different from concrete types. We cannot new a wild
 * type, like new ArrayList<?>(). We cannot inherit from a wild type, like
 * interface MyList extends List<?>. We muse use concrete types in these places.
 * 
 * @author Vijay
 *
 */
public class WildcardExample {
	public static void main(String[] args) {
		List<Number> numList = Arrays.asList(1, 2, 3.14);
		List<Integer> intList = Arrays.asList(1, 2, 3);
		List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
		extendsMethod(numList);
		extendsMethod(intList);
		extendsMethod(doubleList);
		List<Object> objEmptyList = new ArrayList<Object>();
		List<Number> numEmptyList = new ArrayList<Number>();
		superMethod(objEmptyList);
		superMethod(numEmptyList);
	}

	static void extendsMethod(List<? extends Number> extendsList) {
		// Getting IS allowed
		for (Number element : extendsList) {
			System.out.println(element);
		}
		/*
		 * an important rule you need to keep in mind about the extends wildcard is that
		 * you cannot add elements to the collection declared with the extends wildcard.
		 * 
		 */
		/*
		 * List<Integer> intList = Arrays.asList(1, 2, 3); extendsList.addAll(intList);
		 * 
		 * List<? extends Number> numbers = new ArrayList<Integer>();
		 * numbers.add(123);   // COMPILE ERROR
		 */
	}

	/**
	 * You can now call superMethod() with either a List<Number>, or a List typed to
	 * a superclass of Number.
	 * 
	 * @param superList
	 */
	static void superMethod(List<? super Number> superList) {
		// Getting is NOT allowed
		/*
		 * for (E element : superList) { System.out.println(element); }
		 */
		// Setting IS allowed
		List<Number> numList = Arrays.asList(1, 2, 3.3);
		superList.addAll(numList);
		List<Integer> intList = Arrays.asList(1, 2, 3);
		superList.addAll(intList);
		List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
		superList.addAll(doubleList);
	}
}
