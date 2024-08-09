@Test
	@Verifies(value = "should only allow one preferred name", method = "setPreferredName(ConceptName)")
	public void setPreferredName_shouldOnlyAllowOnePreferredName() throws Exception {
		Locale primaryLocale = Locale.US;
		Concept testConcept = createMockConcept(1, primaryLocale);
		
		ConceptName initialPreferred = ConceptNameTest.createMockConceptName(3, primaryLocale, null, true);
		testConcept.addName(initialPreferred);
		Assert.assertEquals(true, initialPreferred.isLocalePreferred());
		ConceptName newPreferredName = ConceptNameTest.createMockConceptName(4, primaryLocale, null, false);
		testConcept.setPreferredName(newPreferredName);
		
		assertEquals(false, initialPreferred.isLocalePreferred());
		assertEquals(true, newPreferredName.isLocalePreferred());
	}