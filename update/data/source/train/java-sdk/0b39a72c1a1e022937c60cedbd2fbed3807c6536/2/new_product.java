public Scores getDocumentRelationScores(final Document document, final List<Concept> concepts) {
		String documentId = IDValidator.getDocumentId(document);
		Validate.notNull(concepts, "concepts can't be null");

		Map<String, Object> queryParams = new HashMap<String, Object>();
		JsonObject contentJson = new JsonObject();
		JsonArray conceptsJson = new JsonArray();

		for (Concept con : concepts) {
			conceptsJson.add(new JsonPrimitive(con.getId()));
		}
		contentJson.add(CONCEPTS, conceptsJson);
		queryParams.put(CONCEPTS, conceptsJson.toString());
		return executeRequest(API_VERSION + documentId + RELATION_SCORES_PATH, queryParams, Scores.class);
	}