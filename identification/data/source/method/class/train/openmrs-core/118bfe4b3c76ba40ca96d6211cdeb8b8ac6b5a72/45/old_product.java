@Transactional(readOnly = true)
	public List<RelationshipType> getRelationshipTypes() throws APIException;