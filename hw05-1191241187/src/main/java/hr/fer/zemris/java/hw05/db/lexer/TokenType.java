package hr.fer.zemris.java.hw05.db.lexer;

/**
 * TokenType is an enum with types for tokens of the lexed text.
 * 
 * @author Božidar Grgur Drmić
 *
 */
public enum TokenType {

	/**
	 * A type which represents the end of the file.
	 */
	EOF,
	
	/**
	 * A type which represents a token for condition separator.
	 */
	AND,
	
	/**
	 * A type which represents a string to which some alue is compared.
	 */
	STRING,
	
	/**
	 * A type which represents a field of comparison.
	 */
	FIELD,
	
	/**
	 * A type which represents a comparison operator.
	 */
	OPERATOR,
	
}
