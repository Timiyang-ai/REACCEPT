@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_RELATIONSHIP_TYPES })
	public List<RelationshipType> getAllRelationshipTypes(boolean includeRetired) throws APIException;