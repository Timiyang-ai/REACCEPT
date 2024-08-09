@Test
  public void testGetDocumentRelationScores() {
    final List<Concept> concepts = new ArrayList<Concept>();
    concepts.add(EXAMPLE_CONCEPT);
    final Scores scores = service.getDocumentRelationScores(EXAMPLE_DOCUMENT, concepts);
    Assert.assertNotNull(scores);
  }