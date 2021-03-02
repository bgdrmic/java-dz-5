package hr.fer.zemris.java.hw05.db;

/**
 * IFieldValueGetter is an interface which represents a strategy for getter method
 * for appropriate field of a StudentRecord instance.
 * @author Božidar Grgur Drmić
 *
 */
public interface IFieldValueGetter {
	
	/**
	 * A getter for some field of some record.
	 * 
	 * @param record - student record whose field is retrieved.
	 * @return the appropriate field of the record.
	 */
	public String get(StudentRecord record);
	
}
