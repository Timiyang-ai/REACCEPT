public Concepts getGraphsRelatedConcepts(final Graph graph, final Map<String, Object> parameters) {
		String graphId = IDValidator.getGraphId(graph, getAccountId());
		// TODO: we may need to divide this into 2 methods
		if (parameters.get(CONCEPTS) == null && parameters.get(CONCEPT) == null)
			throw new MissingFormatArgumentException("concept or concepts should be identified");

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
		if (parameters.get(CONCEPTS) != null) {
			JsonObject contentJson = new JsonObject();
			JsonArray conceptsJson = new JsonArray();
			@SuppressWarnings("unchecked")
			List<String> concepts = (List<String>) parameters.get(CONCEPTS);
			for (String value : concepts) {
				conceptsJson.add(new JsonPrimitive(value));
			}
			contentJson.add(CONCEPTS, conceptsJson);
			queryParameters.put(CONCEPTS, conceptsJson.toString());
			return executeRequest(API_VERSION + graphId + RELATED_CONCEPTS_PATH, queryParameters, Concepts.class);
		} else {
			Concept concept = new Concept(graph, (String) parameters.get(CONCEPT));
			return executeRequest(API_VERSION + concept.getId() + RELATED_CONCEPTS_PATH + RELATED_CONCEPTS_PATH,
					queryParameters, Concepts.class);
		}
	}