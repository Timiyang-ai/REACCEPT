@Authorized( { PrivilegeConstants.VIEW_RELATIONSHIPS })
	public List<Relationship> getAllRelationships(boolean includeVoided) throws APIException;