package hr.fer.zemris.lsystems.impl;

import java.awt.Color;
import java.util.Scanner;

import hr.fer.zemris.java.custom.collections.Dictionary;
import hr.fer.zemris.lsystems.*;
import hr.fer.zemris.lsystems.impl.commands.*;
import static java.lang.Math.*;

/**
 * LSystemBuilderImpl is a class which represents one specific implementation
 * of LSystemBuilder.
 * @author Božidar Grgur Drmić
 *
 */
public class LSystemBuilderImpl implements LSystemBuilder {

	/**
	 * A collection of registered productions.
	 */
	private Dictionary<Character,String> productions;
	/**
	 * A collection of registered productions.
	 */
	private Dictionary<Character,Command> commands;
	
	/**
	 * Basic length of one step of turtle.
	 */
	private double unitLength;
	/**
	 * A scale by which the step is depending of the level of a fractal.
	 */
	private double unitLengthDegreeScaler;
	/**
	 * A starting point of turtle.
	 */
	private Vector2D origin;
	/**
	 * An angle by which the turle is rotated.(In radians)
	 */
	private double angle;
	/**
	 * An axiom of this fractal.
	 */
	private String axiom;
	
	/**
	 * A default constructor. It sets the unitLength to 0.1,
	 * unitLengthDegreeScaler to 1, origin to (0,0), angle to 0 and axiom to "".
	 */
	public LSystemBuilderImpl() {
		this(0.1, 1, new Vector2D(0, 0), 0, "");
	}	
	
	/**
	 * A constructor for this class. It sets all the relevant variables.
	 * @param unitLength - unitLength is set to this value.
	 * @param unitLengthDegreeScaler - unitLengthDegreeScaler is set to this value.
	 * @param origin - origin is set to this value.
	 * @param angle - angle is set to this value.
	 * @param axiom - axiom is set to this value.
	 */
	public LSystemBuilderImpl(double unitLength, double unitLengthDegreeScaler, Vector2D origin, double angle, String axiom) {
		super();
		
		this.productions = new Dictionary<Character,String>();
		this.commands = new Dictionary<Character,Command>();
		
		this.unitLength = unitLength;
		this.unitLengthDegreeScaler = unitLengthDegreeScaler;
		this.origin = origin;
		this.angle = angle;
		this.axiom = axiom;
	}

	/**
	 * An implementation of LSystem interface.
	 */
	private class LSystemImpl implements LSystem {
		@Override
		public void draw(int level, Painter painter) {
			var ctx = new Context();
			var state = new TurtleState(new Vector2D(cos(angle), sin(angle)), origin, Color.BLACK
					, unitLength * pow(unitLengthDegreeScaler, level));
			ctx.pushState(state);
			
			String s = generate(level);
			
			for(char c : s.toCharArray()) {
				Command command = commands.get(c);
				if(command == null) continue;
				command.execute(ctx, painter);
			}
		}

		@Override
		public String generate(int level) {
			if(level == 0) return axiom;
			StringBuilder result = new StringBuilder();
			for(char c : generate(level-1).toCharArray()) {
				String substitute = productions.get(c);
				
				if(substitute == null) {
					result.append(c);
					continue;
				}
				result.append(substitute);
			}
			return result.toString();
		}
	}
	
	@Override
	public LSystem build() {
		return new LSystemImpl();
	}

	@Override
	public LSystemBuilder configureFromText(String[] text) {
        for(String s : text) {
        	Scanner scanner = new Scanner(s);
        	if(!scanner.hasNext()) continue;
        	
        	double x, y;
        	char symbol;
        	
		    try {
		    	switch(scanner.next()) {
		    	case "command":
					symbol = scanner.next().charAt(0);
		    		String command = "";
		    		while(scanner.hasNext()) {
		    			command += " " + scanner.next();
		    		}
		    		command = command.substring(1);
		    		registerCommand(symbol, command);
		        	break;
		    	case "origin":
		    		x = Double.parseDouble(scanner.next());
		    		y = Double.parseDouble(scanner.next());
		    		origin = new Vector2D(x, y);
		    		break;
		    	case "angle":
		    		x = Double.parseDouble(scanner.next());
		    		angle = Math.toRadians(x);
		    		break;
		    	case "unitLength":
		    		x = Double.parseDouble(scanner.next());
		    		unitLength = x;
		    		break;
		    	case "axiom":
		    		axiom = scanner.next();
		    		break;
		    	case "unitLengthDegreeScaler":
		    		x = Double.parseDouble(scanner.next());
		    		String nextToken = scanner.next();
		    		if(nextToken.equals("/")) {
		    			nextToken = scanner.next();
		    		}
		    		nextToken = nextToken.replace("/", " ");
		    		y = Double.parseDouble(nextToken);
		    		unitLengthDegreeScaler = x / y;
		    		break;
		    	case "production":
		    		symbol = scanner.next().charAt(0);
		    		String production = "";
		    		while(scanner.hasNext()) {
		    			production += " " + scanner.next();
		    		}
		    		production = production.substring(1);
		    		registerProduction(symbol, production);
		    		break;
		    	default:
		    		scanner.close();
		    		throw new IllegalArgumentException();
		    	}
		    	if(scanner.hasNext()) {
		    		scanner.close();
		    		throw new IllegalArgumentException();
		    	}
		    	scanner.close();
		    } catch (Exception e) {
		    	throw new IllegalArgumentException();
		    }
        }
	return this;
	}

