public CorpusProcessingState getCorpusProcessingState(final Corpus corpus) {
    final String corpusId = IDHelper.getCorpusId(corpus, getAccountId());
    return executeRequest(API_VERSION + corpusId + PROCESSING_STATE_PATH, null,
        CorpusProcessingState.class);
  }