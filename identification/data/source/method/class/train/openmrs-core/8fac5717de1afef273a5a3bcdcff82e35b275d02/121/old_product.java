@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_RELATIONSHIPS })
	public Relationship getRelationship(Integer relationshipId) throws APIException;