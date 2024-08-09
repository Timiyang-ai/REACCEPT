public CorpusProcessingState getCorpusProcessingState(Corpus corpus) {
        ConceptInsightsId.validateGenarate(corpus, getAccountId());
        return executeRequest(version + corpus.getId() + PROCESSING_STATE_PATH, null, CorpusProcessingState.class);
    }