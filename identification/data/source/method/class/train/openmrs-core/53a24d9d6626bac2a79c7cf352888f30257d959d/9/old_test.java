	@Test
	public void getDurationUnits_shouldReturnAListIfGPIsSet() {
		List<Concept> durationConcepts = orderService.getDurationUnits();
		assertEquals(1, durationConcepts.size());
		assertEquals(28, durationConcepts.get(0).getConceptId().intValue());
	}