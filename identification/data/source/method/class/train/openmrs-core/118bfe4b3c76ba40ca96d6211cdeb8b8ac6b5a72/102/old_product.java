@Transactional(readOnly = true)
	public RelationshipType getRelationshipTypeByUuid(String uuid) throws APIException;