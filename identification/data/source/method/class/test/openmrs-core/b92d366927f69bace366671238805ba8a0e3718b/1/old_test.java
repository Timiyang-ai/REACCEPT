	@Test
	public void getDescription_shouldNotReturnLanguageOnlyMatchForExactMatches() {
		Concept mockConcept = new Concept();
		mockConcept.addDescription(new ConceptDescription("en desc", new Locale("en")));
		
		Assert.assertNull(mockConcept.getDescription(new Locale("en", "US"), true));
	}