@Test
	@Verifies(value = "should only allow one preferred name", method = "setPreferredName(Locale,ConceptName)")
	public void setPreferredName_shouldOnlyAllowOnePreferredName() throws Exception {
		Locale primaryLocale = Locale.US;
		Concept testConcept = createMockConcept(1, primaryLocale);
		ConceptNameTag preferredTag = ConceptNameTag.preferredCountryTagFor(primaryLocale);
		
		ConceptName initialPreferred = testConcept.getPreferredName(primaryLocale);
		ConceptName expectedPreferred = ConceptNameTest.createMockConceptName(initialPreferred.getConceptNameId() + 10,
		    primaryLocale);
		testConcept.setPreferredName(primaryLocale, expectedPreferred);
		
		ConceptName actualPreferred = testConcept.getPreferredName(primaryLocale);
		
		assertNotSame(initialPreferred, actualPreferred);
		assertSame(expectedPreferred, actualPreferred);
		
		assertFalse(initialPreferred.hasTag(preferredTag));
		assertTrue(expectedPreferred.hasTag(preferredTag));
	}