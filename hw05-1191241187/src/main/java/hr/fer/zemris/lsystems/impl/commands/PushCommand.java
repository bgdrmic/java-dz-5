package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * PushCommand which implements Command interface is a class
 * which represents a command to push one TurtleState onto
 * the stack of some context. It pushes the copy of
 * already active state.
 * @author Božidar Grgur Drmić
 *
 */
public class PushCommand implements Command {

	/**
	 * A method which creates another TurtleState identical to the
	 * currently active TurtleState of some Context one and makes it the acitve state. 
	 * 
	 * @param ctx - the context whose method is executed. 
	 * @param painter - does nothing here
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.pushState(ctx.getCurrentState().copy());
	}

}
