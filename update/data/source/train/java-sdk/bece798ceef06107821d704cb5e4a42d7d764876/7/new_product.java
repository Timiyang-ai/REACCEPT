public Scores getCorpusRelationScores(final Corpus corpus, final List<Concept> concepts) {
    final String corpusId = IDHelper.getCorpusId(corpus, getAccountId());
    Validate.notEmpty(concepts, "concepts cannot be empty");

    final Map<String, Object> queryParameters = new HashMap<String, Object>();
    final JsonObject contentJson = new JsonObject();
    final JsonArray conceptsJson = new JsonArray();
    for (final Concept con : concepts) {
      conceptsJson.add(new JsonPrimitive(con.getId()));
    }
    contentJson.add(CONCEPTS, conceptsJson);
    queryParameters.put(CONCEPTS, conceptsJson.toString());
    return executeRequest(API_VERSION + corpusId + RELATION_SCORES_PATH, queryParameters,
        Scores.class);
  }