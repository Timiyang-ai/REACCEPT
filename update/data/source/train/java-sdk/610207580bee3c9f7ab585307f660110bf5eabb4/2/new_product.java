public Concepts getConceptRelatedConcepts(final Concept concept,
      final Map<String, Object> parameters) {
    final String conceptId = IDHelper.getConceptId(concept);

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
    return executeRequest(API_VERSION + conceptId + RELATED_CONCEPTS_PATH, queryParameters,
        Concepts.class);
  }