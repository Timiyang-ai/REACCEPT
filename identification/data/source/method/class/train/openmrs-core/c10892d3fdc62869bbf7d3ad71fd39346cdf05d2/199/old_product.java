@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public List<Concept> getAllConcepts() throws APIException;