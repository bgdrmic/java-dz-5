package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StudentDatabase is a class which represents a database of students.
 * @author Božidar Grgur Drmić
 *
 */
public class StudentDatabase {

	/**
	 * Index of students. Each student is identified by his/her jmbag.
	 */
	private Map<String, Integer> indexOfStudents;
	/**
	 * List of StudentRecords in this database
	 */
	private List<StudentRecord> students;
	
	/**
	 * Constructor for this class which gets a list of entries each of which
	 * is a string in format: "jmbag firstName lastName grade".
	 * 
	 * @param entries - list of entries.
	 * @throws IllegalArgumentException if something is wrong with the entries.
	 */
	public StudentDatabase(List<String> entries) {
		indexOfStudents = new HashMap<String, Integer>();
		students = new ArrayList<StudentRecord>();
		for(var entry : entries) {
			if(entry.contentEquals("")) continue;
			var parts = entry.split("\t");
			
			StudentRecord newRecord;
		
			try {
				newRecord = new StudentRecord(parts[0], parts[2], parts[1], Integer.parseInt(parts[3]));
			} catch(IllegalArgumentException e) {
				throw e;
			} catch (Exception e){
				throw new IllegalArgumentException("Wrong fomat of entry.");
			}
			
			if(indexOfStudents.containsKey(newRecord.getJmbag())) {
				throw new IllegalArgumentException("Duplicate JMBAG: " + newRecord.getJmbag());
			}

			indexOfStudents.put(newRecord.getJmbag(), students.size());
			students.add(newRecord);
		}
	}
	
	/**
	 * A getter method for the StudentRecord of some student.
	 * @param jmbag - jmbag of that student.
	 * @return his/her StudentRecord.
	 */
	public StudentRecord forJMBAG(String jmbag) {
		Integer index = indexOfStudents.get(jmbag);
		if(index == null) return null;
		
		return students.get(index);
	}
	
	/**
	 * A method which loops through all the students and returns the list
	 * of StudentRecords of those who suffice some criterion.
	 * @param filter - criterion.
	 * @return the list of acceptable StudentRecords.
	 */
	public List<StudentRecord> filter(IFilter filter) {
		List<StudentRecord> filteredList = new ArrayList<StudentRecord>();
		for(var student : students) {
			if(filter.accepts(student)) {
				filteredList.add(student);
			}
		}
		return filteredList;
	}
}


