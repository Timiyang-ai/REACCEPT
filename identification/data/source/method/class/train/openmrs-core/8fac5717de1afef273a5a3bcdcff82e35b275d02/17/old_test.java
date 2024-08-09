	@Test
	public void getConceptsWithDrugsInFormulary_shouldGiveAListOfAllMatchingConcepts() {
		int matchingConcepts = 2;
		List<Concept> concepts = conceptService.getConceptsWithDrugsInFormulary();
		assertEquals(matchingConcepts, concepts.size());
	}