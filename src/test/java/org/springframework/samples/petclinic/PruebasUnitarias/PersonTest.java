package org.springframework.samples.petclinic.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

	private static Validator validator;

	@BeforeAll
	static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void testFirstNameGetterSetter() {
		Person person = new Person();
		person.setFirstName("John");
		assertEquals("John", person.getFirstName());
	}

	@Test
	void testLastNameGetterSetter() {
		Person person = new Person();
		person.setLastName("Doe");
		assertEquals("Doe", person.getLastName());
	}

	@Test
	void testFirstNameNotBlank() {
		Person person = new Person();
		person.setFirstName(""); // Cadena vacía

		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("must not be blank")));
	}

	@Test
	void testLastNameNotBlank() {
		Person person = new Person();
		person.setLastName(""); // Cadena vacía

		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("must not be blank")));
	}

}
