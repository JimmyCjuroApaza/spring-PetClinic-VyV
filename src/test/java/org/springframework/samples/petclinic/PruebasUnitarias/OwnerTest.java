package org.springframework.samples.petclinic.owner;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

	private static Validator validator;

	@BeforeAll
	static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void testAddressGetterSetter() {
		Owner owner = new Owner();
		owner.setAddress("123 Street");
		assertEquals("123 Street", owner.getAddress());
	}

	@Test
	void testCityGetterSetter() {
		Owner owner = new Owner();
		owner.setCity("CityName");
		assertEquals("CityName", owner.getCity());
	}

	@Test
	void testTelephoneGetterSetter() {
		Owner owner = new Owner();
		owner.setTelephone("1234567890");
		assertEquals("1234567890", owner.getTelephone());
	}

	@Test
	void testTelephoneValidationFailsForInvalidInput() {
		Owner owner = new Owner();
		owner.setTelephone("123"); // Menos de 10 dígitos

		Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Telephone must be a 10-digit number")));
	}

	@Test
	void testAddPetAndGetPet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Buddy");

		owner.addPet(pet);
		assertTrue(owner.getPets().contains(pet));

		Pet retrievedPet = owner.getPet("Buddy");
		assertNotNull(retrievedPet);
		assertEquals("Buddy", retrievedPet.getName());
	}

	@Test
	void testGetPetReturnsNullIfNotFound() {
		Owner owner = new Owner();
		assertNull(owner.getPet("Nonexistent"));
	}

	@Test
	void testAddVisit() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(1);
		owner.addPet(pet);

		Visit visit = new Visit();
		owner.addVisit(1, visit);

		assertTrue(pet.getVisits().contains(visit));
	}

}
