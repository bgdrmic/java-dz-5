package hr.fer.zemris.java.custom.collections;

/**
 * {@code List} is an interface which extends {@code Collection} and represents a
 * list structure of some collection.
 *  
 * @author Božidar Grgur Drmić
 *
 */

public interface List<T> extends Collection<T> {

	/**
	 * Returns the object that is stored in the list at soem position.
	 * Valid indices are from {@literal 0} to {@code size-1}. If index is invalid,
	 * {@code IndexOutOfBoundsException} is thrown. 
	 * 
	 * @param index Index of an object.
	 * @return Returns an object at index {@code index}.
	 * @throws IndexOutOfBounds if index is invalid.
	 */
	T get(int index);
	
	/**
	 * Inserts (does not overwrite) the given value at the given position in the list
	 * (before actual insertion elements at position and at greater positions are shifted
	 * one place toward the end). The legal positions are from {@literal 0} to {@code size} (both are included).
	 * If position is invalid, {@code IndexOutOfBoundsException} is thrown.
	 * 
	 * @param value An object which is inserted.
	 * @param position Position at which an object is inserted.
	 * @throws IndexOutOfBoundsException if position is invalid.
	 */
	void insert(T value, int position);
	
	/**
	 * Searches the collection and returns the index of the first occurrence of the
	 * given value or {@literal -1} if the value is not found. {@code Null} can be argument.
	 * The equality is determined using the {@code equals} method.
	 * 
	 * @param value An object we search for.
	 * @return Returns an index of the object. {@literal -1} if it isn't contained.
	 */
	int indexOf(Object value);
	
	/**
	 * Removes element at specified index from collection. Element that was
	 * previously at location index+1 after this operation is on location index, etc.
	 * Legal indexes are from {@literal 0} to {@code size-1}. In case of invalid index,
	 * {@code IndexOutOfBoundsException} is thrown.
	 * 
	 * @param index The index of an object to be removed.
	 * @throws IndexOutOFBoundsException if index is invalid.
	 */
	void remove(int index);
}
