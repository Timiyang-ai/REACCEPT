@Transactional(readOnly=true)
	public List<Relationship> getRelationships() throws APIException;