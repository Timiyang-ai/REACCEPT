public Concepts getGraphRelatedConcepts(final Graph graph, final List<Concept> concepts,
      final Map<String, Object> parameters) {
    final String graphId = IDHelper.getGraphId(graph, getAccountId());
    Validate.notEmpty(concepts, "concepts cannot be empty");

    final Map<String, Object> queryParameters = new HashMap<String, Object>();
    final String[] queryParms = new String[] {LEVEL, LIMIT};
    for (final String param : queryParms) {
      if (parameters.containsKey(param))
        queryParameters.put(param, parameters.get(param));
    }
    if (parameters.get(CONCEPT_FIELDS) != null) {
      final RequestedFields fields = (RequestedFields) parameters.get(CONCEPT_FIELDS);
      if (fields != null && !fields.isEmpty())
        queryParameters.put(CONCEPT_FIELDS, toJson(fields.getFields()));
    }
    final JsonObject contentJson = new JsonObject();
    final JsonArray conceptsJson = new JsonArray();
    for (final Concept concept : concepts) {
      conceptsJson.add(new JsonPrimitive(concept.getId()));
    }
    contentJson.add(CONCEPTS, conceptsJson);
    queryParameters.put(CONCEPTS, conceptsJson.toString());
    return executeRequest(API_VERSION + graphId + RELATED_CONCEPTS_PATH, queryParameters,
        Concepts.class);
  }