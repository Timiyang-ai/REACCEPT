	@Test
	public void getDrugDosingUnits_shouldReturnAListIfGPIsSet() {
		List<Concept> dosingUnits = orderService.getDrugDosingUnits();
		assertEquals(2, dosingUnits.size());
		assertThat(dosingUnits, containsInAnyOrder(hasId(50), hasId(51)));
	}