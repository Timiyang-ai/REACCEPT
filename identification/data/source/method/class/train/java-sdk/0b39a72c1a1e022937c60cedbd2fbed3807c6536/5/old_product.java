public Scores getDocumentRelationScores(Document document,String corpusName,List<String> concepts) {
        ConceptInsightsId.validateGenarate(document, getAccountId(), corpusName);
        Validate.notNull(concepts, "concepts can't be null");

        Map<String, Object> queryParams = new HashMap<String, Object>();
        JsonObject contentJson = new JsonObject();
        JsonArray conceptsJson = new JsonArray();

        for (String value : concepts) {
            conceptsJson.add(new JsonPrimitive(value));
        }
        contentJson.add(CONCEPTS, conceptsJson);
        queryParams.put(CONCEPTS, conceptsJson.toString());
        return executeRequest(version + document.getId() + RELATION_SCORES_PATH, queryParams, Scores.class);
    }