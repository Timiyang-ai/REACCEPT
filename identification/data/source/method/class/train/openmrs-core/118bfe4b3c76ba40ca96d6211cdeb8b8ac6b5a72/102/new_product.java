@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_RELATIONSHIP_TYPES })
	public RelationshipType getRelationshipTypeByUuid(String uuid) throws APIException;