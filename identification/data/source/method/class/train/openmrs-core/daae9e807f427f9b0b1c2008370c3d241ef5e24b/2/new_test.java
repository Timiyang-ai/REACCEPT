	@Test
	public void getConceptStopWords_shouldReturnDefaultLocaleConceptStopWordsIfLocaleIsNull() {
		List<String> conceptStopWords = conceptService.getConceptStopWords(null);
		assertEquals(1, conceptStopWords.size());
	}