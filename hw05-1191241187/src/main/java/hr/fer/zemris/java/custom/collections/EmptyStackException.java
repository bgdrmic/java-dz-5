package hr.fer.zemris.java.custom.collections;

/**
 * EmptyStackException is a class which extends RuntimeExcetion.
 * 
 * @author Božidar Grgur Drmić
 *
 */
public class EmptyStackException extends RuntimeException {

	/**
	 * A serialVersionUID variable of this class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A default constructor which creates a new default
	 * RuntimeException.
	 */
	public EmptyStackException() {
		super();
	}

	/**
	 * A constructor which creates a new RuntimeException
	 * and prints the appropriate message.
	 * @param message The message to be printed.
	 */
    public EmptyStackException(String message) {
       super(message);
    }
    
}
