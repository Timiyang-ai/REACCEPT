public void purgeConcept(Concept concept) throws APIException {
		checkIfLocked();
		dao.purgeConcept(concept);
	}