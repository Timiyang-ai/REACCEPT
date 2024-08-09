@Transactional(readOnly=true)
	public RelationshipType getRelationshipType(Integer relationshipTypeId) throws APIException;