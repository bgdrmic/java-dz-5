package hr.fer.zemris.java.hw05.db.lexer;


/**
 * A class which represents a token of a lexer.
 * A token is defined by it's type and value.
 * 
 * @author Božidar Grgur Drmić
 *
 */
public class Token {
		
	/**
	 * A type of the token.
	 */
	private TokenType type;
	
	/**
	 * A value of the token.
	 */
	private Object value;
	
	/**
	 * A constructor for class Token. It creates a new token and
	 * sets it's type and value as specified.
	 * 
	 * @param type Type of the new token.
	 * @param value Value of the new token.
	 */
	public Token(TokenType type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * A getter for the value variable of a token.
	 * 
	 * @return Returns the value of a token.
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * A getter for the type variable of a token.
	 * 
	 * @return Returns the type of a token.
	 */
	public TokenType getType() {
		return type;
	}
	
}