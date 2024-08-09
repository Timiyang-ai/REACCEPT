@Transactional(readOnly=true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_RELATIONSHIP_TYPES })
	public RelationshipType getRelationshipType(Integer relationshipTypeId) throws APIException;