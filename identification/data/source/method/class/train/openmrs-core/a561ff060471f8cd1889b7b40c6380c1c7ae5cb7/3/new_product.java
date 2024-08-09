@Authorized(PrivilegeConstants.GET_CONCEPTS)
	public Concept getConceptByMapping(String code, String sourceName, Boolean includeRetired) throws APIException;