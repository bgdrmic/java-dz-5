package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldValueGettersTest {

	private ArrayList<StudentRecord> students;
	
	@BeforeEach
	private void createDatabase() {
		students = new ArrayList<StudentRecord>();
		students.add(new StudentRecord("0001", "Pero", "Perić", 3));
		students.add(new StudentRecord("0002", "Ana", "Anić", 2));
		students.add(new StudentRecord("0003", "Ivo", "Ivić", 3));
		students.add(new StudentRecord("0004", "Ante", "Antić", 5));
	}
	
	@Test
	void testGetFirstName() {
		var nameGetter = FieldValueGetters.FIRST_NAME;
		assertEquals("Pero", nameGetter.get(students.get(0)));
		assertEquals("Ante", nameGetter.get(students.get(3)));
	}
	
	@Test
	void testGetLastName() {
		var nameGetter = FieldValueGetters.LAST_NAME;
		assertEquals("Perić", nameGetter.get(students.get(0)));
		assertEquals("Antić", nameGetter.get(students.get(3)));
	}
	
	@Test
	void testGetJmbag() {
		var nameGetter = FieldValueGetters.JMBAG;
		assertEquals("0001", nameGetter.get(students.get(0)));
		assertEquals("0004", nameGetter.get(students.get(3)));
	}

}
