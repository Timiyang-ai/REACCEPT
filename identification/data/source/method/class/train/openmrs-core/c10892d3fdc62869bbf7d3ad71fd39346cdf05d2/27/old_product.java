@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_RELATIONSHIPS })
	public List<Relationship> getAllRelationships() throws APIException;