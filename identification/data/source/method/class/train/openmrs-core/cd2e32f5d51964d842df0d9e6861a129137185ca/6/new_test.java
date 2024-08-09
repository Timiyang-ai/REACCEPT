	@Test
	public void setPreferredName_shouldOnlyAllowOnePreferredName() {
		Locale primaryLocale = Locale.US;
		Concept testConcept = createConcept(1, primaryLocale);
		
		ConceptName initialPreferred = createConceptName(3, "Aspirin", primaryLocale, null, true);
		testConcept.addName(initialPreferred);
		Assert.assertEquals(true, initialPreferred.getLocalePreferred());
		ConceptName newPreferredName = createConceptName(4, "Doctor", primaryLocale, null, false);
		testConcept.setPreferredName(newPreferredName);
		
		assertEquals(false, initialPreferred.getLocalePreferred());
		assertEquals(true, newPreferredName.getLocalePreferred());
	}