@Authorized( { PrivilegeConstants.GET_RELATIONSHIPS })
	public List<Relationship> getAllRelationships(boolean includeVoided) throws APIException;