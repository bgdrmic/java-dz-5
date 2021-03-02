package hr.fer.zemris.java.custom.collections;

/**
 * {@code Tester} is an interface which represent a test for determining whether
 * some object is or is not acceptable.
 * 
 * @author Božidar Grgur Drmić
 * 
 */
public interface Tester<T> {

	/**
	 * A method which determines whether the object is acceptable.
	 * @param value An object which is tested.
	 * @return Returns {@code true} only if {@code value} is acceptable.
	 */
	boolean test(T value);
}
