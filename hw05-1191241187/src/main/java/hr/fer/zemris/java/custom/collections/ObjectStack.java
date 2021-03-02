package hr.fer.zemris.java.custom.collections;


/**
 * {@code ObjectStack} is a class which represent an implementation of a stack.
 * It provides all the regular operations with stacks.
 * 
 * @author Božidar Grgur Drmić
 *
 */
public class ObjectStack<T> {
	
	/**
	 * A private non-static variable which represents a stack.
	 */
	private ArrayIndexedCollection<T> stack;
	
	/**
	 * A default constructor for a {@code ObjectStack} class.
	 * It creates a new empty stack.
	 */
	public ObjectStack() {
		stack = new ArrayIndexedCollection<>();
	}
	
	/**
	 * A method which returns {@code true} only if the stack is empty.
	 * @return Returns {@code true} only if the stack is empty.
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * A method which returns the {@code size} of a stack.
	 * @return Returns the number of elements in a stack.
	 */
	public int size() {
		return stack.size();
	}
	
	/**
	 * Adds an object to the top of the stack.
	 * @param value An object to be added.
	 */
	public void push(T value) {
		if(value == null) {
			throw new NullPointerException();
		}
		stack.add(value);
	}
	
	/**
	 * Removes an object from the top of the stack.
	 * It throws an exception if the stack is empty.
	 * 
	 * @return Returns the removed object.
	 * @throws EmptyStackException if called upon an empty stack.
	 */
	public T pop() {
		if(stack.isEmpty()) {
			throw new EmptyStackException();
		}
		T value = stack.get(size()-1);
		stack.remove(size()-1);
		return value;
	}
	
	/**
	 * A getter method for the object on the top of the stack.
	 * It throws an exception if the stack is empty.
	 * 
	 * @return Returns the top element of the stack.
	 * @throws EmptyStackException if called upon an empty stack.
	 */
	public T peek() {
		if(stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.get(size() - 1);
	}
	
	/**
	 * A method which empties a stack.
	 */
	public void clear() {
		stack.clear();
	}
}
