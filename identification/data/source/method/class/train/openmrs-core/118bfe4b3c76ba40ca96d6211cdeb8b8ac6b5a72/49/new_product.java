@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public ConceptName getConceptNameByUuid(String uuid);