@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public ConceptSource getConceptSourceByName(String conceptSourceName) throws APIException;