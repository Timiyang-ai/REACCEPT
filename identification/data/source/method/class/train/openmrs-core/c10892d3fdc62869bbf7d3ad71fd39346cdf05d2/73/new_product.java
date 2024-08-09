@Authorized( { PrivilegeConstants.GET_RELATIONSHIPS })
	public List<Relationship> getRelationshipsByPerson(Person p, Date effectiveDate) throws APIException;