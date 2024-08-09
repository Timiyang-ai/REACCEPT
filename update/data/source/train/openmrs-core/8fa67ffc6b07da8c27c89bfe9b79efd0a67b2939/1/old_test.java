@Test
	public void findBatchOfConcepts_shouldNotReturnConceptByGivenIdIfDatatypeIsNotIncluded() throws Exception {
		String phrase = "1000";
		Concept expected = Context.getConceptService().getConcept(phrase);
		// prepare include concept datatypes list and
		// intentionally do not add expected concept data type
		List<String> includeDatatypes = new ArrayList<String>();
		includeDatatypes.add("test");
		List<Object> result = dwrConceptService.findBatchOfConcepts(phrase, Boolean.FALSE, null, null, includeDatatypes,
		    null, null, null);
		Assert.assertNotNull(result);
		Assert.assertFalse(isConceptFound(expected, result));
	}