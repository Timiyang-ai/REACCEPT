	@Test
	public void getConcepts_shouldGiveAListOfConceptSearchResultForTheMatchingConcepts() {
		Locale locale = new Locale("en", "GB");
		String phrase = "CD4 COUNT";
		List<ConceptSearchResult> res = conceptService.getConcepts(phrase, locale, true);
		assertEquals(res.get(0).getConceptName().getName(), phrase);
	}