package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * {@code ArrayIndexedCollection} which implements interface {@link List}
 * is a class that represents an array-backed collection of objects of some type. 
 * Duplicate elements are allowed, storage of null references is not allowed.
 * 
 * @author Božidar Grgur Drmić 1191241187
 * @param <T> The type of elements contained.
 */
public class ArrayIndexedCollection<T> implements List<T> {
	
	/**
	 * The default capacity of a collection.
	 */
	private final static int DEFAULT_CAPACITY = 16;
	
	/**
	 * A non-static variable which represents the size of the collection.
	 */
	private int size;
	
	/**
	 *  An array of {@code <T>} type references which represent
	 *  the elements of a collection.
	 */
	private T[] elements;
	
	/**
	 * A non-static variable which counts how many changes have been done
	 * to the collection.
	 */
	private long modificationCount;
	
	
	/**
	 * A private static class which represents an implementation of
	 * {@link ElementsGetter} interface specifically for {@code ArrayIndexedCollection} class.
	 */
	private class ArrayElementsGetter implements ElementsGetter<T>{
		private int cursor;
		private long savedModificationCount;
		
		private ArrayElementsGetter() {
			savedModificationCount = modificationCount;
		}
		
		private boolean collectionWasModified() {
			return savedModificationCount != modificationCount;
		}
		
		public boolean hasNextElement() {
			if(collectionWasModified()) {
				throw new ConcurrentModificationException("The collection has been changed");
			}
			if(cursor == elements.length) return false;
			if(elements[cursor] == null) return false;
			return true;
		}
		
		public T getNextElement() {
			if(!this.hasNextElement()) {
				throw new NoSuchElementException("This collection has no more elements.");
			}
			return elements[cursor++]; 
		}	
	}
	
	
	/**
	 * The default constructor that creates an instance with array capacity set to {@code DEFAULT_CAPACITY}.
	 */
	public ArrayIndexedCollection() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * A constructor that creates an instance with array capacity set to some capacity.
	 * @param initialCapacity Selected capacity of the array.
	 * @throws IllegalArgumentException if the initial capacity is set to lower than {@literal 1}.
	 */
	public ArrayIndexedCollection(int initialCapacity) {
		this(null, initialCapacity);
	}
	
	
	/**
	 * A constructor that creates an instance with array capacity set to {@code DEFAULT_CAPACITY}.
	 * It copies the elements from some other {@code Collection} to this one.	 * 
	 * If necessary, the capacity is expanded to the capacity of the other collection.
	 * This other {@code Collection} must be a collection of at least {@code <T>} type elements.
	 * 
	 * @param subSet A {@code Collection} whose elements are added to the new {@code ArrayIndexedCollection}.
	 */
	public <S extends T>ArrayIndexedCollection(Collection<S> subSet) {
		this(subSet, DEFAULT_CAPACITY);
	}
	
	/**
	 * A constructor that creates an instance with array capacity set to some value.
	 * It copies the elements from other {@code Collection} to this one.
	 * If necessary, the capacity is expanded to the capacity of the other {@code Collection}.
	 * This other {@code Collection} must be a collection of at least {@code <T>} type elements.
	 * 
	 * @param subSet A {@code Collection} whose elements are added to the new {@code ArrayIndexedCollection}.
	 * @param initialCapacity An initial capacity of the collection.
	 * @throws IllegalArgumentException if {@code initialCapacity} is lower than {@literal 1}.
	 */
	@SuppressWarnings("unchecked")
	public <S extends T> ArrayIndexedCollection(Collection<S> subSet, int initialCapacity) {
		if(initialCapacity < 1) {
			throw new IllegalArgumentException();
		}
		
		if(subSet == null) {
			elements = (T[]) new Object[initialCapacity];
			return;
		}
		
		if(initialCapacity < subSet.size()) {
			elements = (T[])new Object[subSet.size()];
		} else {
			elements = (T[]) new Object[initialCapacity];
		}

		this.addAll(subSet);
	}

	/**
	 * If the current array is full, it creates the new one with double the capacity
	 * and copies the elements of the old one in the new one. It makes the new array
	 * the relevant one.
	 */
	private void reSizeIfFull() {
		if(size < elements.length) return;
		@SuppressWarnings("unchecked")
		T[] newElements = (T[]) new Object[2 * size];
		
		for(int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		modificationCount++;
		elements = newElements;
	}
	
	/**
	 * Adds the given object into this collection (reference is added into first empty place in the elements array).
	 * If the elements array is full, it is reallocated by doubling its size.
	 * The method throws the {@code NullPointerException} if adding {@code null} is attempted.
	 * The complexity of this method is O(1) if there is no reallocation.
	 * 
	 * @param  value A value which is added to this collection.
	 * @throws NullPointerException if {@code value} is {@code null}.
	 */	
	@Override
	public void add(T value) {
		insert(value, size);
	}

	@Override
	public T get(int index) {
		if(index < 0 || index > size-1) {
				throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}

	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;
		}
		modificationCount++;
		size = 0;
	}
	
	/**
	 * Inserts (does not overwrite) the given value at the given position in array
	 * (before actual insertion elements at position and at greater positions are shifted
	 * one place toward the end). The legal positions are {@literal 0} to {@code size} (both are included).
	 * If position is invalid, {@code IndexOutOfBoundsException} is thrown.
	 * Except the difference in position at witch the given object will be inserted,
	 * everything else is in conformance with the method {@link add}. 
	 * Average complexity of this method is O(n).
	 * 
	 * @param value An object which is inserted.
	 * @param position Position at which an object is inserted.
	 * @throws IndexOutOfBoundsException if position is invalid.
	 */
	@Override
	public void insert(T value, int position) {
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(value == null) {
			throw new NullPointerException();
		}
		
		reSizeIfFull();
		
		for(int i = size; i > position; i--) {
			elements[i] = elements[i-1];
		}
		modificationCount++;
		size++;
		elements[position] = value;
	}
	
	/**
	 * Searches the collection and returns the index of the first occurrence of the
	 * given value or {@literal -1} if the value is not found. {@code Null} can be argument.
	 * The equality is determined using the equals method.
	 * The average complexity of this method is O(n).
	 * 
	 * @param value An object we search for.
	 * @return Returns an index of the object. {@literal -1} if it isn't contained.
	 */
	@Override
	public int indexOf(Object value) {
		if(value == null) return -1;
		
		for(int i = 0; i < size; i++) {
			if(value.equals(get(i))) {
				return i;
			}
		}
		
		return -1;
	}
	
	@Override
	public void remove(int index) {
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		
		for(int i = index; i+1 < size; i++) {
			elements[i] = elements[i+1];
		}
		elements[size-1] = null;
		size--;
		modificationCount++;
	}
	
	@Override
	public boolean remove(Object value) {
		if(value == null) {
			return false;
		}
		if(!contains(value)) {
			return false;
		}
		remove(indexOf(value));
		return true;
	}
	
	@Override
	public int size() {
		return size;
	}
	

	@Override
	public boolean contains(Object value) {
		if(value == null) {
			return false;
		}
		
		for(int i = 0; i < size; i++) {
			if (value.equals(elements[i])) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size];
		for(int i = 0; i < size; i++) {
			array[i] = elements[i];
		}
		return array;
	}

	@Override
	public ElementsGetter<T> createElementsGetter() {
		return new ArrayElementsGetter();
	}
	
}
