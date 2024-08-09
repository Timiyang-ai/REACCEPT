@Transactional(readOnly = true)
	public Map<Person, List<Person>> getRelationships(RelationshipType relationshipType) throws APIException;