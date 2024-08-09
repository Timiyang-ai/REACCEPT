	@Test
	public void getAllConceptStopWords_shouldReturnAllConceptStopWords() {
		List<ConceptStopWord> conceptStopWords = conceptService.getAllConceptStopWords();
		assertEquals(4, conceptStopWords.size());
	}