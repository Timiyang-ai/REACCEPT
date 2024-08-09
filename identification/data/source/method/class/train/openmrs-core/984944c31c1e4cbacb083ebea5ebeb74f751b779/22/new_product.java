@Authorized( { PrivilegeConstants.GET_RELATIONSHIPS })
	public List<Relationship> getRelationships(Person fromPerson, Person toPerson, RelationshipType relType)
	        throws APIException;