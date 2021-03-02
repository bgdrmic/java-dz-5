package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;

/**
 * {@code LinkedListIndexedCollection} which implements {@link List}
 * is a class that represents a linked list-backed collection of objects of type {@code <T>}.
 * Duplicate elements are allowed, storage of {@code null} references is not allowed.
 *  
 * @author Božidar Grgur Drmić 1191241187
 * @param <T> The type of objects stored.
 *
 */
public class LinkedListIndexedCollection<T> implements List<T> {
	
	/**
	 * A private static class each instance of which represents a node.
	 */
	private class ListNode {		
		T value;
		ListNode next;
		ListNode previous;		
	}
		
	/**
	 * A non-static variable which denotes the current size of collection.
	 */
	private int size;
	
	/**
	 * A non-static reference to the first element.
	 */
	private ListNode first;
	
	/**
	 * A non-static reference to the last element.
	 */
	private ListNode last;
	
	/**
	 * A non-static variable which counts how many changes have been done
	 * to the collection.
	 */
	private long modificationCount;
	
	/**
	 * A private static class which represents an implementation of
	 * {@link ElementsGetter} interface specifically for {@code LinkedListIndexedCollection} class.
	 */
	private class ListElementsGetter implements ElementsGetter<T> {
		
		private ListNode currentNode;
		private long savedModificationCount;
		
		public ListElementsGetter() {
			currentNode = first;
			savedModificationCount = modificationCount;
		}
		
		private boolean collectionWasModified() {
			 return savedModificationCount != modificationCount;
		}
		
		public boolean hasNextElement() {
			if(collectionWasModified()) {
				throw new ConcurrentModificationException("The collection has been changed");
			}
			if(currentNode == null) return false;
			if(currentNode.value == null) return false;
			return true;
		}
		
		public T getNextElement() {
			if(!(this.hasNextElement())) {
				throw new IndexOutOfBoundsException("This collection has no more elements.");
			}
			
			T nextElement = currentNode.value;
			currentNode = currentNode.next;
			return nextElement; 
		}	
	}
	
	
	/**
	 * A default constructor which creates an empty {@code LinkedListIndexedCollection}.
	 */
	public LinkedListIndexedCollection() {
		this(null);		
	}
	
	/**
	 * A constructor which creates a new {@code LinkedListIndexedCollection}
	 * which contains all the elements of some other {@code Collection}.
	 * This other {@code Collection} must be a collection of at least {@code <T>} type elements.
	 * 
	 * @param subSet A {@code Collection} whose elements are added into a new {@code LinkedListIndexedCollection}.
	 */
	public <S extends T> LinkedListIndexedCollection(Collection<S> subSet) {
		if(subSet == null) {
			return;
		}
		addAll(subSet);
	}
	
	/**
	 * A getter method for the first element of a list.
	 * 
	 * @return Returns {@code first}.
	 */
	public ListNode getFirst() {
		return first;
	}
	
	/**
	 * A getter method for the last element of a list.
	 * 
	 * @return Returns {@code last}.
	 */
	public ListNode getLast() {
		return last;
	}
	
