@Test
  public void testGetGraphRelatedConcepts() {
    final Map<String, Object> params = new HashMap<String, Object>();
    final List<Concept> concepts = new ArrayList<Concept>();
    concepts.add(EXAMPLE_CONCEPT);
    params.put(ConceptInsights.LIMIT, 10);
    params.put(ConceptInsights.LEVEL, 1);
    final RequestedFields fs = new RequestedFields();
    fs.include("abstract");
    fs.include("link");
    fs.include("name");
    params.put(ConceptInsights.CONCEPT_FIELDS, fs);
    Concepts conceptResults = service.getGraphRelatedConcepts(Graph.WIKIPEDIA, concepts, params);
    Assert.assertNotNull(conceptResults);
    Assert.assertTrue(!conceptResults.getConcepts().isEmpty());
    Assert.assertNotNull(conceptResults.getConcepts().get(0).getConcept().getAbstract());


  }