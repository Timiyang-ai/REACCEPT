@Test
  public void testGetCorpusRelationScores() {
    final List<Concept> concepts = new ArrayList<Concept>();
    concepts.add(EXAMPLE_CONCEPT);
    final Scores scores = service.getCorpusRelationScores(Corpus.IBM_RESEARCHERS, concepts);
    Assert.assertNotNull(scores);
  }