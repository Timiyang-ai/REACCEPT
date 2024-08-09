@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public Concept getConcept(Integer conceptId) throws APIException;