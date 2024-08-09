@Test
	@Verifies(value = "should match to best name", method = "getPreferredName(Locale)")
	public void getPreferredName_shouldMatchToBestName() throws Exception {
		Locale primaryLocale = Locale.US;
		Concept testConcept = createMockConcept(1, primaryLocale);
		
		ConceptName preferredName = testConcept.getPreferredName(primaryLocale);
		ConceptName bestName = testConcept.getBestName(primaryLocale);
		
		assertSame(preferredName, bestName);
	}