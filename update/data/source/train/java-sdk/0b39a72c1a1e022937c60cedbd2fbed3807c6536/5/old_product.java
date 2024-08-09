public Documents listDocuments(Corpus corpus, String query, int limit, int cursor) {
        ConceptInsightsId.validateGenarate(corpus, getAccountId());
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        if (cursor >= 0) {
            queryParameters.put(CURSOR, cursor);
        }
        if (limit >= 0) {
            queryParameters.put(LIMIT, limit);
        }
        if (query != null) {
            // TODO we may need to work in the query format,for now we do expect 
            // the query parameter String formatted as documented in Concept Insights.
            queryParameters.put(QUERY, query);
        }

        return executeRequest(version + corpus.getId() + DOCUMENTS, queryParameters, Documents.class);
    }