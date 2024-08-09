public Concept saveConcept(Concept concept) throws APIException {
		
		// make sure the administrator hasn't turned off concept editing
		checkIfLocked();
		
		// set that creator/dateCreated properties on the concept and child objects
		this.modifyCollections(concept);
		
		Concept conceptToReturn = dao.saveConcept(concept);
		
		// add/remove entries in the concept_word table (used for searching)
		this.updateConceptWord(conceptToReturn);
		
		return conceptToReturn;
	}