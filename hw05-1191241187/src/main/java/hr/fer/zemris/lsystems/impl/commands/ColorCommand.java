package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * ColorCommand which implements Command interface is a class which
 * represents a command to change the color of the active TurtleState
 * of some Context.
 * 
 * @author Božidar Grgur Drmić
 */
public class ColorCommand implements Command {

	/**
	 * The color of the TurtleState is changed to this color.
	 */
	private Color color;
	
	/**
	 * A constructor for this class.
	 * It sets the color variable.
	 * @param color - the color variable is set to this value.
	 */
	public ColorCommand(Color color) {
		this.color = color;
	} 
	
	/**
	 * Changes the color of the currently active state of some Context.
	 * @param ctx - the context whose active state's color is changed.
	 * @param painter - does nothing here.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setColor(color);
	}

}
