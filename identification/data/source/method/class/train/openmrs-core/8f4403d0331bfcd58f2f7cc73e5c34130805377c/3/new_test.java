	@Test
	public void getShortNameInLocale_shouldReturnTheBestShortNameForAConcept() {
		Concept concept = new Concept();
		concept.addName(new ConceptName("Giant cat", new Locale("en")));
		concept.addName(new ConceptName("Gato gigante", new Locale("es", "MX")));
		
		ConceptName shortName1 = new ConceptName("Cat", new Locale("en"));
		shortName1.setConceptNameType(ConceptNameType.SHORT);
		concept.addName(shortName1);
		
		ConceptName shortName2 = new ConceptName("Gato", new Locale("es"));
		shortName2.setConceptNameType(ConceptNameType.SHORT);
		concept.addName(shortName2);
		
		Assert.assertEquals("Gato", concept.getShortNameInLocale(new Locale("es", "ES")).getName());
	}