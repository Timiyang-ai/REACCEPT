public Concept retireConcept(Concept concept, String reason) throws APIException {
		if (!StringUtils.hasText(reason))
			throw new IllegalArgumentException("reason is required");
		
		// only do this if the concept isn't retired already
		if (concept.isRetired() == false) {
			checkIfLocked();
			
			concept.setRetired(true);
			concept.setRetireReason(reason);
			concept.setRetiredBy(Context.getAuthenticatedUser());
			concept.setDateRetired(new Date());
			return dao.saveConcept(concept);
		}
		
		return concept;
	}