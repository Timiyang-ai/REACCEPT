public Concepts getDocumentRelatedConcepts(Document document,String corpusName, RequestedFields conceptFields, int level, int limit) {
        ConceptInsightsId.validateGenarate(document, getAccountId(), corpusName);
        Map<String, Object> queryParams = new HashMap<String, Object>();
        if (level >= 0) {
            queryParams.put(LEVEL, level);
        }
        if (limit >= 0) {
            queryParams.put(LIMIT, limit);
        }
        if (conceptFields != null) {
            if (conceptFields != null && conceptFields.getFields() != null && !conceptFields.getFields().isEmpty())
                queryParams.put(CONCEPT_FIELDS, conceptFields.toString());
        }
        return executeRequest(version + document.getId() + RELATED_CONCEPTS_PATH, queryParams, Concepts.class);
    }