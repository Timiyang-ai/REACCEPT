@Authorized(PrivilegeConstants.GET_CONCEPTS)
	public List<Concept> getAllConcepts(String sortBy, boolean asc, boolean includeRetired) throws APIException;