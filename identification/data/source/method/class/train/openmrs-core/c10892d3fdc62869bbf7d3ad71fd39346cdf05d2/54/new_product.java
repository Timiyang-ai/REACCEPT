@Authorized( { PrivilegeConstants.VIEW_RELATIONSHIPS })
	public Map<Person, List<Person>> getRelationshipMap(RelationshipType relationshipType) throws APIException;