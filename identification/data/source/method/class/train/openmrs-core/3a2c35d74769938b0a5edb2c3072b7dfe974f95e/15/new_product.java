@Deprecated
	@Authorized(PrivilegeConstants.GET_CONCEPTS)
	public List<Concept> getConcepts(String sortBy, String dir) throws APIException;