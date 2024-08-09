	@Test
	public void findConceptAnswers_shouldReturnAListOfAllMatchingConceptSearchResults() {
		Locale locale = new Locale("en", "GB");
		String phrase = "CD4 COUNT";
		int conceptId = 5497;
		List<ConceptSearchResult> concepts = conceptService.findConceptAnswers(phrase, locale,
		    conceptService.getConcept(conceptId));
		assertEquals(concepts.get(0).getConceptName().getName(), phrase);
	}