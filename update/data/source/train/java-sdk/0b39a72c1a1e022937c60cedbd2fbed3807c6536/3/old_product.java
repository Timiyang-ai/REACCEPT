public Corpus getCorpus(Corpus corpus) {
        ConceptInsightsId.validateGenarate(corpus, getAccountId());
        return executeRequest(version + corpus.getId(), null, Corpus.class);
    }