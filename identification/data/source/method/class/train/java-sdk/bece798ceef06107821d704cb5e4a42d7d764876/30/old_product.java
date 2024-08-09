public Concepts getDocumentRelatedConcepts(final Document document, final Map<String, Object> parameters) {
		String documentId = IDHelper.getDocumentId(document);
		String[] queryParameters = new String[] { LEVEL, LIMIT };
		Map<String, Object> queryParams = new HashMap<String, Object>();
		for (String param : queryParameters) {
			if (parameters.containsKey(param))
				queryParams.put(param, parameters.get(param));
		}
		if (parameters.get(CONCEPT_FIELDS) != null) {
			RequestedFields fields = (RequestedFields) parameters.get(CONCEPT_FIELDS);
			if (fields != null && !fields.isEmpty())
				queryParams.put(CONCEPT_FIELDS, fields.toString());
		}
		return executeRequest(API_VERSION + documentId + RELATED_CONCEPTS_PATH, queryParams, Concepts.class);
	}