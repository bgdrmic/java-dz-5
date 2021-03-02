package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentDatabaseTest {

	private StudentDatabase database;
	
	@BeforeEach
	private void createDatabase() {
		var students = new ArrayList<String>();
		students.add((new StudentRecord("0001", "Pero", "Perić", 3)).toString());
		students.add((new StudentRecord("0002", "Ana", "Anić", 2)).toString());
		students.add((new StudentRecord("0003", "Ivo", "Ivić", 3)).toString());
		students.add((new StudentRecord("0004", "Ante", "Antić", 5)).toString());
		database = new StudentDatabase(students);
	}
	
	
	@Test
	void testForJMBAG() {
		assertEquals("Pero", database.forJMBAG("0001").getFirstName());
		assertEquals(3, database.forJMBAG("0003").getGrade());
		assertEquals("Ante", database.forJMBAG("0004").getFirstName());
	}
	
	@Test
	void testFilter1() {
		IFilter filter = student -> { return false; };
		assertEquals(0, database.filter(filter).size());
	}
	
	@Test
	void testFilter2() {
		IFilter filter = student -> { return true; };
		assertEquals(4, database.filter(filter).size());
	}

}
