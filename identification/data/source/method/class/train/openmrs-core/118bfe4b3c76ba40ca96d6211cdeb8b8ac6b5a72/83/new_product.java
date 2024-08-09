@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_SOURCES)
	public ConceptSource getConceptSourceByUuid(String uuid);