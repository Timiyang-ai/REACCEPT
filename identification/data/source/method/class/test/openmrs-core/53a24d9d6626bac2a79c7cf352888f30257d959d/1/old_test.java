	@Test
	public void getDrugDispensingUnits_shouldReturnTheUnionOfTheDosingAndDispensingUnits() {
		List<Concept> dispensingUnits = orderService.getDrugDispensingUnits();
		assertEquals(2, dispensingUnits.size());
		assertThat(dispensingUnits, containsInAnyOrder(hasId(50), hasId(51)));
	}