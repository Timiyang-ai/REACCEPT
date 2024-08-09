@Test
	@Verifies(value = "should return the shortest name for the concept from any locale if exact is false", method = "getShortestName(Locale,Boolean)")
	public void getShortestName_shouldReturnTheShortestNameForTheConceptFromAnyLocaleIfExactIsFalse() throws Exception {
		Concept concept = new Concept();
		concept.addName(new ConceptName("shortName123", Context.getLocale()));
		concept.addName(new ConceptName("shortName12", Context.getLocale()));
		concept.addName(new ConceptName("shortName1", Locale.US));
		concept.addName(new ConceptName("shortName", Locale.FRANCE));
		Assert.assertEquals("shortName", concept.getShortestName(Context.getLocale(), false).getName());
	}