package hr.fer.zemris.lsystems.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;

class LSystemBuilderImplTest {

	LSystem system;
	LSystemBuilder systemBuilder;
	
	@BeforeEach
	private void createLSystem() {
		systemBuilder = new LSystemBuilderImpl();
		systemBuilder.setAxiom("F");
		systemBuilder.registerProduction('F', "F+F--F+F");
		system = systemBuilder.build();
	}
	
	@Test
	void testGenerate() {
		assertEquals("F", system.generate(0));
		assertEquals("F+F--F+F", system.generate(1));
		assertEquals("F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F", system.generate(2));
	}

}
