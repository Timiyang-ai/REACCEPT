public Scores getDocumentRelationScores(final Document document, final List<Concept> concepts) {
    final String documentId = IDHelper.getDocumentId(document);
    Validate.notEmpty(concepts, "concepts cannot be empty");

    final Map<String, Object> queryParams = new HashMap<String, Object>();
    final JsonObject contentJson = new JsonObject();
    final JsonArray conceptsJson = new JsonArray();

    for (final Concept con : concepts) {
      conceptsJson.add(new JsonPrimitive(con.getId()));
    }
    contentJson.add(CONCEPTS, conceptsJson);
    queryParams.put(CONCEPTS, conceptsJson.toString());
    return executeRequest(API_VERSION + documentId + RELATION_SCORES_PATH, queryParams,
        Scores.class);
  }