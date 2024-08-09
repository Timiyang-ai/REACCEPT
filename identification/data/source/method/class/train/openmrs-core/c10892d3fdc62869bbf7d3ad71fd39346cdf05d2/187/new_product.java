@Authorized(PrivilegeConstants.GET_CONCEPTS)
	public List<Concept> getConceptsByMapping(String code, String sourceName, boolean includeRetired) throws APIException;