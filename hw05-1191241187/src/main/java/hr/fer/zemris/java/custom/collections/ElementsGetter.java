package hr.fer.zemris.java.custom.collections;

/**
 * {@code ElementsGetter} is an interface which represents an iterator for 
 * some {@code Collection} of <T> type elements.
 * 
 * @author Božidar Grgur Drmić
 * @param <T> The type of elements this iterator retrieves.
 *
 */
public interface ElementsGetter<T> {

	/**
	 * A method which returns true only if the {@code ElementGetter} hasn't
	 * already read all the existing elements. 
	 * 
	 * @return Returns true if there are elements left to read. False otherwise.
	 */
	boolean hasNextElement();
	
	/**
	 * A method which reads the next element.
	 * 
	 * @return Returns the next element.
	 * @throws {@code IndexOutOfBoundsException} if there are no more elements to read.
	 */
	T getNextElement();
	
	
	/**
	 * A method which executes the {@link process} method of some {@link Processor}
	 * upon the remaining elements which haven't been read.
	 * This {@code Processor} must be a {@code Processor} for <T> or more general type elements.
	 * 
	 * @param p A {@code Processor} which {@code process} method is executed.
	 */
	default void processRemaining(Processor<? super T> p) {
		while(this.hasNextElement()) {
			p.process(this.getNextElement());
		}
	}
}
