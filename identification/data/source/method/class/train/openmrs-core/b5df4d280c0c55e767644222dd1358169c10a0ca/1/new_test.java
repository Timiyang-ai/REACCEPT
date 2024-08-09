	@Test
	public void getOrderFrequencyByConcept_shouldReturnTheOrderFrequencyThatMatchesTheSpecifiedConcept() {
		Concept concept = conceptService.getConcept(4);
		assertEquals(3, orderService.getOrderFrequencyByConcept(concept).getOrderFrequencyId().intValue());
	}