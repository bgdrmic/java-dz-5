package hr.fer.zemris.java.custom.collections;

/**
 * {@code Processor} is an interface which represents a model of an object capable
 * of performing some operation on the passed object.
 * 
 * @author Božidar Grgur Drmić 1191241187
 *
 */
public interface Processor<T> {

	/**
	 * A method which processes some object.
	 * 
	 * @param value The object the process is executed upon.
	 */
	void process(T value);
}
