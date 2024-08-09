@Test
	public void testGetCorpusProcessingState() {
		CorpusProcessingState corpusProcessingState = service.getCorpusProcessingState(Corpus.IBM_RESEARCHERS);
		Assert.assertNotNull(corpusProcessingState);
	}