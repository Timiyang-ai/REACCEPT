public Concepts getGraphRelatedConcepts(final Graph graph, final List<Concept> concepts,
			final Map<String, Object> parameters) {
		String graphId = IDHelper.getGraphId(graph, getAccountId());
		Validate.notNull(concepts, "concepts should be specified");

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
		JsonObject contentJson = new JsonObject();
		JsonArray conceptsJson = new JsonArray();
		for (Concept concept : concepts) {
			conceptsJson.add(new JsonPrimitive(concept.getId()));
		}
		contentJson.add(CONCEPTS, conceptsJson);
		queryParameters.put(CONCEPTS, conceptsJson.toString());
		return executeRequest(API_VERSION + graphId + RELATED_CONCEPTS_PATH, queryParameters, Concepts.class);
	}