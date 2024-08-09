@Test
  public void testGetCorpusProcessingState() {
    final CorpusProcessingState corpusProcessingState =
        service.getCorpusProcessingState(Corpus.IBM_RESEARCHERS);
    Assert.assertNotNull(corpusProcessingState);
  }