	@Test
	public void getConceptDatatypeByName_shouldNotReturnAFuzzyMatchOnName() {
		executeDataSet(INITIAL_CONCEPTS_XML);
		ConceptDatatype result = conceptService.getConceptDatatypeByName("Tex");
		Assert.assertNull(result);
	}