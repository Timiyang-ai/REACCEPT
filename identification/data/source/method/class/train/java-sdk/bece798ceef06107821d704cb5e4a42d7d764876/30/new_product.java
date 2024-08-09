public Concepts getDocumentRelatedConcepts(final Document document,
      final Map<String, Object> parameters) {
    final String documentId = IDHelper.getDocumentId(document);
    final String[] queryParameters = new String[] {LEVEL, LIMIT};
    final Map<String, Object> queryParams = new HashMap<String, Object>();
    for (final String param : queryParameters) {
      if (parameters.containsKey(param))
        queryParams.put(param, parameters.get(param));
    }
    if (parameters.get(CONCEPT_FIELDS) != null) {
      final RequestedFields fields = (RequestedFields) parameters.get(CONCEPT_FIELDS);
      if (fields != null && !fields.isEmpty())
        queryParams.put(CONCEPT_FIELDS, fields.toString());
    }
    return executeRequest(API_VERSION + documentId + RELATED_CONCEPTS_PATH, queryParams,
        Concepts.class);
  }