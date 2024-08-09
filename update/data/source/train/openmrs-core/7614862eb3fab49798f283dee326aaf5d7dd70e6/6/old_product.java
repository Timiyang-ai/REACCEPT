public Concept saveConcept(Concept concept) throws APIException {
		
		// make sure the administrator hasn't turned off concept editing
		checkIfLocked();
		checkIfDatatypeCanBeChanged(concept);
		
		//check that there is no concept already using the preferred concept name in the locale
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
		if (errors.hasErrors())
			throw new APIException("Validation errors found");
		
		Concept conceptToReturn = dao.saveConcept(concept);
		
		// add/remove entries in the concept_word table (used for searching)
		this.updateConceptWord(conceptToReturn);
		
		return conceptToReturn;
	}