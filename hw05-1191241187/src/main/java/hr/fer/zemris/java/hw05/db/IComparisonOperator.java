package hr.fer.zemris.java.hw05.db;

/**
 * IComparisonOperator is an interface which represents a strategy for determining whether
 * some string correlates appropriately to some referent string.
 * @author Božidar Grgur Drmić
 *
 */
public interface IComparisonOperator {
	
	/**
	 * A method which determines whether
	 * value1 correlates appropriately to value2.
	 * @param value1 - string whose validity is tested.
	 * @param value2 - referent string
	 * @return true if value1 is in appropriate relation with value2
	 */
	public boolean satisfied(String value1, String value2);
	
}
