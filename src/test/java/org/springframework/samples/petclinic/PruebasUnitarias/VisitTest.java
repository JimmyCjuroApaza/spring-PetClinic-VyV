package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VisitTest {

	@Test
	void testCreateVisitWithValidDateAndDescription() {
		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Consulta");

		assertNotNull(visit);
		assertEquals(LocalDate.now(), visit.getDate());
		assertEquals("Consulta", visit.getDescription());
	}

	@Test
	void testCreateVisitWithEmptyDescription() {
		Visit visit = new Visit();
		visit.setDate(LocalDate.now());

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			visit.setDescription("");
		});

		assertEquals("Description cannot be blank", exception.getMessage());
	}

	@Test
	void testCreateVisitWithPastDate() {
		Visit visit = new Visit();

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			visit.setDate(LocalDate.now().minusDays(1));
		});

		assertEquals("Date cannot be in the past", exception.getMessage());
	}

	@Test
	void testCreateVisitWithNullDate() {
		Visit visit = new Visit();

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			visit.setDate(null);
		});

		assertEquals("Date cannot be null", exception.getMessage());
	}

	@Test
	void testCreateVisitWithFutureDate() {
		Visit visit = new Visit();
		visit.setDate(LocalDate.now().plusDays(1));
		visit.setDescription("Chequeo");

		assertNotNull(visit);
		assertEquals(LocalDate.now().plusDays(1), visit.getDate());
		assertEquals("Chequeo", visit.getDescription());
	}

	@Test
	void testUpdateVisitDescription() {
		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Consulta");

		visit.setDescription("Consulta Actualizada");

		assertEquals("Consulta Actualizada", visit.getDescription());
	}

}
