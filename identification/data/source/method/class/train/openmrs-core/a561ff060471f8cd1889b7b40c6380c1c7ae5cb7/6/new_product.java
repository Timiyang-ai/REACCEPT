@Authorized(PrivilegeConstants.VIEW_CONCEPTS)
	public Concept getConceptByMapping(String code, String sourceName, Boolean includeRetired) throws APIException;