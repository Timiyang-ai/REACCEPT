public ConceptMetadata getConcept(Concept concept,String graphName) {
        ConceptInsightsId.validateGenarate(concept, getAccountId(), graphName);
        return executeRequest(version + concept.getId(), null, ConceptMetadata.class);
    }