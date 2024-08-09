@Transactional(readOnly=true)
	public Relationship getRelationship(Integer relationshipId) throws APIException;