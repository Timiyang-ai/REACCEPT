@Test
  public void testGetCorpusStats() {
    final CorpusStats corpusStats = service.getCorpusStats(Corpus.IBM_RESEARCHERS);
    Assert.assertNotNull(corpusStats);
  }