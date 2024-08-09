	@Test
	public void getConceptSourceByName_shouldGetConceptSourceWithTheGivenName() {
		ConceptSource conceptSource = conceptService.getConceptSourceByName("SNOMED CT");
		assertEquals("Method did not retrieve ConceptSource by name", Integer.valueOf(2), conceptSource.getConceptSourceId());
	}