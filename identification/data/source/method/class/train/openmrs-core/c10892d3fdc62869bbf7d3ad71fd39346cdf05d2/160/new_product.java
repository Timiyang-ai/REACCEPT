@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_RELATIONSHIPS })
	public List<Relationship> getRelationshipsByPerson(Person p) throws APIException;