	/**
	 * A getter method for the value of a certain {@code ListNode}.
	 * 
	 * @param node A {@code ListNode} which value the method returns.
	 * @return Returns a value of the {@code node}.
	 */
	public Object getNodeValue(ListNode node) {
		return node.value;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Adds the given object at the end of this collection.
	 * The method throws the {@code NullPointerException} if adding {@code null} is attempted.
	 * The complexity of this method is O(1).
	 * 
	 * @param  value An object which is added to this collection.
	 * @throws NullPointerException if value is null.
	 */	
	@Override
	public void add(T value) {
		insert(value, size);
	}

	@Override
	public boolean contains (Object value) {
		if(indexOf(value) == -1) {
			return false;
		}
		return true;
	}
	
	/**
	 * Searches the collection and returns the index of the first occurrence of the
	 * given value or {@literal -1} if the value is not found. {@code Null} can be argument.
	 * The equality is determined using the {@code equals} method.
	 * The average complexity of this method is O(n).
	 * 
	 * @param value An object we search
	 * @return Returns an index of the object. {@literal -1} if it isn't contained.
	 */
	@Override
	public int indexOf(Object value) {
		if(value == null || isEmpty()) {
			return -1;
		}
		ListNode currentNode = first;
		for(int i = 0; i < size; i++) {
			if(value.equals(currentNode.value)) {
				return i;
			}
			currentNode = currentNode.next;;
		}
		return -1;
	}
	
	/**
	 * A private method which removes some node from the list.
	 * @param node A {@code ListNode} which is to be removed.
	 */
	private void remove(ListNode node) {
		if(node == first) {
			if(first != last) {
				first.previous = null;
			}			
			first = first.next;
			
		} else if(node == last) {
			last = node.previous;
			last.next = null;
		} else {
			node.previous.next = node.next;
			node.next.previous = node.previous;
		}
		node.next = null;
		node.previous = null;
		size--;
		modificationCount++;
	}

	@Override
	public boolean remove(Object value) {
		ListNode currentNode = first;
		
		while(currentNode != null) {
			if(currentNode.value.equals(value)) {
				remove(currentNode);
				return true;
			}			
			currentNode = currentNode.next;
		}
		
		return false;
	}
	
	/**
	 * Removes element at specified index from this collection. Element that was
	 * previously at location index+1 after this operation is on location index, etc.
	 * Legal indices are from {@literal 0} to {@code size-1}. In case of invalid index,
	 * {@code IndexOutOfBoundsException} is thrown.
	 * 
	 * @param index Index of an object to be removed.
	 * @throws IndexOutOFBoundsException if index is invalid.
	 */
	@Override
	public void remove(int index) {
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		
		var currentNode = first;
		while(index > 0) {
			index--;
			currentNode = currentNode.next;
		}
		remove(currentNode);
	}
	
	/**
	 * Returns the object that is stored in the list at certain index.
	 * Valid indices are from {@literal 0} to {@code size-1}. In case of invalid index,
	 * {@code IndexOutOfBoundsException} is thrown.
	 * The complexity of this method is always smaller than {@literal size/2 + 1};
	 * 
	 * @param index Index of an object.
	 * @return Returns the object at the specified index.
	 * @throws IndexOutOfBounds if index is invalid.
	 */
	@Override
	public T get(int index) {
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		
		int increment;
		ListNode currentNode;
		
		if(index <= size/2) {
			increment = -1;
			currentNode = first;
		} else {
			increment = 1;
			currentNode = last;
		}
		while(index < size-1 && index > 0) {
			index += increment;
			if(increment < 0) {
				currentNode = currentNode.next;
			}
			else {
				currentNode = currentNode.previous;
			}
		}
		return currentNode.value;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
		modificationCount++;
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size];
		var currentNode = first;
		
		for(int i = 0; i < size; i++) {
			array[i] = currentNode.value;
			currentNode = currentNode.next;
		}
		
		return array;
	}
	
	/**
	 * Inserts (does not overwrite) the given value at the given position in the list
	 * (before actual insertion elements at position and at greater positions are shifted
	 * one place toward the end). The legal positions are from {@literal 0} to {@code size} (both are included).
	 * If position is invalid, {@code IndexOutOfBoundsException} is thrown.
	 * Except the difference in position at witch the given object will be inserted,
	 * everything else is in conformance with the method {@code add}. 
	 * Average complexity of this method is O(n).
	 * 
	 * @param value An object which is inserted.
	 * @param position Position at which an object is inserted.
	 * @throws IndexOutOfBoundsException if position is invalid.
	 */
	public void insert(T value, int position) {
		if(value == null) {
			throw new NullPointerException();
		}
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		var newNode = new ListNode();
		newNode.value = value;
		size++;
		modificationCount++;
		
		if(first == null) {
			first = newNode;
			last = newNode;
			newNode.next = null;
			newNode.previous = null;
			return;
		}
		
		if(position == 0) {
			first.previous = newNode;
			newNode.next = first;
			first = newNode;
			return;
		}
		if(position == size-1) {
			last.next = newNode;
			newNode.previous = last;
			last = newNode;
			return;
		}
		
		var currentNode = first;
		while(position > 0) {
			currentNode = currentNode.next;
			position--;
		}
		newNode.previous = currentNode.previous;
		newNode.next = currentNode;
		newNode.previous.next = newNode;
		newNode.next.previous = newNode;
	}
	
	@Override
	public ElementsGetter<T> createElementsGetter() {
		return new ListElementsGetter();
	}
}
