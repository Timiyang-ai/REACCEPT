@Authorized( { PrivilegeConstants.GET_RELATIONSHIP_TYPES })
	public List<RelationshipType> getRelationshipTypes(String relationshipTypeName, Boolean preferred) throws APIException;