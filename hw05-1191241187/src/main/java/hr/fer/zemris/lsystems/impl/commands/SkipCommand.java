package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.Vector2D;

/**
 * SkipCommand which implements Command interface is a class which
 * represents a command to move the turtle by some amount relative
 * to the unitLength in the current direction.
 * 
 * @author Božidar Grgur Drmić
 */
public class SkipCommand implements Command {

	/**
	 * Turtle is moved by this amount relative to unitLength.
	 */
	private double step;
	
	/**
	 * A constructor which sets the step by which the turtle is moved.
	 * @param step - step is set to this value.
	 */
	public SkipCommand(double step) {
		super();
		this.step = step;
	}
	
	/**
	 * Moves the turtle by some {@code step} in the current direction.
	 * The newly created state is then made the active state.
	 * @param ctx - the context whose turtle is moved.
	 * @param painter- does nothing here.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		var state = ctx.getCurrentState();

		var x = state.getLocation().getX() + state.getDirection().getX() * step * state.getUnitLength();
		var y = state.getLocation().getY() + state.getDirection().getY() * step * state.getUnitLength();
		state.setLocation(new Vector2D(x, y));
		
	}
}
