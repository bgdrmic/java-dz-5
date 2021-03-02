package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hr.fer.zemris.java.hw05.db.lexer.*;

/**
 * Query parser is a class instances of which represent a parser for some query.
 * @author Božidar Grgur Drmić
 *
 */
public class QueryParser {

	/**
	 * List of conditions in this query.
	 */
	private List<ConditionalExpression> conditions;
	
	/**
	 * Constructor for this class which accepts the query as argument.
	 * @param query - text of the query.
	 * @throws NullPointerException if query is null.
	 * @throws RunTimeException if expression is invalid.
	 */
	public QueryParser(String query) {
		Objects.requireNonNull(query);
		
		conditions = new ArrayList<ConditionalExpression>();
		parse(query);
	}
	
	/**
	 * A method which parses a query.
	 * @param query - text of the query.
	 * @throws RunTimeException if expression is invalid.
	 */
	private void parse(String query) {
		var lexer = new Lexer(query);
		while(true) {
			Token field;
			Token comparison;
			Token literal; 
			
			try {
				field = lexer.nextToken();
				if(field.getType() == TokenType.AND) continue;
				if(field.getType() == TokenType.EOF) return;
				
				comparison = lexer.nextToken();
				literal = lexer.nextToken();
			} catch(RuntimeException e) {
				throw new IllegalArgumentException("Wrong query format.");
			}
			
			if(field.getType() != TokenType.FIELD || comparison.getType() != TokenType.OPERATOR
					|| literal.getType() != TokenType.STRING) {
				throw new IllegalArgumentException("Wrong query format.");
			}
			conditions.add(new ConditionalExpression((IFieldValueGetter)field.getValue(),
					(String)literal.getValue(), (IComparisonOperator) comparison.getValue()));
		}
	}
	
	/**
	 * A method which returns true if the query is direct.
	 * A query is considered direct if it is in format "query jmbag = " ... "".
	 * 
	 * @return true if this query is direct. False otherwise.
	 */
	public boolean isDirectQuery() {
		return conditions.size() == 1 && conditions.get(0).getFieldGetter() == FieldValueGetters.JMBAG
				&& conditions.get(0).getComparisonOperator() == ComparisonOperators.EQUALS;
	}
	
	/**
	 * A method which returns the jmbag literal if this query is direct. 
	 * It throws exception otherwise.
	 * @return jmbag literal
	 * @throws IllegalStateException if query isn't direct.
	 */
	public String getQueriedJMBAG() {
		if(!isDirectQuery()){
			throw new IllegalStateException();
		}
		return conditions.get(0).getStringLiteral();
	}
	
	/**
	 * A getter method for ConditionalExpressions of this query.
	 * @return conditions variable.
	 */
	public List<ConditionalExpression> getQuery() {
		return conditions;
	}
	
}
