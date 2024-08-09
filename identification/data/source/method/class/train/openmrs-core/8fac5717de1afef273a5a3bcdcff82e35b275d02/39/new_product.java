public List<ConceptSet> getSetsContainingConcept(Concept concept) {
		if (concept.getConceptId() == null)
			return Collections.emptyList();
		
		return dao.getSetsContainingConcept(concept);
	}