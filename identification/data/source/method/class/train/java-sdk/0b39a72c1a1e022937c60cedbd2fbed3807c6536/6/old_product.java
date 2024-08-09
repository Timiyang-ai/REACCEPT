public Scores getCorpusRelationScores(Corpus corpus,List<String> concepts) {
        ConceptInsightsId.validateGenarate(corpus, getAccountId());
        Validate.notNull(concepts, "concepts can't be null");

        Map<String, Object> queryParameters = new HashMap<String, Object>();
        JsonObject contentJson = new JsonObject();
        JsonArray conceptsJson = new JsonArray();
        for (String value : concepts) {
            conceptsJson.add(new JsonPrimitive(value));
        }
        contentJson.add(CONCEPTS, conceptsJson);
        queryParameters.put(CONCEPTS, conceptsJson.toString());
        return executeRequest(version + corpus.getId() + RELATION_SCORES_PATH, queryParameters, Scores.class);
    }