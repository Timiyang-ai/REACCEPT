	@Test
	public void getCompatibleNames_shouldExcludeIncompatibleCountryLocales() {
		Locale primaryLocale = Locale.US;
		Concept testConcept = createConcept(1, primaryLocale);
		
		// concept should only have US and generic english names.
		// add an incompatible name -- en_UK
		int initialNameCollectionSize = testConcept.getNames().size();
		ConceptName name_en_UK = createConceptName(initialNameCollectionSize + 1, "Labour", Locale.UK,
		    ConceptNameType.FULLY_SPECIFIED, false);
		testConcept.addName(name_en_UK);
		
		Collection<ConceptName> compatibleNames = testConcept.getCompatibleNames(primaryLocale);
		
		assertFalse(compatibleNames.contains(name_en_UK));
	}