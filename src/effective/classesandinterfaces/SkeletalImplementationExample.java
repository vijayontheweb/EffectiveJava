package effective.classesandinterfaces;

/**
 * 1.The class implementing the interface can forward invocations of interface
 * methods to a contained instance of a private inner class that extends the
 * skeletal implementation. This technique, known as simulated multiple
 * inheritance.
 * 
 * 2.First, study the interface and decide which methods are the primitives in
 * terms of which the others can be implemented. These primitives will be the
 * abstract methods in your skeletal implementation.
 * 
 * 2.Next, provide default methods in the interface for all of the methods that
 * can be implemented directly atop the primitives
 * 
 * Reference -> https://dzone.com/articles/favour-skeletal-interface-in-java
 * 
 * @author Vijay
 *
 */
public class SkeletalImplementationExample {
	public static void main(String[] args) {
		Car creta = new Creta();
		Car city = new City();
		
		city.start();
		city.run();
		city.stop();
		
		creta.start();
		creta.run();
		if(creta instanceof Accessories) {
			((Accessories) creta).describeMusic();
		}
		creta.stop();
		
		
	}
}

interface Car {
	default void start() {
		System.out.println("Car started");
	}

	default void stop() {
		System.out.println("Car stopped");
	}

	default void run() {
		System.out.println("Car running");
		describeEngine();
	}

	void describeEngine();
}

abstract class AbstractHondaEngine implements Car {
	public void describeEngine() {
		System.out.println("using iVTEC engine");
		describeCylinder();
		describeDisplacement();
	}

	abstract void describeCylinder();

	abstract void describeDisplacement();
}

abstract class AbstractHyundaiEngine implements Car {
	public void describeEngine() {
		System.out.println("using CRDi engine");
		describeCylinder();
		describeDisplacement();
	}

	abstract void describeCylinder();

	abstract void describeDisplacement();
}

class City implements Car {
	private class AbstractHondaEngineDelegator extends AbstractHondaEngine {
		void describeCylinder() {
			System.out.println("with 4 cylinder");
		}

		void describeDisplacement() {
			System.out.println("with 1497cc");
		}
	}

	AbstractHondaEngineDelegator hondaEngineDelegator = new AbstractHondaEngineDelegator();

	@Override
	public void start() {
		hondaEngineDelegator.start();
	}

	@Override
	public void stop() {
		hondaEngineDelegator.stop();
	}

	@Override
	public void describeEngine() {
		hondaEngineDelegator.describeEngine();
	}
}

class Accessories {
	void describeMusic() {
		System.out.println("with Bose music System");
	}
}

class Creta extends Accessories implements Car{
	private class AbstractHyundaiEngineDelegator extends  AbstractHyundaiEngine{
		void describeCylinder() {
			System.out.println("with 5 cylinder");	
		}
		
		void describeDisplacement() {
			System.out.println("with 1493cc");
		}
	}
	
	AbstractHyundaiEngineDelegator hyundaiEngineDelegator = new AbstractHyundaiEngineDelegator();
	
	@Override
	public void start() {
		hyundaiEngineDelegator.start();
	}

	@Override
	public void stop() {
		hyundaiEngineDelegator.stop();
	}	

	@Override
	public void describeEngine() {
		hyundaiEngineDelegator.describeEngine();
	}
	
}