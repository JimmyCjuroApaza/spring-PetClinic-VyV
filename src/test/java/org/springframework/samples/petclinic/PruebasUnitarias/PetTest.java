package org.springframework.samples.petclinic.owner;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

	private static Validator validator;

	@BeforeAll
	static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void testNameValidation() {
		Pet pet = new Pet();
		pet.setName("");

		Set<ConstraintViolation<Pet>> violations = validator.validate(pet);
		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("must not be blank")));
	}

	@Test
	void testBirthDateGetterSetter() {
		Pet pet = new Pet();
		LocalDate date = LocalDate.of(2020, 1, 1);
		pet.setBirthDate(date);
		assertEquals(date, pet.getBirthDate());
	}

	@Test
	void testAddVisit() {
		Pet pet = new Pet();
		Visit visit = new Visit();

		pet.addVisit(visit);
		assertTrue(pet.getVisits().contains(visit));
	}

}
