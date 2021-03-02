package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * PushCommand which implements Command interface is a class which
 * represents a command to rotate the direction vector of the currently active state
 * of some context.
 * 
 * @author Božidar Grgur Drmić
 *
 */
public class RotateCommand implements Command {

	/**
	 * angle of rotation (in degrees)
	 */
	private double angle;
	
	/**
	 * A public constructor for RotateCommand class.
	 * It sets the angle of rotation.
	 * @param angle - angle of rotation
	 */
	public RotateCommand(double angle) {
		super();
		this.angle = angle;
	}
	
	/**
	 * Rotates the direction vector of some Context by {@linkplain angle}.
	 * @param ctx - the context whose direction vector is rotated.
	 * @param painter - does nothing here
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().getDirection().rotate(angle);
	}

}
