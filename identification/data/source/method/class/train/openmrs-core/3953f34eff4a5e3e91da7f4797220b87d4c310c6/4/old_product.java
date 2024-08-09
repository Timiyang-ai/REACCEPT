public Concept saveConcept(Concept concept) throws APIException {
		
		// make sure the administrator hasn't turned off concept editing
		checkIfLocked();
		
		Concept conceptToReturn = dao.saveConcept(concept);
		
		// add/remove entries in the concept_word table (used for searching)
		this.updateConceptWord(conceptToReturn);
		
		return conceptToReturn;
	}