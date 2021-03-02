package hr.fer.zemris.java.custom.collections;


/**
 * {@code Collection} is an interface which represents
 * a general collection of objects of type <T>.
 * 
 * @author Božidar Grgur Drmić 1191241187
 * @param <T> The type of objects stored in this {@code Collection}.
 */
public interface Collection<T> {
	
	/**
	 * Returns true if {@code Collection} contains no elements and false otherwise.
	 * 
	 * @return Returns true only if {@code Collection} is empty. False otherwise.
	 */
	default boolean isEmpty() {
		if(this.size() == 0) return true;
		return false;
	}
	
	/**
	 * Returns the number of currently stored objects in this {@code Collection}.
	 */
	int size();
	
	/**
	 * Adds the given object into this {@code Collection}.
	 * 
	 * @param value A reference to an object which is to be added.
	 */
	void add(T value);
	
	/**
	 * Returns true only if the {@code Collection} contains given value, as determined by {@link equals} method.
	 * 
	 * @param value An object whose affiliation is to be determined.
	 * @return Returns true if there is one. False otherwise.
	 */
	boolean contains(Object value);
	
	/**
	 * Returns true only if the {@code Collection} contains given value as determined by {@link equals} method
	 * and removes one occurrence of it (in this class it is not specified which one).
	 * 
	 * @param value A value one occurrence of which is to be removed.
	 * @return Returns true if such object was found. False otherwise.
	 */
	boolean remove(Object value);
	
	/**
	 * Allocates new array with size equals to the size of this {@code Collection},
	 * fills it with this collection's content and returns the array.
	 * This method never returns {@value null}.
	 * 
	 * @return An array of objects from the {@code Collection}.
	 */
	T[] toArray();
	
	/**
	 * Method calls {@link processor.process()} for each element of this {@code Collection}.
	 * The order in which elements will be sent is undefined in this class.
	 * @param processor A {@link Processor} which executes the processes.
	 */
	default void forEach(Processor<? super T> processor) {
		ElementsGetter<T> getter = this.createElementsGetter();		
		getter.processRemaining(processor);
	}
	
	/**
	 * Method adds into the current collection all elements from the given {@code Collection}.
	 * This {@code other} collection remains unchanged.
	 * The {@code other} collection must be an collection of at least {@code <T>} type elements.
	 * 
	 * @param other A {@code Collection} whose elements are added into the current one.
	 */
	default <S extends T>void addAll(Collection<S> other) {
		class AddAllProcessor implements Processor<T>{			
			@Override
			public void process(T value) {
				Collection.this.add(value);
			}			
		}
		other.forEach(new AddAllProcessor());	
	}
	
	/**
	 * Removes all elements from this collection.
	 */
	void clear();
	
	/**
	 * A method which creates a new {@code ElementsGetter}
	 * for the current {@code Collection}.
	 * @return Returns a reference to a newly created {@code ElementsGetter};
	 */
	ElementsGetter<T> createElementsGetter();

	/**
	 * A method which adds all the elements which suffice the criterion
	 * from some other {@code Collection} to the current one.
	 * The other collection must be an collection of at least {@code <T>} type elements.
	 * 
	 * @param col A reference to the other {@code Collection}.
	 * @param tester A reference to some {@link Tester} which defines the criterion.
	 */
	default <S extends T> void addAllSatisfying(Collection<S> col, Tester<? super T> tester) {
		class addAllSatisfyingProcessor implements Processor<T> {
			@Override
			public void process(T value) {
				if(tester.test(value)) Collection.this.add(value);	
			}
		}
		
		var getter = col.createElementsGetter();
		
		getter.processRemaining(new addAllSatisfyingProcessor());
	}
	
}
