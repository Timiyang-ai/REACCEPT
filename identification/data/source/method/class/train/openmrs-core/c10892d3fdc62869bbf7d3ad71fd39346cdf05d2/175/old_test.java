	@Test
	public void getConceptsByAnswer_shouldFindAnswersForConcept() {
		Concept concept = conceptService.getConcept(7);
		Assert.assertNotNull(concept);
		List<Concept> concepts = conceptService.getConceptsByAnswer(concept);
		Assert.assertEquals(1, concepts.size());
		Assert.assertEquals(21, concepts.get(0).getId().intValue());
	}