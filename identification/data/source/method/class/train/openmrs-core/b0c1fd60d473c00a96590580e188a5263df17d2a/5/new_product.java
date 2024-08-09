@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_RELATIONSHIPS })
	public Relationship getRelationshipByUuid(String uuid) throws APIException;