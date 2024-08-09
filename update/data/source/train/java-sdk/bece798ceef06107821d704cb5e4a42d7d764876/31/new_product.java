public CorpusStats getCorpusStats(final Corpus corpus) {
    final String corpusId = IDHelper.getCorpusId(corpus, getAccountId());
    return executeRequest(API_VERSION + corpusId + STATS_PATH, null, CorpusStats.class);
  }