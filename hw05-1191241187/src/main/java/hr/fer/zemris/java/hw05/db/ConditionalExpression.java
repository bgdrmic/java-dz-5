package hr.fer.zemris.java.hw05.db;

import java.util.Objects;

/**
 * ConditionalExpression is a class which represents a conditional expression.
 * @author Božidar Grgur Drmić
 *
 */
public class ConditionalExpression {

	/**
	 * getter for field of StudentRecord which is conditioned.
	 */
	private IFieldValueGetter fieldGetter;
	/**
	 * reference literal.
	 */
	private String stringLiteral;
	/**
	 * comparison by which they are compared.
	 */
	private IComparisonOperator comparisonOperator;
	
	/**
	 * A constructor which accepts a reference to IFieldValueGetter strategy,
	 * a reference to string literal and a reference to IComparisonOperator strategy.	
	 * @param fieldGetter - a getter for field of StudentRecord which is conditioned.
	 * @param stringLiteral - a referent literal.
	 * @param comparisonOperator - comparison by which they are compared.
	 * @throws NullPointerException if any argument is null.
	 */
	public ConditionalExpression(IFieldValueGetter fieldGetter,
			String stringLiteral, IComparisonOperator comparisonOperator) {
		Objects.requireNonNull(fieldGetter);
		Objects.requireNonNull(stringLiteral);
		Objects.requireNonNull(comparisonOperator);
		
		this.fieldGetter = fieldGetter;
		this.stringLiteral = stringLiteral;
		this.comparisonOperator = comparisonOperator;
	}

	/**
	 * A getter method for fieldGetter variable.
	 * @return fieldGetter variable.
	 */
	public IFieldValueGetter getFieldGetter() {
		return fieldGetter;
	}

	/**
	 * A getter method for stringLiteral variable.
	 * @return stringLiteral variable.
	 */
	public String getStringLiteral() {
		return stringLiteral;
	}

	/**
	 * A getter method for comparisonOperator variable.
	 * @return comparisonOperator variable.
	 */
	public IComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}
	
}
