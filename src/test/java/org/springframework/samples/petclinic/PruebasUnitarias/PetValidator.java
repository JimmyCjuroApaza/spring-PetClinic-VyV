package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PetValidatorTest {

	private final PetValidator validator = new PetValidator();

	@Test
	void supportsClass() {
		assertTrue(validator.supports(Pet.class));
		assertFalse(validator.supports(Owner.class));
	}

	@Test
	void validateValidPet() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		pet.setBirthDate(LocalDate.of(2020, 1, 1));
		pet.setType(new PetType());

		Errors errors = new BeanPropertyBindingResult(pet, "pet");
		validator.validate(pet, errors);

		assertFalse(errors.hasErrors());
	}

	@Test
	void validatePetWithMissingName() {
		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(2020, 1, 1));
		pet.setType(new PetType());

		Errors errors = new BeanPropertyBindingResult(pet, "pet");
		validator.validate(pet, errors);

		assertTrue(errors.hasFieldErrors("name"));
		assertEquals("required", errors.getFieldError("name").getCode());
	}

	@Test
	void validatePetWithMissingBirthDate() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		pet.setType(new PetType());

		Errors errors = new BeanPropertyBindingResult(pet, "pet");
		validator.validate(pet, errors);

		assertTrue(errors.hasFieldErrors("birthDate"));
		assertEquals("required", errors.getFieldError("birthDate").getCode());
	}

	@Test
	void validatePetWithMissingType() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		pet.setBirthDate(LocalDate.of(2020, 1, 1));

		Errors errors = new BeanPropertyBindingResult(pet, "pet");
		validator.validate(pet, errors);

		assertTrue(errors.hasFieldErrors("type"));
		assertEquals("required", errors.getFieldError("type").getCode());
	}

}
