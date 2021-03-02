package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComparisonOperatorsTest {

	@Test
	void testLess() {
		IComparisonOperator operator = ComparisonOperators.LESS; 
		assertTrue(operator.satisfied("Ana", "Jasna"));
		assertTrue(operator.satisfied("Ana", "Anan"));
		assertFalse(operator.satisfied("Banana", "Anan"));
		assertFalse(operator.satisfied("Anaa", "Ana"));
		assertFalse(operator.satisfied("Banana", "Banana"));
	}
	
	@Test
	void testLessOrEquals() {
		IComparisonOperator operator = ComparisonOperators.LESS_OR_EQUALS; 
		assertTrue(operator.satisfied("Ana", "Jasna"));
		assertTrue(operator.satisfied("Ana", "Anan"));
		assertFalse(operator.satisfied("Banana", "Anan"));
		assertFalse(operator.satisfied("Anaa", "Ana"));
		assertTrue(operator.satisfied("Banana", "Banana"));
	}
	
	@Test
	void testGreater() {
		IComparisonOperator operator = ComparisonOperators.GREATER; 
		assertFalse(operator.satisfied("Ana", "Jasna"));
		assertFalse(operator.satisfied("Ana", "Anan"));
		assertTrue(operator.satisfied("Banana", "Anan"));
		assertTrue(operator.satisfied("Anaa", "Ana"));
		assertFalse(operator.satisfied("Banana", "Banana"));
	}
	
	@Test
	void testGreaterOrEquals() {
		IComparisonOperator operator = ComparisonOperators.GREATER_OR_EQUALS; 
		assertFalse(operator.satisfied("Ana", "Jasna"));
		assertFalse(operator.satisfied("Ana", "Anan"));
		assertTrue(operator.satisfied("Banana", "Anan"));
		assertTrue(operator.satisfied("Anaa", "Ana"));
		assertTrue(operator.satisfied("Banana", "Banana"));
	}
	
	@Test
	void testEquals() {
		IComparisonOperator operator = ComparisonOperators.EQUALS; 
		assertFalse(operator.satisfied("Ana", "Jasna"));
		assertFalse(operator.satisfied("Ana", "Anan"));
		assertFalse(operator.satisfied("Banana", "Anan"));
		assertFalse(operator.satisfied("Anaa", "Ana"));
		assertTrue(operator.satisfied("Banana", "Banana"));
	}
	
	@Test
	void testNotEquals() {
		IComparisonOperator operator = ComparisonOperators.NOT_EQUALS; 
		assertTrue(operator.satisfied("Ana", "Jasna"));
		assertTrue(operator.satisfied("Ana", "Anan"));
		assertTrue(operator.satisfied("Banana", "Anan"));
		assertTrue(operator.satisfied("Anaa", "Ana"));
		assertFalse(operator.satisfied("Banana", "Banana"));
	}
	
	@Test
	void testLike() {
		IComparisonOperator operator = ComparisonOperators.LIKE; 
		assertFalse(operator.satisfied("Zagreb", "Aba*"));
		assertFalse(operator.satisfied("AAA", "AA*AA"));
		assertTrue(operator.satisfied("AAAA", "AA*AA"));
		assertFalse(operator.satisfied("Ana", "Anan"));
		assertTrue(operator.satisfied("Banana", "*"));
		assertFalse(operator.satisfied("Banana", "Banana*Ana"));
		assertTrue(operator.satisfied("Banana", "Banana*"));
		assertTrue(operator.satisfied("Banana", "*Banana"));
		assertTrue(operator.satisfied("Banana", "B*a"));
		assertTrue(operator.satisfied("Banana", "B*a"));
		assertTrue(operator.satisfied("Banana", "Ba*"));
		assertTrue(operator.satisfied("Banana", "*na"));
		assertTrue(operator.satisfied("Banana", "Ba*na"));
		assertThrows(IllegalArgumentException.class, () -> operator.satisfied("Banana", "Ba*a*a"));
	}

}
