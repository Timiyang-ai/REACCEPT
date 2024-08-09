public QueryConcepts conceptualSearch(Corpus corpus, Map<String, Object> parameters) {
		Validate.notNull(parameters.get(IDS), "ids can't be null");
		String corpusId = IDValidator.getCorpusId(corpus, getAccountId());

		Map<String, Object> queryParams = new HashMap<String, Object>();
		String[] queryParameters = new String[] { CURSOR, LIMIT };

		for (String param : queryParameters) {
			if (parameters.containsKey(param))
				queryParams.put(param, parameters.get(param));
		}

		JsonArray IdsJsonArray = new JsonArray();
		@SuppressWarnings("unchecked")
		List<String> ids = (List<String>) parameters.get(IDS);
		for (String value : ids) {
			IdsJsonArray.add(new JsonPrimitive(value));
		}
		queryParams.put(IDS, IdsJsonArray.toString());

		if (parameters.get(CONCEPT_FIELDS) != null) {
			RequestedFields fields = (RequestedFields) parameters.get(CONCEPT_FIELDS);
			if (fields != null && !fields.isEmpty())
				queryParams.put(CONCEPT_FIELDS, fields.toString());
		}

		if (parameters.get(DOCUMENT_FIELDS) != null) {
			RequestedFields fields = (RequestedFields) parameters.get(DOCUMENT_FIELDS);
			if (fields != null && !fields.isEmpty())
				queryParams.put(DOCUMENT_FIELDS, fields.toString());
		}

		return executeRequest(API_VERSION + corpusId + CONCEPTUAL_SEARCH_PATH, queryParams, QueryConcepts.class);
	}