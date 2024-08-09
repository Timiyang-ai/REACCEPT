	@Test
	public void getConcepts_shouldReturnConceptSearchResultsThatMatchUniqueConcepts() {
		executeDataSet("org/openmrs/api/include/ConceptServiceTest-names.xml");
		List<ConceptSearchResult> searchResults = conceptService.getConcepts("trust", Collections
		        .singletonList(Locale.ENGLISH), false, null, null, null, null, null, null, null);
		//trust is included in 2 names for conceptid=3000 and in one name for conceptid=4000.
		//So we should see 2 results only
		Assert.assertEquals(2, searchResults.size());
	}