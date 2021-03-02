package hr.fer.zemris.java.hw05.db;

import java.util.Objects;
/**
 * StudentRecord is a class whose instances represent a record of one student. 
 * @author Božidar Grgur Drmić
 *
 */
public class StudentRecord {
	/**
	 * Student's JMBAG.
	 */
	private String jmbag;
	/**
	 * Student's first name.
	 */
	private String firstName;
	/**
	 * Student's last name.
	 */
	private String lastName;
	/**
	 * The grade student got. Must be between 1 and 5.
	 */
	private int grade;
	
	/**
	 * A constructor for this class. It sets all the relevant data to some value.
	 * @param jmbag - jmbag of this new student.
	 * @param firstName - first name of this new student.
	 * @param lastName - last name of this new student.
	 * @param grade - grade of this new student. Must be between 1 and 5.
	 * @throws IllegalArgumentException if grade isn't between 1 and 5.
	 */
	public StudentRecord(String jmbag, String firstName, String lastName, int grade) {
		super();
		this.jmbag = jmbag;
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		if(grade < 1 || grade > 5 || jmbag.length() != 10) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * A getter for jmbag.
	 * @return student's jmbag.
	 */
	public String getJmbag() {
		return jmbag;
	}
	/**
	 * A getter for firstName.
	 * @return student's first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * A getter for lastName.
	 * @return student's last name.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * A getter for grade.
	 * @return student's grade.
	 */
	public int getGrade() {
		return grade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(jmbag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentRecord))
			return false;
		StudentRecord other = (StudentRecord) obj;
		return Objects.equals(jmbag, other.jmbag);
	}
	
	@Override
	public String toString() {
		return jmbag + " " + firstName + " " + lastName + " " + grade;
	}
}
