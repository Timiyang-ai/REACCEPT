@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_RELATIONSHIP_TYPES })
	public List<RelationshipType> getAllRelationshipTypes() throws APIException;