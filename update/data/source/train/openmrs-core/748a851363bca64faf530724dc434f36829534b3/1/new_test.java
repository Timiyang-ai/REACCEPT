@Test
	@Verifies(value = "should return the fully specified name if no name is explicitly marked as locale preferred", method = "getPreferredName(Locale)")
	public void getPreferredName_shouldReturnTheFullySpecifiedNameIfNoNameIsExplicitlyMarkedAsLocalePreferred()
	                                                                                                           throws Exception {
		Concept testConcept = createMockConcept(1, Locale.US);
		//preferred name in en_US
		ConceptName preferredNameEN_US = ConceptNameTest.createMockConceptName(3, Locale.US, null, false);
		testConcept.addName(preferredNameEN_US);
		String fullySpecName = testConcept.getFullySpecifiedName(Locale.US).getName();
		//preferred name in en
		ConceptName preferredNameEN = ConceptNameTest.createMockConceptName(4, new Locale("en"), null, false);
		testConcept.addName(preferredNameEN);
		Assert.assertEquals(fullySpecName, testConcept.getPreferredName(Locale.US).getName());
	}