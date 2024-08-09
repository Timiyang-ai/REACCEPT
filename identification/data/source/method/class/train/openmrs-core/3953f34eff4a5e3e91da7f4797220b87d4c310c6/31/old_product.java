public Concept saveConcept(Concept concept) throws APIException {
		checkIfLocked();
		this.modifyCollections(concept);
		this.updateConceptWord(concept);
		return dao.saveConcept(concept);    
	}