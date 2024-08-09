@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_RELATIONSHIPS })
	public List<Relationship> getRelationships(Person fromPerson, Person toPerson, RelationshipType relType,
	        Date startEffectiveDate, Date endEffectiveDate) throws APIException;