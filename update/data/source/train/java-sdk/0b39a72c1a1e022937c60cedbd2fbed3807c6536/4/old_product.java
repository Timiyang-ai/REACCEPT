public QueryConcepts conceptualSearch(Corpus corpus, List<String> ids, RequestedFields conceptFields, RequestedFields documentFields, int cursor, int limit) {
        ConceptInsightsId.validateGenarate(corpus, getAccountId());
        Validate.notNull(ids, "ids can't be null");
        Map<String, Object> queryParams = new HashMap<String, Object>();
        if (cursor >= 0) {
            queryParams.put(CURSOR, cursor);
        }
        if (limit >= 0) {
            queryParams.put(LIMIT, limit);
        }
        JsonArray IdsJsonArray = new JsonArray();
        for (String value : ids) {
            IdsJsonArray.add(new JsonPrimitive(value));
        }
        queryParams.put(IDS, IdsJsonArray.toString());
        if (conceptFields != null) {
            if (conceptFields != null && conceptFields.getFields() != null && !conceptFields.getFields().isEmpty())
                queryParams.put(CONCEPT_FIELDS, conceptFields.toString());
        }

        if (documentFields != null) {
            if (documentFields != null && documentFields.getFields() != null && !documentFields.getFields().isEmpty())
                queryParams.put(DOCUMENT_FIELDS, documentFields.toString());
        }

        return executeRequest(version + corpus.getId() + CONCEPTUAL_SEARCH_PATH, queryParams, QueryConcepts.class);
    }