package hr.fer.zemris.java.hw05.db.lexer;

import java.util.Objects;

import hr.fer.zemris.java.hw05.db.ComparisonOperators;
import hr.fer.zemris.java.hw05.db.FieldValueGetters;

public class Lexer {

	/**
	 * A field of characters which represents the text
	 * this instance is working on.
	 */
	private char[] data;
	/**
	 * Original text that is lexed.
	 */
	private String text;
	
	/**
	 * A reference to the current token.
	 */
	private Token token;
	
	/**
	 * The index of the first not read character.
	 */
	private int currentIndex;
	
	
	
	/**
	 * A constructor for class Lexer. It creates a new lexer
	 * which lexes the given text. LexerState is set to text.
	 * 
	 * @param text A text which is lexed.
	 * @throws NullPointerException if text is null.
	 */
	public Lexer(String text) {
		Objects.requireNonNull(text);
		data = text.toCharArray();
		this.text = text;
	}
	
	
	/**
	 * A method which generates and returns the next
	 * token. It throws LexerException if there was an error.
	 * The mode is switched if symbol-typed token with the value of '#' was read.
	 * 
	 * @return Returns the next token.
	 * @throws RuntimeException if an error had occurred.
	 */
	public Token nextToken() {		
		if(token != null && token.getType() == TokenType.EOF) {
			throw new RuntimeException();
		}	
		
		generateNextToken();
		return getToken();
	}
		
	/**
	 * A method which returns the last generated token.
	 * It doesn't generate a new one in the process.
	 * 
	 * @return Returns the last generated token
	 */
	public Token getToken() {
		if(token == null) {
			return null;
		}
		return token;
	}
	

	/**
	 * A private method which generates the next token.
	 * @throws RuntimeException id something goes wrong.
	 */
	private void generateNextToken() {
		skipBlanks();
		if(currentIndex == text.length()) {
			token = new Token(TokenType.EOF, null);
			return;
		}

		String literal = "=";
		if(text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.OPERATOR, ComparisonOperators.EQUALS);
			currentIndex += literal.length();
			return;
		}
		literal = "<=";
		if(currentIndex+2 <= data.length && text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.OPERATOR, ComparisonOperators.LESS_OR_EQUALS);
			currentIndex += literal.length();
			return;
		}
		literal = ">=";
		if(currentIndex+2 <= data.length && text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.OPERATOR, ComparisonOperators.GREATER_OR_EQUALS);
			currentIndex += literal.length();
			return;
		}
		literal = "<";
		if(text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.OPERATOR, ComparisonOperators.LESS);
			currentIndex += literal.length();
			return;
		}
		literal = ">";
		if(text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.OPERATOR, ComparisonOperators.GREATER);
			currentIndex += literal.length();
			return;
		}
		
		if(text.charAt(currentIndex) == '"') {
			int i = currentIndex;
			do {
				currentIndex++;
			} while(text.charAt(currentIndex) != '"');
			token = new Token(TokenType.STRING, text.substring(i+1, currentIndex));
			currentIndex++;
			return;
		}
		
		literal = "!=";
		if(text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.OPERATOR, ComparisonOperators.NOT_EQUALS);
			currentIndex += literal.length();
			return;
		}
		
		literal = "AND";
		if(text.substring(currentIndex, currentIndex+literal.length()).toUpperCase().equals(literal)) {
			token = new Token(TokenType.AND, null);
			currentIndex += literal.length();
			return;
		}
		literal = "LIKE";
		if(text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.OPERATOR, ComparisonOperators.LIKE);
			currentIndex += literal.length();
			return;
		}		
		literal = "jmbag";
		if(text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.FIELD, FieldValueGetters.JMBAG);
			currentIndex += literal.length();
			return;
		}
		literal = "lastName";
		if(text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.FIELD, FieldValueGetters.LAST_NAME);
			currentIndex += literal.length();
			return;
		}
		literal = "firstName";
		if(text.substring(currentIndex, currentIndex+literal.length()).equals(literal)) {
			token = new Token(TokenType.FIELD, FieldValueGetters.FIRST_NAME);
			currentIndex += literal.length();
			return;
		}
		
		throw new RuntimeException();
	}

	
	/**
	 * A method which moves the cursor currentIndex of a lexer
	 * to the next non-whitespace character. It stops if the whole
	 * string {@code data} was read.
	 */
	private void skipBlanks() {		
		while(currentIndex < data.length) {
			switch(data[currentIndex]) {
			case ' ':
			case '\r':
			case '\n':
			case '\t':
				currentIndex++;
				break;
			default:
				return;
			}
		}
	}
}
