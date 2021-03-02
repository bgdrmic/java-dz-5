package hr.fer.zemris.java.hw05.db;

import java.util.List;

/**
 * QueryFilter which implements IFilter is a class whose instances represent filters
 * with various conditions each StudentRecord has to fulfil
 * in order to be considered acceptable.
 * 
 * @author JohnDoe
 *
 */
public class QueryFilter implements IFilter {

	/**
	 * List of conditions which StudentRecord must fulfil to be considered acceptable.
	 */
	private List<ConditionalExpression> expressions;
	
	/**
	 * Constructor which accepts list of conditions each StudentRecord has to fulfil
	 * in order to be considered acceptable.
	 * @param expressions - list of conditions 
	 */
	public QueryFilter(List<ConditionalExpression> expressions) {
		super();
		this.expressions = expressions;
	}

	@Override
	public boolean accepts(StudentRecord student) {
		for(var expression : expressions) {
			if(!expression.getComparisonOperator()
					.satisfied(expression.getFieldGetter().get(student), expression.getStringLiteral())) {
				return false;
			}
		}
		return true;
	}

}
