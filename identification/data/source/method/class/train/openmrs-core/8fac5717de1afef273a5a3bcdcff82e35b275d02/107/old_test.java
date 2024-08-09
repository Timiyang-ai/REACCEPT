	@Test
	public void getNextConcept_shouldReturnTheConceptNextToTheGivenConcept() {
		Integer conceptId = 3;
		Integer nextConceptId = 4;
		Concept returnedConcept = conceptService.getNextConcept(conceptService.getConcept(conceptId));
		assertEquals(returnedConcept, conceptService.getConcept(nextConceptId));
	}