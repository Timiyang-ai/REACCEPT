public Concepts getConceptRelatedConcepts(final Concept concept, final Map<String, Object> parameters) {
		String conceptId = IDHelper.getConceptId(concept);

		Map<String, Object> queryParameters = new HashMap<String, Object>();
		String[] queryParms = new String[] { LEVEL, LIMIT };
		for (String param : queryParms) {
			if (parameters.containsKey(param))
				queryParameters.put(param, parameters.get(param));
		}
		if (parameters.get(CONCEPT_FIELDS) != null) {
			RequestedFields fields = (RequestedFields) parameters.get(CONCEPT_FIELDS);
			if (fields != null && !fields.isEmpty())
				queryParameters.put(CONCEPT_FIELDS, fields.toString());
		}
		return executeRequest(API_VERSION + conceptId + RELATED_CONCEPTS_PATH, queryParameters, Concepts.class);
	}