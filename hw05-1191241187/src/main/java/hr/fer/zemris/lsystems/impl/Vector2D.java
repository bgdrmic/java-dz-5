package hr.fer.zemris.lsystems.impl;

import static java.lang.Math.*;

/**
 * {@code Vector2D} is a class that represents a 2D radius-vector.
 * 
 * @author Božidar Grgur Drmić
 *
 */
public class Vector2D {

	/**
	 * A private static final variable which represents the maximum allowed difference
	 * between two {@code double} values so that they are still considered equal.
	 */
	private static final double THRESHOLD = 0.000000001; 
	/**
	 * The x-axis coordinate of the vector. 
	 */
	private double x;
	/**
	 * The y-axis coordinate of the vector. 
	 */
	private double y;
	
	
	/**
	 * A public constructor which creates a new {@code Vector2D}
	 * with it's {@code x} and {@code y} coordinates set to some value.
	 * 
	 * @param x The value {@code x} is set to.
	 * @param y The value {@code y} is set to.
	 */
	public Vector2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * A getter method for {@code x} variable.
	 * @return Returns {@code x}.
	 */
	public double getX() {
		return x;
	}

	/**
	 * A getter method for {@code y} variable.
	 * @return Returns {@code y}.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * A method which translates the current vector by some {@code offset}.
	 * @param offset The value of offset.
	 */
	public void translate(Vector2D offset) {
		if(offset == null) return;
		
		this.x += offset.getX();
		this.y += offset.getY();
	}
	
	/**
	 * A method which creates a new vector equal to the current vector translated by {@code offset}. 
	 * @param offset The value of offset.
	 * @return Returns the new vector.
	 */
	public Vector2D translated(Vector2D offset) {
		if(offset == null) return copy();
		
		return new Vector2D(this.x + offset.x, this.y + offset.y);
	}
	
	/**
	 * A method which rotates the current vector by some {@code angle}.
	 * @param angle The angle by which the vector is translated.
	 */
	public void rotate(double angle) {
		double x = cos(angle) * this.x - sin(angle) * this.y;
		double y = sin(angle) * this.x + cos(angle) * this.y;
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * A method which creates a new vector equal to the current vector rotated by {@code angle}. 
	 * @param angle The angle by which the vector is translated.
	 * @return Returns the new vector.
	 */
	public Vector2D rotated(double angle) {
		double x = cos(angle) * this.x - sin(angle) * this.y;
		double y = sin(angle) * this.x + cos(angle) * this.y;
		
		return new Vector2D(x, y);
	}
	
	/**
	 * A method which scales the current vector by {@code scaler}.
	 * @param scaler The value by which the vector is scaled.
	 */
	public void scale(double scaler) {
		this.x *= scaler;
		this.y *= scaler;
	}
	
	/**
	 * A method which creates a new vector equal to the current vector scaled by {@code scaler}. 
	 * @param scaler The value by which the vector is scaled.
	 * @return Returns the new vector.
	 */
	public Vector2D scaled(double scaler) {
		return new Vector2D(this.x * scaler, this.y * scaler);
	}
	
	/**
	 * A method which creates a new vector equal to the current one.
	 * @return Returns the new vector.
	 */
	public Vector2D copy() {
		return new Vector2D(this.x, this.y);
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return true;
		if(!(other instanceof Vector2D)) return false;
		
		if(other == this) return true;
		
		if(abs(this.x - ((Vector2D)other).x) > THRESHOLD) return false;
		if(abs(this.y - ((Vector2D)other).y) > THRESHOLD) return false;
		
		return true;
	}
}
