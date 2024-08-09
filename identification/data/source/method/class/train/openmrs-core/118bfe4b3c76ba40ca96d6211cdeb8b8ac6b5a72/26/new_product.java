@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_CONCEPTS })
	public ConceptDescription getConceptDescriptionByUuid(String uuid);