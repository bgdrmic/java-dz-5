package hr.fer.zemris.java.hw05.db;

import java.util.Objects;

/**
 * CompariosnOperators is a class which contains implementations
 * of IComparisonOperator interface.
 * @author Božidar Grgur Drmić
 *
 */
public class ComparisonOperators {
	
	/**
	 * ComparisonOperator which compares whether given
	 * string is lexicographically less to referent string.
	 */
	public static final IComparisonOperator LESS = (v1, v2) -> v1.compareTo(v2) < 0;
	/**
	 * ComparisonOperator which compares whether given
	 * string is lexicographically less or equal to referent string.
	 */
	public static final IComparisonOperator LESS_OR_EQUALS = (v1, v2) -> v1.compareTo(v2) <= 0;
	
	/**
	 * ComparisonOperator which compares whether given
	 * string is lexicographically equal to referent string.
	 */
	public static final IComparisonOperator EQUALS = (v1, v2) -> v1.compareTo(v2) == 0;
	
	/**
	 * ComparisonOperator which compares whether given
	 * string is lexicographically greater than referent string.
	 */
	public static final IComparisonOperator GREATER = (v1, v2) -> v1.compareTo(v2) > 0;
	
	/**
	 * ComparisonOperator which compares whether given
	 * string is lexicographically greater or equal to referent string.
	 */
	public static final IComparisonOperator GREATER_OR_EQUALS = (v1, v2) -> v1.compareTo(v2) >= 0;
	
	/**
	 * ComparisonOperator which compares whether given
	 * string is lexicographically alike to the referent string.
	 */
	public static final IComparisonOperator LIKE = (v1, v2) -> {
		Objects.requireNonNull(v1);
		Objects.requireNonNull(v2);
		
		if(!v2.contains("*")) return v1.equals(v2);
		if(v2.equals("*")) return true;
		
		String[] parts = v2.split("[*]");
		
		if(parts.length == 0) return false;;
		
		if(parts.length > 2) {
			throw new IllegalArgumentException("More than one '*' sign in LIKE comparisson");
		}
		if(parts.length == 1) {
			return v1.substring(v1.length()-v2.length()+1).equals(v2.substring(1)) || v1.substring(0, v2.length()-1).equals(v2.substring(0, v2.length()-1));
		}
		
		if(v1.length() + 1 < v2.length()) return false;
		if(parts[0].length() != 0 && !v1.substring(0, parts[0].length()).equals(parts[0])) {
			return false;
		}
		
		return v1.substring(v1.length() - parts[1].length()).equals(parts[1]);
	};
	
	/**
	 * ComparisonOperator which compares whether given
	 * string is lexicographically different than referent string.
	 */
	public static final IComparisonOperator NOT_EQUALS = (v1, v2) -> v1.compareTo(v2) != 0;
	
}
