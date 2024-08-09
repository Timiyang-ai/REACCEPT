public ConceptMetadata getConcept(final Concept concept) {
		IDValidator.getConceptId(concept);
		return executeRequest(API_VERSION + concept.getId(), null, ConceptMetadata.class);
	}