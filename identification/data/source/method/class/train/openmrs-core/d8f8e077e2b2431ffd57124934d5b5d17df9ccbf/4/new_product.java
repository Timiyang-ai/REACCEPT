public List<Concept> getConceptsByAnswer(Concept concept) throws APIException {
		if (concept.getConceptId() == null)
			return Collections.emptyList();
		
		return dao.getConceptsByAnswer(concept);
	}