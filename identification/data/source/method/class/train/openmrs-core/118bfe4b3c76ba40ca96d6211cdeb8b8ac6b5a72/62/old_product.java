@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_CONCEPTS })
	public ConceptDescription getConceptDescriptionByUuid(String uuid);