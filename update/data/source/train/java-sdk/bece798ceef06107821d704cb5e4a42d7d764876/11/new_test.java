@Test
  public void testGetDocumentRelatedConcepts() {
    final Map<String, Object> params = new HashMap<String, Object>();
    params.put(ConceptInsights.LEVEL, "1");
    params.put(ConceptInsights.LIMIT, 10);
    final Concepts concepts = service.getDocumentRelatedConcepts(EXAMPLE_DOCUMENT, params);
    Assert.assertNotNull(concepts);
  }