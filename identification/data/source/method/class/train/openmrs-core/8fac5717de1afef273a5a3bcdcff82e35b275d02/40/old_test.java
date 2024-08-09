	@Test
	public void getSetsContainingConcept_shouldGiveAListOfConceptSetContainingTheGivenConcept() {
		Concept concept = conceptService.getConcept(18);
		List<ConceptSet> conceptSets = conceptService.getSetsContainingConcept(concept);
		assertNotNull(conceptSets);
		assertEquals(conceptSets.get(0).getConcept(), concept);
	}