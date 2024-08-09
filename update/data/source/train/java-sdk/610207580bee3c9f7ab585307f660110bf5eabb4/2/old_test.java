@Test
  public void testGetConceptRelatedConcepts() {
    final Map<String, Object> params = new HashMap<String, Object>();
    params.put(ConceptInsights.LIMIT, 10);
    params.put(ConceptInsights.LEVEL, 1);
    final RequestedFields fs = new RequestedFields();
    fs.include("abstract");
    params.put("concept_fields", fs);
    final Concepts concepts = service.getConceptRelatedConcepts(EXAMPLE_CONCEPT, params);
    Assert.assertNotNull(concepts);

  }