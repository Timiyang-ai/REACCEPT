@Test
	public void testListDocuments() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ConceptInsights.CURSOR, 0);
		params.put(ConceptInsights.LIMIT, 20);

		Documents documents = service.listDocuments(Corpus.TED_TALKS, params);
		Assert.assertNotNull(documents);
	}