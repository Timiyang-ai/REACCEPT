public Scores getCorpusRelationScores(final Corpus corpus, final List<Concept> concepts) {
		String corpusId = IDValidator.getCorpusId(corpus, getAccountId());
		Validate.notNull(concepts, "concepts can't be null");

		Map<String, Object> queryParameters = new HashMap<String, Object>();
		JsonObject contentJson = new JsonObject();
		JsonArray conceptsJson = new JsonArray();
		for (Concept con : concepts) {
			conceptsJson.add(new JsonPrimitive(con.getId()));
		}
		contentJson.add(CONCEPTS, conceptsJson);
		queryParameters.put(CONCEPTS, conceptsJson.toString());
		return executeRequest(API_VERSION + corpusId + RELATION_SCORES_PATH, queryParameters, Scores.class);
	}