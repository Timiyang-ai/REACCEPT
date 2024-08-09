@Transactional(readOnly=true)
	public Map<Person, List<Person>> getRelationships(RelationshipType relType) throws APIException;