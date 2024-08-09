	@Test
	public void getAllConceptNameLocales_shouldReturnAllLocalesForConceptNamesForThisConceptWithoutDuplicates() {
		Concept concept = new Concept();
		concept.addName(new ConceptName("name1", new Locale("en")));
		concept.addName(new ConceptName("name2", new Locale("en", "US")));
		concept.addName(new ConceptName("name3", new Locale("en", "UG")));
		concept.addName(new ConceptName("name4", new Locale("fr", "RW")));
		concept.addName(new ConceptName("name5", new Locale("en", "UK")));
		//add some names in duplicate locales
		concept.addName(new ConceptName("name6", new Locale("en", "US")));
		concept.addName(new ConceptName("name7", new Locale("en", "UG")));
		Set<Locale> localesForNames = concept.getAllConceptNameLocales();
		Assert.assertEquals(5, localesForNames.size());
	}