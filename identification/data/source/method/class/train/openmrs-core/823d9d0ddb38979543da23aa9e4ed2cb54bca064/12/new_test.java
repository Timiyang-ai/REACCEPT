	@Test
	public void retireConcept_shouldFailIfNoReasonIsGiven() {
		Concept concept = conceptService.getConcept(3);
		expectedException.expect(IllegalArgumentException.class);
		conceptService.retireConcept(concept, "");
	}