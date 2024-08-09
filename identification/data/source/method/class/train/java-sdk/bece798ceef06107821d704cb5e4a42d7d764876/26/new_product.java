public QueryConcepts conceptualSearch(Corpus corpus, Map<String, Object> parameters) {
    Validate.notNull(parameters.get(IDS), "ids cannot be null");
    final String corpusId = IDHelper.getCorpusId(corpus, getAccountId());

    final Map<String, Object> queryParams = new HashMap<String, Object>();
    final String[] queryParameters = new String[] {CURSOR, LIMIT};

    for (final String param : queryParameters) {
      if (parameters.containsKey(param))
        queryParams.put(param, parameters.get(param));
    }

    final JsonArray IdsJsonArray = new JsonArray();
    @SuppressWarnings("unchecked")
    final List<String> ids = (List<String>) parameters.get(IDS);
    for (final String value : ids) {
      IdsJsonArray.add(new JsonPrimitive(value));
    }
    queryParams.put(IDS, IdsJsonArray.toString());

    if (parameters.get(CONCEPT_FIELDS) != null) {
      final RequestedFields fields = (RequestedFields) parameters.get(CONCEPT_FIELDS);
      if (fields != null && !fields.isEmpty())
        queryParams.put(CONCEPT_FIELDS, fields.toString());
    }

    if (parameters.get(DOCUMENT_FIELDS) != null) {
      final RequestedFields fields = (RequestedFields) parameters.get(DOCUMENT_FIELDS);
      if (fields != null && !fields.isEmpty())
        queryParams.put(DOCUMENT_FIELDS, fields.toString());
    }

    return executeRequest(API_VERSION + corpusId + CONCEPTUAL_SEARCH_PATH, queryParams,
        QueryConcepts.class);
  }