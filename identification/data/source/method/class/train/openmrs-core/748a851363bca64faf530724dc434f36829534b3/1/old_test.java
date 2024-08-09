@Test
	@Verifies(value = "should return null if no name is explicitly marked as locale preferred", method = "getPreferredName(Locale)")
	public void getPreferredName_shouldReturnNullIfNoNameIsExplicitlyMarkedAsLocalePreferred() throws Exception {
		Concept testConcept = createMockConcept(1, Locale.US);
		//preferred name in en_US
		ConceptName preferredNameEN_US = ConceptNameTest.createMockConceptName(3, Locale.US, null, false);
		testConcept.addName(preferredNameEN_US);
		//preferred name in en
		ConceptName preferredNameEN = ConceptNameTest.createMockConceptName(4, new Locale("en"), null, false);
		testConcept.addName(preferredNameEN);
		Assert.assertNull(testConcept.getPreferredName(Locale.US));
		
	}