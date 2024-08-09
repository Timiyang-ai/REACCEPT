@Test
	@Verifies(value = "should always have a best name even if none match locale", method = "getBestName(Locale)")
	public void getBestName_shouldAlwaysHaveABestNameEvenIfNoneMatchLocale() throws Exception {
		Locale primaryLocale = Locale.US;
		Concept testConcept = createMockConcept(1, primaryLocale);
		
		ConceptName bestNameForNonExistentLocale = testConcept.getBestName(Locale.JAPAN);
		assertNotNull(bestNameForNonExistentLocale);
		assertFalse(Locale.JAPAN.equals(bestNameForNonExistentLocale.getLocale()));
	}