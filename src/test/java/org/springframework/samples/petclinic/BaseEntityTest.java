package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

	@Test
	void testIdGetterSetter() {
		BaseEntity entity = new BaseEntity();
		assertNull(entity.getId()); // ID inicial es null

		entity.setId(1);
		assertEquals(1, entity.getId()); // ID debe ser 1
	}

	@Test
	void testIsNew() {
		BaseEntity entity = new BaseEntity();
		assertTrue(entity.isNew()); // Si ID es null, es nuevo

		entity.setId(1);
		assertFalse(entity.isNew()); // Si ID no es null, no es nuevo
	}

}
