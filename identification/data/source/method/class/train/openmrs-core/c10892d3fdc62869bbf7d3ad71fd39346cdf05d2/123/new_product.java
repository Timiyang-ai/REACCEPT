@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_RELATIONSHIP_TYPES })
	public RelationshipType getRelationshipTypeByName(String relationshipTypeName) throws APIException;