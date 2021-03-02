package hr.fer.zemris.java.hw05.db;

/**
 * FieldValueGetters is a class which represents some concrete implementations
 * of IFieldValueGetter interface.
 * @author Božidar Grgur Drmić
 *
 */
public class FieldValueGetters {
	
	/**
	 * A getter for firstName variable of StudentRecord.
	 */
	public static final IFieldValueGetter FIRST_NAME = StudentRecord::getFirstName;

	/**
	 * A getter for alstName variable of StudentRecord.
	 */
	public static final IFieldValueGetter LAST_NAME = StudentRecord::getLastName;

	/**
	 * A getter for jmbag variable of StudentRecord.
	 */
	public static final IFieldValueGetter JMBAG = StudentRecord::getJmbag;
	
}
