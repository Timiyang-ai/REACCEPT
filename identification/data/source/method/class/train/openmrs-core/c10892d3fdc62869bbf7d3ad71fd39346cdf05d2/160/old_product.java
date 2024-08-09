@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_RELATIONSHIPS })
	public List<Relationship> getRelationshipsByPerson(Person p) throws APIException;