@Authorized( { PrivilegeConstants.GET_RELATIONSHIP_TYPES })
	public List<RelationshipType> getAllRelationshipTypes(boolean includeRetired) throws APIException;