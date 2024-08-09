	@Test
	public void getFullySpecifiedName_shouldReturnTheNameMarkedAsFullySpecifiedForTheGivenLocale() {
		Locale primaryLocale = Locale.US;
		Concept testConcept = createConcept(1, primaryLocale);
		ConceptName fullySpecifiedName_FR = createConceptName(3, "Docteur", new Locale("fr"),
		    ConceptNameType.FULLY_SPECIFIED, true);
		testConcept.addName(fullySpecifiedName_FR);
		Assert.assertEquals(primaryLocale, testConcept.getFullySpecifiedName(primaryLocale).getLocale());
		Assert.assertEquals(ConceptNameType.FULLY_SPECIFIED,
		    testConcept.getFullySpecifiedName(primaryLocale).getConceptNameType());
	}