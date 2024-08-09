public CorpusStats getCorpusStats(Corpus corpus) {
        ConceptInsightsId.validateGenarate(corpus, getAccountId());
        return executeRequest(version + corpus.getId() + STATS_PATH, null, CorpusStats.class);
    }