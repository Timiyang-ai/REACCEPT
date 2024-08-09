	@Test
	public void getConcepts_shouldNotReturnConceptsWithMatchingNamesThatAreVoided() {
		Concept concept = dao.getConcept(7);
		updateSearchIndex();
		List<Concept> concepts = dao.getConcepts("VOIDED", null, false, new ArrayList<>(),
				new ArrayList<>());
		Assert.assertEquals(0, concepts.size());
	}