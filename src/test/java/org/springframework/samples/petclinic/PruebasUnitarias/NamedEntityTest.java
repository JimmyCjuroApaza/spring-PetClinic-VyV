package org.springframework.samples.petclinic.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NamedEntityTest {

	private static Validator validator;

	@BeforeAll
	static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void testNameGetterSetter() {
		NamedEntity namedEntity = new NamedEntity();
		namedEntity.setName("TestName");
		assertEquals("TestName", namedEntity.getName());
	}

	@Test
	void testNameNotBlank() {
		NamedEntity namedEntity = new NamedEntity();
		namedEntity.setName(""); // Cadena vacía

		Set<ConstraintViolation<NamedEntity>> violations = validator.validate(namedEntity);
		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("must not be blank")));
	}

}
