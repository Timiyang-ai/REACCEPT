@Test
	public void testGetGraphRelatedConcepts() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(EXAMPLE_CONCEPT);
		params.put(ConceptInsights.LIMIT, 10);
		params.put(ConceptInsights.LEVEL, 1);
		RequestedFields fs = new RequestedFields();
		fs.include("abstract");
		params.put("concept_fields", fs);
		Concepts conceptResults = service.getGraphRelatedConcepts(Graph.WIKIPEDIA, concepts, params);
		Assert.assertNotNull(conceptResults);
	}