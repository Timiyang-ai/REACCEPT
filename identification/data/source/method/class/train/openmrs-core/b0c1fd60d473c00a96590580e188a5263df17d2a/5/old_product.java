@Transactional(readOnly = true)
	public Relationship getRelationshipByUuid(String uuid) throws APIException;