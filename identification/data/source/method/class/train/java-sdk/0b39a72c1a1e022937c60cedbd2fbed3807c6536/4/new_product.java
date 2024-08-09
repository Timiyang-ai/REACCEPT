public CorpusStats getCorpusStats(final Corpus corpus) {
		String corpusId = IDValidator.getCorpusId(corpus, getAccountId());
		return executeRequest(API_VERSION + corpusId + STATS_PATH, null, CorpusStats.class);
	}