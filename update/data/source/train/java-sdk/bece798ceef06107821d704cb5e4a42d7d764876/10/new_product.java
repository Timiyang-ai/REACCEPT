public Documents listDocuments(final Corpus corpus, final Map<String, Object> parameters) {
    final String corpusId = IDHelper.getCorpusId(corpus, getAccountId());

    final Map<String, Object> queryParameters = new HashMap<String, Object>();
    final String[] queryParams = new String[] {CURSOR, LIMIT};
    for (final String param : queryParams) {
      if (parameters.containsKey(param)) {
        queryParameters.put(param, parameters.get(param));
      }
    }
    if (parameters.get(QUERY) != null) {
      // TODO we may need to work in the query format,for now we do expect
      // the query parameter String formatted as documented in Concept Insights.
      queryParameters.put(QUERY, parameters.get(QUERY));
    }

    return executeRequest(API_VERSION + corpusId + DOCUMENTS_PATH, queryParameters, Documents.class);
  }