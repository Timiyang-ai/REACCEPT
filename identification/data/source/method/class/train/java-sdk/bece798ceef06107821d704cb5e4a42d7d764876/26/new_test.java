@Test
  public void testConceptualSearch() {
    final Map<String, Object> params = new HashMap<String, Object>();
    final List<String> ids = new ArrayList<String>();
    ids.add(EXAMPLE_CONCEPT.getId());
    params.put(ConceptInsights.IDS, ids);
    params.put(ConceptInsights.LIMIT, 10);
    params.put(ConceptInsights.CURSOR, 0);
    final QueryConcepts cp = service.conceptualSearch(Corpus.TED_TALKS, params);
    Assert.assertNotNull(cp);
  }