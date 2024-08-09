@Deprecated
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public List<Concept> getConcepts(String sortBy, String dir) throws APIException;