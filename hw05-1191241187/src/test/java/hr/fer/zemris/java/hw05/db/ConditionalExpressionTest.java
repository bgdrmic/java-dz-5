package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConditionalExpressionTest {

	private ArrayList<StudentRecord> students;
	
	@BeforeEach
	private void createDatabase() {
		students = new ArrayList<StudentRecord>();
		students.add(new StudentRecord("0001", "Pero", "Perić", 3));
		students.add(new StudentRecord("0002", "Ana", "Anić", 2));
		students.add(new StudentRecord("0003", "Anita", "Ivić", 3));
		students.add(new StudentRecord("0004", "Ana", "Antić", 5));
	}
	
	@Test
	void test1() {
		var output = new ArrayList<StudentRecord>();
		var expectedOutput = new ArrayList<StudentRecord>();
		expectedOutput.add(students.get(1));
		expectedOutput.add(students.get(3));
		var expr = new ConditionalExpression(FieldValueGetters.FIRST_NAME, "Ana", ComparisonOperators.EQUALS);
		for(var student : students) {
			if(expr.getComparisonOperator().satisfied(
					expr.getFieldGetter().get(student),
					expr.getStringLiteral()
					)) {
				output.add(student);
			}
		}
		assertTrue(output.equals(expectedOutput));
	}
	
	@Test
	void test2() {
		var output = new ArrayList<StudentRecord>();
		var expectedOutput = new ArrayList<StudentRecord>();
		expectedOutput.add(students.get(0));
		expectedOutput.add(students.get(2));
		var expr = new ConditionalExpression(FieldValueGetters.FIRST_NAME, "Ana", ComparisonOperators.GREATER);
		for(var student : students) {
			if(expr.getComparisonOperator().satisfied(
					expr.getFieldGetter().get(student),
					expr.getStringLiteral()
					)) {
				output.add(student);
			}
		}
		assertTrue(output.equals(expectedOutput));
	}
	
	@Test
	void test3() {
		var output = new ArrayList<StudentRecord>();
		var expectedOutput = new ArrayList<StudentRecord>();
		expectedOutput.add(students.get(1));
		expectedOutput.add(students.get(2));
		expectedOutput.add(students.get(3));
		var expr = new ConditionalExpression(FieldValueGetters.FIRST_NAME, "A*a", ComparisonOperators.LIKE);
		for(var student : students) {
			if(expr.getComparisonOperator().satisfied(
					expr.getFieldGetter().get(student),
					expr.getStringLiteral()
					)) {
				output.add(student);
			}
		}
		assertTrue(output.equals(expectedOutput));
	}

}
