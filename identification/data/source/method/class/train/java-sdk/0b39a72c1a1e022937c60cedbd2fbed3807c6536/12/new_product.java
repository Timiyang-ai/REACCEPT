public Corpus getCorpus(final Corpus corpus) {
		String corpusId = IDValidator.getCorpusId(corpus, getAccountId());
		return executeRequest(API_VERSION + corpusId, null, Corpus.class);
	}