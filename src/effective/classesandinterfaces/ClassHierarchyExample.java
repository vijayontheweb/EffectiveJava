package effective.classesandinterfaces;

/**
 * To transform a tagged class into a class hierarchy, first define an abstract
 * class containing an abstract method for each method in the tagged class whose
 * behavior depends on the tag value. In the Shape class, there is only one
 * such method, which is area. This abstract class is the root of the class
 * hierarchy. If there are any methods whose behavior does not depend on the
 * value of the tag, put them in this class. Similarly, if there are any data
 * fields used by all the flavors, put them in this class. There are no such
 * flavor-independent methods or fields in the Shape class. Next, define a
 * concrete subclass of the root class for each flavor of the original tagged
 * class. In our example, there are two: circle and rectangle. Include in each
 * subclass the data fields particular to its flavor. In our example, radius is
 * particular to circle, and length and breadth are particular to rectangle. Also
 * include in each subclass the appropriate implementation of each abstract
 * method in the root class.
 * 
 * @author Vijay
 *
 */
public class ClassHierarchyExample {

	public static void main(String[] args) {
		Shape rect = new Rectangle(5, 6);
		Shape circle = new Circle(6);
		Shape square = new Square(7);
		System.out.println(rect.area());
		System.out.println(circle.area());
		System.out.println(square.area());
	}

}

abstract class Shape {
	abstract double area();
}

class Rectangle extends Shape {
	final double length, breadth;

	Rectangle(double length, double breadth) {
		this.length = length;
		this.breadth = breadth;
	}

	double area() {
		return length * breadth;
	}
}

class Circle extends Shape {
	final double radius;

	Circle(double radius) {
		this.radius = radius;
	}

	double area() {
		return 3.14 * radius * radius;
	}
}

class Square extends Rectangle {
	Square(double side) {
		super(side, side);
	}
}
