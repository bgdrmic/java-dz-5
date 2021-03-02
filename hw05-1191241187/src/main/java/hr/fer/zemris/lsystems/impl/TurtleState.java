package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

/**
 * TurtleState is a class each instance of which represents
 * a state of the turtle.
 * @author Božidar Grgur Drmić
 *
 */
public class TurtleState {

	/**
	 * The direction to which the turtle is currently turned.
	 */
	private Vector2D direction;
	/**
	 * The current location of the turtle.
	 */
	private Vector2D location;
	/**
	 * The color with which the turtle currently draws.
	 */
	private Color color;
	/**
	 * The current length of one normalised step.
	 */
	private double unitLength;
	
	/**
	 * A default public constructor.
	 * direction is set to the east, location is set to (0, 0), color to black
	 * and unitLength to 1. 
	 */
	public TurtleState() {
		this(new Vector2D(1, 0), new Vector2D(0, 0), Color.BLACK, 1);
	}
	
	/**
	 * A public constructor which sets all the relevant variables.
	 * @param direction - direction is set to this value.
	 * @param location - location is set to this value.
	 * @param color - color is set to this value.
	 * @param unitLength - unitLength is set to this value.
	 */
	public TurtleState(Vector2D direction, Vector2D location, Color color, double unitLength) {
		super();
		this.direction = direction;
		this.location = location;
		this.color = color;
		this.unitLength = unitLength;
	}

	/**
	 * Creates a copy of this TurtleState.
	 * If any value of any of these two states is changed, the other remains unchanged.
	 * @return Returns the copy of this TrutleState.
	 */
	public TurtleState copy() {
		var turtle = new TurtleState();
		turtle.direction = direction.copy();
		turtle.location = location.copy();
		turtle.color = color;
		turtle.unitLength = unitLength;
		return turtle;
	}

	/**
	 * A getter method for location variable
	 * @return Returns the location variable.
	 */
	public Vector2D getLocation() {
		return location;
	}
	
	/**
	 * A setter method for location variable
	 * @param direction - location is set to this value.
	 */
	public void setLocation(Vector2D location) {
		this.location = location;
	}

	/**
	 * A getter method for color variable.
	 * @return Returns the color variable.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * A setter method for color variable.
	 * @param color - color is set to this value.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * A getter method for unitLength variable.
	 * @return Returns the unitLength variable.
	 */
	public double getUnitLength() {
		return unitLength;
	}

	/**
	 * A setter method for unitLength variable.
	 * @param stepLength - unitLengths is set to this value.
	 */
	public void setUnitLength(double stepLength) {
		this.unitLength = stepLength;
	}

	/**
	 * A getter method for direction variable.
	 * @return Returns the direction variable.
	 */
	public Vector2D getDirection() {
		return direction;
	}

	/**
	 * A setter method for direction variable.
	 * @param direction - direction is set to this value.
	 */
	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	
}
