@Test
	public void testGetGraphsRelatedConcepts() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> concepts = new ArrayList<String>();
		concepts.add(EXAMPLE_CONCEPT.getId());
		params.put(ConceptInsights.CONCEPTS, concepts);
		params.put(ConceptInsights.LIMIT, 10);
		params.put(ConceptInsights.LEVEL, 1);
		RequestedFields fs = new RequestedFields();
		fs.include("abstract");
		params.put("concept_fields", fs);
		Concepts concepts1 = service.getGraphsRelatedConcepts(Graph.WIKIPEDIA, params);
		Assert.assertNotNull(concepts1);
	}