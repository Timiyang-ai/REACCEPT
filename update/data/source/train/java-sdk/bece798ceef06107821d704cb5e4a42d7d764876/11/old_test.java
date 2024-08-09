@Test
	public void testGetDocumentRelatedConcepts() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ConceptInsights.LEVEL, "1");
		params.put(ConceptInsights.LIMIT, 10);
		Concepts concepts = service.getDocumentRelatedConcepts(EXAMPLE_DOCUMENT, params);
		Assert.assertNotNull(concepts);
	}