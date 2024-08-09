	@Test
	public void purgeConcept_shouldPurgeTheConceptIfNotBeingUsedByAnObs() {
		int conceptId = 88;
		conceptService.purgeConcept(conceptService.getConcept(conceptId));
		assertNull(conceptService.getConcept(conceptId));
	}