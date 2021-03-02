package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * ScaleCommand which implements Command interface is a class which
 * represents a command to scale the unitLength of the currently active
 * state of some context.
 * 
 * @author Božidar Grgur Drmić
 *
 */
public class ScaleCommand implements Command {

	/**
	 * A scale by which the unitLength is scaled.
	 */
	private double scale;
	
	/**
	 * A public constructor for ScaleCommand class.
	 * It sets the scaler.
	 * @param scale - value of the scaler.
	 */
	public ScaleCommand(double scale) {
		this.scale = scale;
	}
	
	/**
	 * Scales the unitLength of the currently active state of some context.
	 * @param ctx - unitLength of the currently active state of this context is scaled.
	 * @param painter - does nothing here.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setUnitLength(ctx.getCurrentState().getUnitLength() * scale);
	}

}
