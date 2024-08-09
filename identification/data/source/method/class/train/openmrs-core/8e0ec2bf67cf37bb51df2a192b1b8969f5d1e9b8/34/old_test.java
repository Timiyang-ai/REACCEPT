	@Test
	public void getSynonyms_shouldSortPreferredFirst() {
		Concept concept = new Concept();
		ConceptName conceptNameNotPreferred = new ConceptName("Non Preferred", Locale.ENGLISH);
		ConceptName conceptNameNotPreferred2 = new ConceptName("Non Preferred2", Locale.ENGLISH);
		ConceptName conceptNamePreferred = new ConceptName("Preferred", Locale.ENGLISH);
		conceptNamePreferred.setLocalePreferred(true);
		concept.addName(conceptNameNotPreferred);
		concept.addName(conceptNameNotPreferred2);
		concept.addName(conceptNamePreferred);
		
		conceptNameNotPreferred.setConceptNameType(null);
		conceptNameNotPreferred2.setConceptNameType(null);
		conceptNamePreferred.setConceptNameType(null);
		
		ConceptName conceptNameExpectedPreferred = concept.getSynonyms(Locale.ENGLISH).iterator().next();
		assertEquals("Preferred", conceptNameExpectedPreferred.getName());
	}