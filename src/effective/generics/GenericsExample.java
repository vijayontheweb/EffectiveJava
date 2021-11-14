package effective.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Generics are implemented by erasure because the types
 * List<Integer>,List<String>, and List<List<String>> are all represented at
 * run-time by the same type, List.
 * 
 * The term erasure is a slight misnomer, since the process erases type
 * parameters but adds casts.
 * 
 * Cast-iron guarantee: the implicit casts added by the compilation of generics
 * never fail. There is also some fine print on this guarantee: it applies only
 * when no unchecked warnings have been issued by the compiler.
 * 
 * Java reifies array component types but does not reify list element types (or
 * other generic types).
 * 
 * @author Vijay
 *
 */
public class GenericsExample {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		int sum = 0;
		for (int number : numbers) {
			sum += number;
		}
		assert sum == 6;
		System.out.println(sum);

		// Dot product of 2 vectors
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(4, 5, 6);
		Iterator<Integer> iterator1 = numbers1.iterator();
		Iterator<Integer> iterator2 = numbers2.iterator();
		int dotProduct = 0;
		while (iterator1.hasNext() && iterator2.hasNext()) {
			int number1 = iterator1.next();
			int number2 = iterator2.next();
			dotProduct += number1 * number2;
		}
		System.out.println(dotProduct);

		// Generic Method call using Array
		List<Integer> ints1 = arrayToList(new Integer[] { 1, 2, 3 });
		List<String> words1 = arrayToList(new String[] { "hello", "world" });
		// Generic Method call using varargs
		List<Integer> ints2 = varargsToList(1, 2, 3);
		List<String> words2 = varargsToList("hello", "world");

		List<Integer> thisList = new ArrayList<Integer>();
		varargsToThisList(thisList, 1, 2);
		varargsToThisList(thisList, 3, 4);
		/*
		 * Assertions are enabled by invoking the JVM with the -ea or -enableassertions
		 * flag.
		 */
		assert thisList.toString().equals("[1, 2, 3, 4]");
		System.out.println(thisList);

		GenericsExample example = new GenericsExample();
		/*
		 * To invoke a generic method, its type-parameters must be substituted with
		 * actual types, either explicitly or by inference
		 */
		List<Number> numberList1 = example.<Number>newList();
		List<Number> numberList2 = example.newList();
	}

	/**
	 * The static method arrayToList accepts an array of type T[] and returns a list
	 * of type List<T>, and does so for any type T. This is indicated by writing <T>
	 * at the beginning of the method signature, which declares T as a new type
	 * variable. A method which declares a type variable in this way is called a
	 * generic method. The scope of the type variable T is local to the method
	 * itself; it may appear in the method signature and the method body, but not
	 * outside the method.
	 * 
	 * @param tArr
	 * @return
	 */
	public static <T> List<T> arrayToList(T[] tArr) {
		List<T> tList = new ArrayList<T>();
		for (T tElt : tArr) {
			tList.add(tElt);
		}
		return tList;
	}

	/**
	 * Packing the arguments into an array is cumbersome. The vararg feature permits
	 * a special, more convenient syntax for the case in which the last argument of
	 * a method is an array. To use this feature, we replace T[] with T… in the
	 * method declaration
	 * 
	 * @param vArr
	 * @return
	 */
	public static <T> List<T> varargsToList(T... vArr) {
		List<T> tList = new ArrayList<T>();
		for (T tElt : vArr) {
			tList.add(tElt);
		}
		return tList;
	}

	public static <T> List<T> varargsToThisList(List<T> tList, T... vArr) {
		for (T tElt : vArr) {
			tList.add(tElt);
		}
		return tList;
	}

	/**
	 * A generic method can also be viewed as a code template
	 * 
	 * @return
	 */
	<T> List<T> newList() {
		return new ArrayList<T>();
	}

}
