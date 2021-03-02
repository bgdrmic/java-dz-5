package hr.fer.zemris.java.hw05.db;

/**
 * IFilter is an interface which represents a filter for StudentRecord instances.
 * @author Božidar Grgur Drmić
 *
 */
public interface IFilter {
	
	/**
	 * A method which tests whether some student suffices a criterion.
	 * 
	 * @param student - student which is tested.
	 * @return true if student is acceptable. False otherwise.
	 */
	public boolean accepts(StudentRecord student);
}
