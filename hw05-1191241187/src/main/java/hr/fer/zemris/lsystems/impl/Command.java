package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;

/**
 * Command is an interface which represents a command to
 * change some Context by eventually utilising some Painter.
 * 
 * @author Božidar Grgur Drmić
 *
 */
public interface Command {
	
	/**
	 * A method which executes the command.
	 * @param ctx - Context upon which the command is executed.
	 * @param painter - A painter which is utilised if necessary.
	 */
	void execute(Context ctx, Painter painter);
}
