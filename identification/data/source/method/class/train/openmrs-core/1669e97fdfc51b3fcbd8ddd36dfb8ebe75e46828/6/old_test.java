	@Test
	public void getPreferredName_shouldReturnTheFullySpecifiedNameIfNoNameIsExplicitlyMarkedAsLocalePreferred() {
		Concept testConcept = createConcept(1, Locale.US);
		//preferred name in en_US
		ConceptName preferredNameEN_US = createConceptName(3, "Aspirin", Locale.US, null, false);
		testConcept.addName(preferredNameEN_US);
		String fullySpecName = testConcept.getFullySpecifiedName(Locale.US).getName();
		//preferred name in en
		ConceptName preferredNameEN = createConceptName(4, "Doctor", new Locale("en"), null, false);
		testConcept.addName(preferredNameEN);
		Assert.assertEquals(fullySpecName, testConcept.getPreferredName(Locale.US).getName());
	}