@Test
	public void findBatchOfConcepts_shouldNotReturnConceptByGivenIdIfClassnameIsNotIncluded() throws Exception {
		String phrase = "1000";
		Concept expected = Context.getConceptService().getConcept(phrase);
		// prepare include concept classnames list and
		// intentionally do not add expected concept class name
		List<String> includeClassNames = new ArrayList<String>();
		includeClassNames.add("test");
		List<Object> result = dwrConceptService.findBatchOfConcepts(phrase, Boolean.FALSE, includeClassNames, null, null,
		    null, null, null);
		Assert.assertNotNull(result);
		Assert.assertFalse(isConceptFound(expected, result));
	}