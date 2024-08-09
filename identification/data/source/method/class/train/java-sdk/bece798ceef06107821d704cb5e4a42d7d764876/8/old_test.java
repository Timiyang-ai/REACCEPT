@Test
	public void testGetConceptRelatedConcepts() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ConceptInsights.LIMIT, 10);
		params.put(ConceptInsights.LEVEL, 1);
		RequestedFields fs = new RequestedFields();
		fs.include("abstract");
		params.put("concept_fields", fs);
		Concepts concepts = service.getConceptRelatedConcepts(EXAMPLE_CONCEPT, params);
		Assert.assertNotNull(concepts);
		
	}