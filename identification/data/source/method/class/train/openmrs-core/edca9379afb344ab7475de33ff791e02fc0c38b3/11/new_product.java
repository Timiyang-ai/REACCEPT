public void purgeConcept(Concept concept) throws APIException {
		checkIfLocked();
		
		if (concept.getConceptId() != null) {
			for (ConceptName conceptName : concept.getNames()) {
				if (hasAnyObservation(conceptName))
					throw new ConceptNameInUseException("Can't delete concept with id : " + concept.getConceptId()
					        + " because it has a name '" + conceptName.getName()
					        + "' which is being used by some observation(s)");
			}
		}
		
		dao.purgeConcept(concept);
	}