@Transactional(readOnly=true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_RELATIONSHIPS })
	public Relationship getRelationship(Integer relationshipId) throws APIException;