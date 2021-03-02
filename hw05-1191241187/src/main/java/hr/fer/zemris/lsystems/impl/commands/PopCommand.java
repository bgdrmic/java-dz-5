package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * PopCommand which implements Command interface is a class
 * which represents a command to pop one TurtleState from
 * the stack of some context.
 * @author Božidar Grgur Drmić
 *
 */
public class PopCommand implements Command {

	/**
	 * Executes the popState() method of some Context.
	 * @param ctx - the context whose method is executed. 
	 * @param painter - does nothing here
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.popState();		
	}

}
