	@Test
	public void getPrevConcept_shouldReturnTheConceptPreviousToTheGivenConcept() {
		Integer conceptId = 4;
		Integer prevConceptId = 3;
		Concept returnedConcept = conceptService.getPrevConcept(conceptService.getConcept(conceptId));
		assertEquals(returnedConcept, conceptService.getConcept(prevConceptId));
	}