@Test
	public void testGetCorpus() {
		Corpus corpus = service.getCorpus(Corpus.IBM_RESEARCHERS);
		Assert.assertNotNull(corpus);
		corpus = service.getCorpus(Corpus.TED_TALKS);
		Assert.assertNotNull(corpus);
	}