package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Context is a class whose instances enable the creation of fractals. 
 * @author Božidar Grgur Drmić
 *
 */
public class Context {
	
	/**
	 * A stack that contains all of the TurtleStates. The newer the state, more it is to the top.
	 */
	private ObjectStack<TurtleState> stateStack;
	
	/**
	 * A default constructor.
	 * It initialises the stateStack variable.
	 */
	public Context() {
		super();
		stateStack = new ObjectStack<TurtleState>();
	}
	
	/**
	 * A getter method for the currently active TurtleState.
	 * @return Returns the currently active TurtleState.
	 */
	public TurtleState getCurrentState() {
		return stateStack.peek();
	}
	
	/**
	 * A method which pushes a TurtleState on the stateStack.
	 * That state becomes the active one.
	 * @param state - the state which is pushed onto the stateStack.
	 */
	public void pushState(TurtleState state) {
		stateStack.push(state);
	}
	
	/**
	 * A method which pops one TurtleState from the stateStack.
	 * @return Returns the state which was poped.
	 */
	public TurtleState popState() {
		return stateStack.pop();
	}
	
}
