@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public ConceptNameTag getConceptNameTagByUuid(String uuid);