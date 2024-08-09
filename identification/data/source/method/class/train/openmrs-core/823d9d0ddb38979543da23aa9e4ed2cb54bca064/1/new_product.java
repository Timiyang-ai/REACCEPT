public Concept retireConcept(Concept concept, String reason) throws APIException {
		if (!StringUtils.hasText(reason))
			throw new IllegalArgumentException(Context.getMessageSourceService().getMessage("general.voidReason.empty"));
		
		// only do this if the concept isn't retired already
		if (concept.isRetired() == false) {
			checkIfLocked();
			
			concept.setRetired(true);
			concept.setRetireReason(reason);
			return dao.saveConcept(concept);
			
		}
		
		return concept;
	}