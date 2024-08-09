@Test
	public void testGetCorpusStats() {
		CorpusStats corpusStats = service.getCorpusStats(Corpus.IBM_RESEARCHERS);
		Assert.assertNotNull(corpusStats);
	}