	@Override
	public LSystemBuilder registerCommand(char symbol, String command) {
		String[] sequence = command.split("\\s+");
		Command newCommand = parseCommand(sequence);
		commands.put(symbol, newCommand);
		return this;
	}

	@Override
	public LSystemBuilder registerProduction(char symbol, String production) {
		productions.put(symbol, production);
		return this;
	}

	@Override
	public LSystemBuilder setAngle(double angle) {
		this.angle = Math.toRadians(angle);
		return this;
	}

	@Override
	public LSystemBuilder setAxiom(String axiom) {
		this.axiom = axiom;
		return this;
	}

	@Override
	public LSystemBuilder setOrigin(double x, double y) {
		origin = new Vector2D(x, y);
		return this;
	}

	@Override
	public LSystemBuilder setUnitLength(double unitLength) {
		this.unitLength = unitLength;
		return this;
	}

	@Override
	public LSystemBuilder setUnitLengthDegreeScaler(double unitLengthDegreeScaler) {
		this.unitLengthDegreeScaler = unitLengthDegreeScaler;
		return this;
	}
	
	/**
	 * A method which parses a string into a command.
	 * @param sequence - string which is parsed
	 * @return Returns the Command which is the result of parsing.
	 * @throws IllegalArgumentException if it wasn't a valid command.
	 */
	private static Command parseCommand(String[] sequence) {
		
		if(sequence.length < 1) {
			throw new IllegalArgumentException();
		}
		
		if(sequence[0].equals("draw")) {
			if(sequence.length != 2) {
				throw new IllegalArgumentException();
			}
			
			double s;
			try {
				s = Double.parseDouble(sequence[1]);
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException();
			}			
			
			return new DrawCommand(s);
		}
		if(sequence[0].equals("skip")) {
			if(sequence.length != 2) {
				throw new IllegalArgumentException();
			}
			
			double s;
			try {
				s = Double.parseDouble(sequence[1]);
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException();
			}			
			
			return new SkipCommand(s);
		}
		if(sequence[0].equals("scale")) {
			if(sequence.length != 2) {
				throw new IllegalArgumentException();
			}
			
			double s;
			try {
				s = Double.parseDouble(sequence[1]);
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException();
			}			
			
			return new ScaleCommand(s);
		}
		if(sequence[0].equals("rotate")) {
			if(sequence.length != 2) {
				throw new IllegalArgumentException();
			}
			
			double a;
			try {
				a = Math.toRadians(Double.parseDouble(sequence[1]));
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException();
			}			
			
			return new RotateCommand(a);
		}
		if(sequence[0].equals("push")) {
			if(sequence.length != 1) {
				throw new IllegalArgumentException();
			}
			
			return new PushCommand();
		}
		if(sequence[0].equals("pop")) {
			if(sequence.length != 1) {
				throw new IllegalArgumentException();
			}
			return new PopCommand();
		}
		if(sequence[0].equals("color")) {
			if(sequence.length != 2) {
				throw new IllegalArgumentException();
			}
			return new ColorCommand(hex2RGB(sequence[1]));
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * A method which converts a string in rrggbb format to Color.
	 * @param colorStr - a string which is converted
	 * @return Returns the result of conversion.
	 * @throws IllegalArgumentException if the format was wrong.
	 */
	private static Color hex2RGB(String colorStr) {
		if(colorStr.length() != 6) {
			throw new IllegalArgumentException();
		}
	    return new Color(
	    		Integer.valueOf(colorStr.substring(0, 2), 16),
	            Integer.valueOf(colorStr.substring(2, 4), 16),
	            Integer.valueOf(colorStr.substring(4, 6), 16)
	    );
	}
}