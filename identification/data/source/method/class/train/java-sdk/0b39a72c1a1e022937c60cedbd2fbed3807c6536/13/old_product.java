public Concepts getGraphsRelatedConcepts(String graphName, List<String> concepts, String conceptName, RequestedFields conceptFields, int level, int limit) {
        //TODO: we may need to divide this into 2 methods
        Validate.notNull(graphName, "graphName can't be null");
        if (concepts == null && conceptName == null)
            throw new MissingFormatArgumentException("conceptName or concepts should be identified");

        Graph graph = new Graph(getAccountId(), graphName);
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        if (level >= 0) {
            queryParameters.put(LEVEL, level);
        }
        if (limit >= 0) {
            queryParameters.put(LIMIT, limit);
        }
        if (conceptFields != null) {
            if (conceptFields != null && conceptFields.getFields() != null && !conceptFields.getFields().isEmpty())
                queryParameters.put(CONCEPT_FIELDS, conceptFields);
        }
        if (concepts != null) {
            JsonObject contentJson = new JsonObject();
            JsonArray conceptsJson = new JsonArray();
            for (String value : concepts) {
                conceptsJson.add(new JsonPrimitive(value));
            }
            contentJson.add(CONCEPTS, conceptsJson);
            queryParameters.put(CONCEPTS, conceptsJson.toString());

            return executeRequest(version + graph.getId() + RELATED_CONCEPTS_PATH, queryParameters, Concepts.class);
        } else {
            Concept concept = new Concept(graph, conceptName);
            return executeRequest(version + concept.getId() + RELATED_CONCEPTS_PATH, queryParameters, Concepts.class);
        }
    }